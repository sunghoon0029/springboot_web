package com.project.web.controller;

import com.project.web.dto.request.SignRequest;
import com.project.web.dto.response.SignResponse;
import com.project.web.security.jwt.TokenDTO;
import com.project.web.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService memberService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(memberService.join(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(memberService.login(request), HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> findByEmail(@RequestParam String email) throws Exception {
        return new ResponseEntity<>(memberService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshAccessToken(@RequestBody TokenDTO token) throws Exception {
        return new ResponseEntity<>(memberService.refreshAccessToken(token), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam  String accessToken, @RequestParam String refreshToken) {
        try {
            memberService.logout(accessToken, refreshToken);
            return ResponseEntity.ok("로그아웃 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 실패" + e.getMessage());
        }
    }
}
