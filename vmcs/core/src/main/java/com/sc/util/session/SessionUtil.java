package com.sc.util.session;

import com.sc.core.spring.SpringContextHolder;
import com.sc.util.redis.RedisKey;
import com.sc.util.redis.RedisUtil;
import com.sc.util.string.StringUtil;
import com.sc.util.web.WebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类，用于获取用户session
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class SessionUtil {

    /**
     * 获取管理端用户的session信息
     *
     * @param request
     * @return
     */
    public static WebSession getWebSession(HttpServletRequest request) {
        //X-Token,如果没有，则从request中获取
        String authorization = WebUtil.getSafeStr(request.getHeader("X-Token"));
        if (StringUtil.isNotNullOrEmpty(authorization)) {
            RedisUtil redisUtil = (RedisUtil) SpringContextHolder.getBean("redisUtil");
            return redisUtil.get(RedisKey.WEBSESSION + authorization, WebSession.class);
        } else {
            return null;
        }
    }
}
