package com.example.bo.jwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class JwtVO implements Serializable {
    private static final long serialVersionUID = 5131310271211201286L;

    private String accessToken;
    private String refreshToken;
}