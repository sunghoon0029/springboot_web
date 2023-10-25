package com.project.web.dto.request;

import lombok.Getter;

@Getter
public class MemberRequest {

    private Long id;

    private String email;

    private String password;

    private String nickname;
}
