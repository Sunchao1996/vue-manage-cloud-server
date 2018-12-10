package com.sc.api.config.interceptor;

import com.sc.util.code.EnumReturnCode;
import com.sc.util.code.GlobalCode;
import com.sc.util.json.JsonResult;
import com.sc.util.json.JsonUtil;
import com.sc.util.string.StringUtil;
import com.sc.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验版本
 * Created by 孔垂云 on 2017/8/18.
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    /**
     * 操作前先判断是否登录，未登录返回未登陆，跳转到登陆页面
     * header或request里面只要包含Authorization即可
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = WebUtil.getSafeStr(request.getHeader("X-Token"));//token
        if (StringUtil.isNullOrEmpty(token)) {
            JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_NOLOGIN);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());//状态设置为错误
            WebUtil.out(response, JsonUtil.toStr(jsonResult));
            return false;
        } else {
            return true;
        }
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
