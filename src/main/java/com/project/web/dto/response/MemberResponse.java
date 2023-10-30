package com.project.web.dto.response;

import com.project.web.entity.Authority;
import com.project.web.entity.Board;
import com.project.web.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private List<Authority> roles = new ArrayList<>();

    private List<Board> boards = new ArrayList<>();

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.createdTime = member.getCreatedTime();
        this.updatedTime = member.getUpdatedTime();
        this.roles = member.getRoles();
    }

    public static MemberResponse toDTO(Member member, List<Board> boards) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setEmail(member.getEmail());
        memberResponse.setPassword(member.getPassword());
        memberResponse.setNickname(member.getNickname());
        memberResponse.setCreatedTime(member.getCreatedTime());
        memberResponse.setUpdatedTime(member.getUpdatedTime());
        memberResponse.setRoles(member.getRoles());
        memberResponse.setBoards(boards);
        return memberResponse;
    }
}
