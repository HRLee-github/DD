package com.example.bo.jwt.service.impl;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.jwt.JwtProvider;
import com.example.bo.jwt.model.JwtVO;
import com.example.bo.jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.access-token-exp-seconds}")
    private long accessTokenExpSeconds;

    @Value("${jwt.refresh-token-exp-seconds}")
    private long refreshTokenExpSeconds;

    private final JwtProvider jwtProvider;

    @Override
    public JwtVO genToken(MemberVO memberVO) {
        return JwtVO.builder()
                    .accessToken(genAccessToken(memberVO).getAccessToken())
                    .refreshToken(genRefreshToken(memberVO).getRefreshToken())
                    .build();
    }

    @Override
    public JwtVO genAccessToken(MemberVO memberVO) {
        Claims claims = Jwts.claims();

        claims.setAudience(memberVO.getId());

        return JwtVO.builder()
                    .accessToken(jwtProvider.buildToken(claims, accessTokenExpSeconds))
                    .build();
    }

    @Override
    public JwtVO genRefreshToken(MemberVO memberVO) {
        Claims claims = Jwts.claims();

        claims.setAudience(memberVO.getId());

        return JwtVO.builder()
                .refreshToken(jwtProvider.buildToken(claims, refreshTokenExpSeconds))
                .build();
    }
}
