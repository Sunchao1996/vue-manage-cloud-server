package com.sc.api.config.interceptor;

import com.sc.api.config.filter.BodyReaderHttpServletRequestWrapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (StringUtil.isNullOrEmpty(request.getHeader("X-Token")) || SessionUtil.getWebSession(request) == null) {
            JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_INTERCEPTOR1);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
            WebUtil.out(response, JsonUtil.toStr(jsonResult));
            return false;
        } else {
            //判断token是否一致
            //校验权限
            String path = request.getServletPath();
            String parameters = "";//参数
            if (request.getMethod().equals("GET")) {
                parameters = StringUtil.getOperaParams(request);
            } else {
                ServletInputStream streams = null;
                if (request instanceof HttpServletRequest) {
                    streams = ((BodyReaderHttpServletRequestWrapper) request).getInputStream();
                } else {
                    streams = request.getInputStream();
                }
                StringBuilder content = new StringBuilder();
                byte[] b = new byte[1024];
                int lens = -1;
                while ((lens = streams.read(b)) > 0) {
                    content.append(new String(b, 0, lens));
                }
                parameters = content.toString();
            }
            WebSession webSession = SessionUtil.getWebSession(request);
            String requestIp = StringUtil.getIp(request);
            if (!requestIp.equals(webSession.getIp()) && !request.getHeader("X-Token").equals(webSession.getToken())) {
                JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_INTERCEPTOR2);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
                WebUtil.out(response, JsonUtil.toStr(jsonResult));
                return false;
            }
            logOperation(path, parameters, webSession);

            //如果用户被锁定，直接踢出
            SysUser sysUser = sysUserService.getById(webSession.getUserId());
            if (sysUser.getUserStatus() == 1) {
                JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_USER_LOCK);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
                WebUtil.out(response, JsonUtil.toStr(jsonResult));
                return false;
            } else {
                //請求路徑不再允许范围之内
                String manageUrl = webSession.getManageUrl();
                if (manageUrl.indexOf(path) == -1 && checkUrl(path.substring(path.lastIndexOf("/") + 1))) {
                    JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_NOAUTH);
                    jsonResult.setMsg("无权限访问，请联系管理员！");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());//状态设置为未授权
                    WebUtil.out(response, JsonUtil.toStr(jsonResult));
                    return false;
                }
                sysLogService.addLog(webSession.getUserId(), path, parameters, webSession.getIp());
                return true;
            }
        }
    }

    private static boolean checkUrl(String url) {
        Pattern pattern = Pattern.compile("^(add|update|delete|save|import).*");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     * @param manageSession
     */
    public void logOperation(String path, String parameters, WebSession manageSession) {
        String log = "";
        log = "[OPERALOG-操作日志]" + "-[" + manageSession.getIp() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[userName:" + manageSession.getName() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
