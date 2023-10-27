package com.project.web.controller;

import com.project.web.dto.request.SignRequest;
import com.project.web.dto.response.SignResponse;
import com.project.web.security.jwt.TokenDTO;
import com.project.web.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService memberService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(memberService.join(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request) throws Exception {
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
    public ResponseEntity<Void> logout(HttpServletRequest request) throws Exception {
        memberService.logout();
        return ResponseEntity.ok().build();
    }
}
