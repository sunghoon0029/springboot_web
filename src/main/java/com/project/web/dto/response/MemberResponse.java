package com.project.web.dto.response;

import com.project.web.entity.Authority;
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

    private String nickname;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private List<Authority> roles = new ArrayList<>();

    public MemberResponse(Long id, String email, String nickname, List<Authority> roles, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = getId();
        this.email = getEmail();
        this.nickname = getNickname();
        this.createdTime = getCreatedTime();
        this.updatedTime = getUpdatedTime();
        this.roles = getRoles();
    }
}
