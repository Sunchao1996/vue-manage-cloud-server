package com.sc.api.config.interceptor;

import com.sc.api.config.filter.BodyReaderHttpServletRequestWrapper;
import com.sc.api.security.utils.SecurityUtils;
import com.sc.sys.model.SysUser;
import com.sc.sys.service.SysLogService;
import com.sc.sys.service.SysUserService;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.code.GlobalCode;
import com.sc.util.date.DateUtil;
import com.sc.util.json.JsonResult;
import com.sc.util.json.JsonUtil;
import com.sc.util.session.SessionUtil;
import com.sc.util.session.WebSession;
import com.sc.util.string.StringUtil;
import com.sc.util.web.WebUtil;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验权限
 * Created by 孔垂云 on 2017/8/18.
 */
@Component
public class CheckAuthorizationInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger("operationLog");
    private SysLogService sysLogService;
    private SysUserService sysUserService;

    public CheckAuthorizationInterceptor(SysUserService sysUserService, SysLogService sysLogService) {
        this.sysUserService = sysUserService;
        this.sysLogService = sysLogService;
    }

    /**
     * 操作前先判断是否登录，未登录跳转到登录界面
     * header或request里面只要包含Authorization即可
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtil.isNullOrEmpty(request.getHeader("Authorization")) || SessionUtil.getWebSession(request) == null) {
            JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_INTERCEPTOR1);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
            WebUtil.out(response, JsonUtil.toStr(jsonResult));
            return false;
        } else {
            //校验权限，记录请求日志
            String path = request.getServletPath();
            String parameters = "";//参数
            parameters = getRequestParams(request);
            logOperation(path, parameters, request);
            UserDetails userDetails = SecurityUtils.getUserDetails();
            if (userDetails == null) {
                JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_USER_LOCK);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
                WebUtil.out(response, JsonUtil.toStr(jsonResult));
                return false;
            }
            return true;
        }
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     */
    public void logOperation(String path, String parameters, HttpServletRequest request) {
        String ip = StringUtil.getIp(request);
        String username = SecurityUtils.getUserDetails().getUsername();
        String log = "";
        log = "[OPERALOG-操作日志]" + "-[" + ip + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[userName:" + username + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public static String getRequestParams(HttpServletRequest request) {
        String parameters = "";
        try {
            if (request.getMethod().equals("GET")) {
                parameters = StringUtil.getOperaParams(request);
            } else {
                ServletInputStream streams = null;
                streams = request.getInputStream();
                StringBuilder content = new StringBuilder();
                byte[] b = new byte[1024];
                int lens = -1;
                while ((lens = streams.read(b)) > 0) {
                    content.append(new String(b, 0, lens));
                }
                parameters = content.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameters;
    }
}
