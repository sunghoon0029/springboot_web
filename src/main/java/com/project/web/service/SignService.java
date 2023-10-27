package com.project.web.service;

import com.project.web.dto.request.SignRequest;
import com.project.web.dto.response.SignResponse;
import com.project.web.entity.Authority;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import com.project.web.security.jwt.JwtProvider;
import com.project.web.security.jwt.Token;
import com.project.web.security.jwt.TokenDTO;
import com.project.web.security.jwt.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;

    public boolean join(SignRequest request) throws Exception {
        try {
            Member member = Member.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nickname(request.getNickname())
                    .build();

            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public SignResponse login(SignRequest request) throws Exception {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정 정보 입니다."));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보 입니다.");
        }

        String token = jwtProvider.createToken(member.getEmail(), member.getRoles());
        redisTemplate.opsForValue().set("JWT_TOKEN:" + request.getEmail(), token, jwtProvider.getExpiration(token));

        return SignResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdTime(member.getCreatedTime())
                .updatedTime(member.getUpdatedTime())
                .roles(member.getRoles())
                .token(TokenDTO.builder()
                        .accessToken(token)
                        .refreshToken(createRefreshToken(member))
                        .build())
                .build();
    }

    public void logout() {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (redisTemplate.opsForValue().get("JWT_TOKEN:" + member.getEmail()) != null) {
            redisTemplate.delete("JWT_TOKEN:" + member.getEmail());
        }
    }


    public SignResponse findByEmail(String email) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        return new SignResponse(member);
    }

    public String createRefreshToken(Member member) {
        Token token = tokenRepository.save(
                Token.builder()
                        .id(member.getId())
                        .refreshToken(UUID.randomUUID().toString())
                        .exp(7)
                        .build()
        );
        return token.getRefreshToken();
    }

    public Token validRefreshToken(Member member, String refreshToken) throws Exception {
        Token token = tokenRepository.findById(member.getId()).orElseThrow(() -> new Exception("만료된 계정입니다. 로그인을 다시 시도하세요."));
        if (token.getRefreshToken() == null) {
            return null;
        } else {
            if (token.getExp() < 1) {
                token.setExp(7);
                tokenRepository.save(token);
            }
            if (!token.getRefreshToken().equals(refreshToken)) {
                return null;
            } else {
                return token;
            }
        }
    }

    public TokenDTO refreshAccessToken(TokenDTO token) throws Exception {
        String email = jwtProvider.getEmail(token.getAccessToken());
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정 정보 입니다."));
        Token refreshToken = validRefreshToken(member, token.getRefreshToken());

        if (refreshToken != null) {
            return TokenDTO.builder()
                    .accessToken(jwtProvider.createToken(email, member.getRoles()))
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        } else {
            throw new Exception("로그인을 시도하세요.");
        }
    }
}
