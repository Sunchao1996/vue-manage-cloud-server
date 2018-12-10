package com.sc.api.site.service;

import com.sc.sys.dao.SysRoleResourceDao;
import com.sc.sys.dao.SysUserDao;
import com.sc.sys.dao.SysUserRoleDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysUser;
import com.sc.sys.service.SysUserLoginService;
import com.sc.util.redis.RedisKey;
import com.sc.util.redis.RedisUtil;
import com.sc.util.session.WebSession;
import com.sc.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by 孔垂云 on 2017/10/30.
 */
@Service
public class SiteLoginService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysUserLoginService sysUserLoginService;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 给appSession设值
     *
     * @param
     * @return
     */
    public WebSession setWebSession(SysUser sysUser, HttpServletRequest request, int tokenTimeout) {
        String uuid = UUID.randomUUID().toString();
        WebSession webSession = new WebSession();
//        private String[] roles;
//        private String resources;//用@分隔
        webSession.setUserId(sysUser.getId());
        webSession.setIp(sysUser.getUserIp());//user_ip
        webSession.setIntroduction(sysUser.getUserIntroduction());
        webSession.setAvatar(sysUser.getUserAvatar());
        webSession.setName(sysUser.getUserName());
        webSession.setToken(uuid);
        webSession.setStatus(String.valueOf(sysUser.getUserStatus()));
        webSession.setUser(sysUser.getUserName());
        webSession.setUserRealName(sysUser.getUserRealName());
        //获取所有角色
        List<SysRole> roleList = sysUserRoleDao.listRolesByUserId(sysUser.getId());
        StringBuffer roles = new StringBuffer();
        StringBuffer resources = new StringBuffer();
        StringBuffer manageUrl = new StringBuffer();
        StringBuffer roleName = new StringBuffer();
        for (SysRole roleTemp : roleList) {
            roles.append(roleTemp.getRoleCode());
            roles.append("@");
            roleName.append(roleTemp.getRoleName());
            roleName.append("@");
            //获取所有资源
            List<SysResource> resourceList = sysRoleResourceDao.listResourcesByRoleId(roleTemp.getId());
            for (SysResource resourceTemp : resourceList) {
                resources.append(resourceTemp.getResourceCode());
                resources.append("@");
                if (StringUtil.isNotNullOrEmpty(resourceTemp.getResourceManagerUrl())) {
                    manageUrl.append(resourceTemp.getResourceManagerUrl());
                    manageUrl.append("@");
                }
            }
        }
        webSession.setRoleName(roleName.toString());
        webSession.setRoles(roles.toString().split("@"));
        webSession.setResources(resources.toString());
        webSession.setManageUrl(manageUrl.toString());
        redisUtil.set(RedisKey.WEBSESSION + uuid, webSession, tokenTimeout);//把登录的人员信息生成uuid，放入到redis里面，作为token使用

        // 插入登录日志
        sysUserLoginService.addLoginLog(webSession, request, uuid);
        return webSession;
    }
}
