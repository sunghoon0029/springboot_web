package com.project.web.dto.request;

import lombok.Getter;

@Getter
public class SignRequest {

    private Long id;

    private String email;

    private String password;

    private String nickname;
}
