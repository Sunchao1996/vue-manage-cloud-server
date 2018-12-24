package com.sc.api.config.interceptor;

import com.sc.api.security.utils.SecurityUtils;
import com.sc.sys.service.SysUserService;
import com.sc.util.date.DateUtil;
import com.sc.util.session.WebSession;
import com.sc.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录请求日志，和请求参数
 * Created by 孔垂云 on 2017/9/10.
 */
@Component
public class LogRequestInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger("operationLog");

    /**
     * 记录操作日志
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验权限
        String path = request.getServletPath();
        String parameters = CheckAuthorizationInterceptor.getRequestParams(request);//参数
        UserDetails userDetails = SecurityUtils.getUserDetails();
        if (userDetails == null) {
            userDetails = new WebSession();
            ((WebSession) userDetails).setName("LOGIN");
        }
        logOperation(request, path, parameters, userDetails);//记录日志
        return true;
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     */
    public void logOperation(HttpServletRequest request, String path, String parameters, UserDetails userDetails) {
        String log = "";
        String ip = StringUtil.getIp(request);
        log = "[OPERALOG-操作日志]" + "-[" + ip + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + userDetails.getUsername() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) throws Exception {
    }


}
