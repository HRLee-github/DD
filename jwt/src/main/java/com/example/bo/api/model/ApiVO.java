package com.example.bo.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ApiVO<T> implements Serializable {
    private static final long serialVersionUID = 1788167933457415944L;

    //메시지
    private String msg;

    //데이터
    private T data;
}