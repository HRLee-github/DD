package com.example.bo.api.member.service;

import com.example.bo.api.member.model.MemberVO;

import java.util.List;

public interface  MemberService {
    List<MemberVO> findAll();

    MemberVO findById(String id);
}
