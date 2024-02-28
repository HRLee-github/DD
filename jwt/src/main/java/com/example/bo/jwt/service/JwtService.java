package com.example.bo.jwt.service;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.jwt.model.JwtVO;

public interface JwtService {
    JwtVO genToken(MemberVO memberVO);

    JwtVO genAccessToken(MemberVO memberVO);

    JwtVO genRefreshToken(MemberVO memberVO);
}
