package com.sc.api.config.security;

import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import com.sc.util.json.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ConsumterAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(JsonUtil.toStr(new JsonResult(EnumReturnCode.FAIL_AUTH)));
        response.getWriter().close();
        System.out.println(response.isCommitted());
    }
}
