package com.project.web.controller;

import com.project.web.dto.response.MemberResponse;
import com.project.web.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(memberService.deleteById(id), HttpStatus.OK);
    }
}
