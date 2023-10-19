package com.project.web.dto;

import com.project.web.entity.Authority;
import com.project.web.entity.Member;
import com.project.web.security.TokenDto;
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
public class SignResponse {

    private Long id;

    private String email;

    private String nickname;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<Authority> roles = new ArrayList<>();

    private TokenDto token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.createTime = member.getCreatedTime();
        this.updateTime = member.getUpdatedTime();
        this.roles = member.getRoles();
    }
}
