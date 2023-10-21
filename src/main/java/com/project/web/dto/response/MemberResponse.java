package com.project.web.dto.response;

import com.project.web.entity.Authority;
import com.project.web.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.createdTime = member.getCreatedTime();
        this.updatedTime = member.getUpdatedTime();
        this.roles = member.getRoles();
    }
}
