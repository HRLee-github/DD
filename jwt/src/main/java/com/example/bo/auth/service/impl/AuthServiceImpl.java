package com.example.bo.auth.service.impl;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.api.member.service.MemberService;
import com.example.bo.auth.service.AuthService;
import com.example.bo.jwt.model.JwtVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtVO login(MemberVO memberVO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberVO.getId(), memberVO.getPw()));

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new CustomDataNotFoundException("User with email [" + request.getEmail() + "] not found")
                );

        revokeAllUserTokens(user);

        return saveUserTokenAndReturnAuthResponse(user);

    }
}
