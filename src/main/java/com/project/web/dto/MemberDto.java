package com.project.web.dto;

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
public class MemberDto {

    private Long id;

    private String email;

    private String nickname;

    private List<Authority> roles = new ArrayList<>();

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.roles = member.getRoles();
        this.createTime = member.getCreatedTime();
        this.updateTime = member.getUpdatedTime();
    }
}
