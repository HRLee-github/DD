package com.example.bo.config;

import com.example.bo.api.member.model.MemberVO;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;

@Configuration
public class ServletContextConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute("memberList", new ArrayList<MemberVO>() {{
            add(MemberVO.builder().id("test1").pw("test1").nm("테스트1").build());
            add(MemberVO.builder().id("test3").pw("test3").nm("테스트3").build());
            add(MemberVO.builder().id("test4").pw("test4").nm("테스트4").build());
        }});
    }
}
