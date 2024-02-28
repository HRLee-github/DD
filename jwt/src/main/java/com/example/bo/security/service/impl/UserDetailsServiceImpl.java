package com.example.bo.security.service.impl;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.api.member.service.MemberService;
import com.example.bo.security.model.UserDetailsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    @Transactional(readOnly = true)
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberService.findById(username);

        if(memberVO == null) {
            throw new UsernameNotFoundException("User not found with username: ".concat(username));
        }

        return UserDetailsVO.builder()
                .username(memberVO.getId())
                .password("{noop}".concat(memberVO.getPw()))
                .build();
    }
}
