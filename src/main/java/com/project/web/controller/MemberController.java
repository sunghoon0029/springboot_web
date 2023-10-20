package com.project.web.controller;

import com.project.web.dto.response.MemberResponse;
import com.project.web.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public List<MemberResponse> findAll(){
        return memberService.findAll();
    }
}
