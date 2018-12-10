package com.sc.sys.service;

import com.sc.sys.dao.SysUserDao;
import com.sc.sys.dao.SysUserLoginDao;
import com.sc.sys.model.SysUser;
import com.sc.sys.model.SysUserLogin;
import com.sc.sys.vo.SysUserloginSearchVO;
import com.sc.util.redis.RedisKey;
import com.sc.util.redis.RedisUtil;
import com.sc.util.session.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统登录Service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
@Configuration
@EnableAsync
public class SysUserLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RedisUtil redisUtil;

    public void addLoginLog(WebSession webSession, HttpServletRequest request, String uuid) {
        SysUserLogin sysUserLogin = new SysUserLogin();
        sysUserLogin.setUserId(webSession.getUserId());
        sysUserLogin.setLoginIp(webSession.getIp());
        sysUserLogin.setTerminal(webSession.getToken());
        sysUserLogin.setExplorerType("");
        sysUserLogin.setExplorerVersion("");
        sysUserLoginDao.add(sysUserLogin);
        //删除原有websession
        SysUser oldUser = sysUserDao.getById(webSession.getUserId());
        redisUtil.del(RedisKey.WEBSESSION + oldUser.getUserToken());
        sysUserDao.updateToken(webSession.getUserId(), webSession.getToken());
    }
}
