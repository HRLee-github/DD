package com.example.bo.api.member.service.impl;

import com.example.bo.api.member.model.MemberVO;
import com.example.bo.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final ServletContext servletContext;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<MemberVO> findAll() {
        return (List<MemberVO>) servletContext.getAttribute("memberList");
    }

    @Override
    @Transactional(readOnly = true)
    public MemberVO findById(String id) {
        return findAll().stream().filter(memberVO -> memberVO.getId().equals(id)).findFirst().orElse(null);
    }
}
