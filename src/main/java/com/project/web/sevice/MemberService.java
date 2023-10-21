package com.project.web.sevice;

import com.project.web.dto.response.MemberResponse;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;

    public List<MemberResponse> findAll() {

        List<Member> memberList = memberRepository.findAll();
        List<MemberResponse> memberResponseList = new ArrayList<>();

        for (Member member: memberList) {
            MemberResponse memberResponse = new MemberResponse(
                    member.getId(),
                    member.getEmail(),
                    member.getPassword(),
                    member.getNickname(),
                    member.getCreatedTime(),
                    member.getUpdatedTime(),
                    member.getRoles()
            );
            memberResponseList.add(memberResponse);
        }
        return memberResponseList;
    }

    public MemberResponse findById(Long id) throws Exception{
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));
        return new MemberResponse(member);
    }

    public boolean deleteById(Long id) {
        memberRepository.deleteById(id);
        return true;
    }
}
