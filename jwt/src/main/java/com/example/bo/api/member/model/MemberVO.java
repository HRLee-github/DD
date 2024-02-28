package com.example.bo.api.member.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 회원 데이터 객체
 */
@Getter
@Setter
@Builder
public class MemberVO implements Serializable {
    private static final long serialVersionUID = 4176069190956995574L;

    private String id;
    private String pw;
    private String nm;
}