package com.project.web.service;

import com.project.web.dto.request.MemberRequest;
import com.project.web.dto.response.MemberResponse;
import com.project.web.entity.Board;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;
    public final PasswordEncoder passwordEncoder;

    public List<MemberResponse> findAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberResponse> memberResponseList = new ArrayList<>();

        for (Member member: memberList) {
            List<Board> boards = member.getBoards();

            memberResponseList.add(MemberResponse.toDTO(member, boards));
        }
        return memberResponseList;
    }

    public MemberResponse findById(Long id) throws Exception{
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        return new MemberResponse(member);
    }

    public boolean update(Long id, MemberRequest request) throws Exception {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        member.updateMember(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getNickname());

        memberRepository.save(member);

        return true;
    }

    public boolean deleteById(Long id) {
        memberRepository.deleteById(id);

        return true;
    }
}
