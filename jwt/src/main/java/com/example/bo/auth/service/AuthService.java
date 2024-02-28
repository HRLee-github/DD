package com.example.bo.auth.service;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.jwt.model.JwtVO;

public interface AuthService {
    JwtVO login(MemberVO memberVO);
}
