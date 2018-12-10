package com.sc.api.config.interceptor;

import com.sc.util.date.DateUtil;
import com.sc.util.session.WebSession;
import com.sc.util.session.SessionUtil;
import com.sc.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   /* @Autowired
    private ResponseHolder responseHolder;*/

    /**
     * 记录操作日志
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验权限
        String path = request.getServletPath();
        String parameters = StringUtil.getBodyString(request.getReader());//参数
        WebSession webSession = SessionUtil.getWebSession(request);
        if (webSession == null) {
            webSession = new WebSession();
            webSession.setIp(StringUtil.getIp(request));
            webSession.setName("LOGIN");
        }
        logOperation(path, parameters, webSession);//记录日志
        return true;
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     * @param webSession
     */
    public void logOperation(String path, String parameters, WebSession webSession) {
        String log = "";
        if (webSession == null) webSession = new WebSession();
        log = "[OPERALOG-操作日志]" + "-[" + webSession.getIp() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + webSession.getName() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) throws Exception {
    }


}
