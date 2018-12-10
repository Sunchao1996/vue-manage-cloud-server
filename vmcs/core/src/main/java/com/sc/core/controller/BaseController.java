package com.sc.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sc.util.json.JsonUtil;
import com.sc.util.string.StringUtil;
import com.sc.util.web.WebUtil;

/**
 * 基础Controller，用于异常处理
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        logger.error("请求地址：" + request.getServletPath());
        logger.error("请求参数：" + StringUtil.getOperaParams(request));
        logger.error("异常：" + ex.getMessage());
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toStr(msg));
            return null;
        } else {
            //URL请求处理
            Map<String, Object> map = new HashMap<>();
            map.put("message", ex.getMessage());
            map.put("isError", true);
            map.put("exceptionName", ex.getMessage());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("/common/exception");
            mv.addObject("message", ex.getMessage());
            return mv;
        }
    }


}
