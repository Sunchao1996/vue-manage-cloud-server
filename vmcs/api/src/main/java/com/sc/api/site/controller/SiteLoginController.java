package com.sc.api.site.controller;

import com.sc.api.site.dto.UserInfoLoginDto;
import com.sc.api.site.service.SiteLoginService;
import com.sc.sys.model.SysUser;
import com.sc.sys.service.SysUserService;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.code.GlobalCode;
import com.sc.util.code.ReturnCodeUtil;
import com.sc.util.json.JsonResult;
import com.sc.util.redis.RedisKey;
import com.sc.util.redis.RedisUtil;
import com.sc.util.session.WebSession;
import com.sc.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by 孔垂云 on 2017/9/2.
 */
@RequestMapping("/login")
@RestController
@Validated
public class SiteLoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SiteLoginService siteLoginService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 校验登录
     *
     * @param userInfoLoginDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult checkLogin(HttpServletRequest request, @Valid @RequestBody UserInfoLoginDto userInfoLoginDto) {
        boolean flag = false;
        SysUser sysUser = sysUserService.getByUserName(userInfoLoginDto.getUsername());
        if (sysUser != null) {
            int status = sysUser.getUserStatus();
            if (status == 1) {
                return new JsonResult(EnumReturnCode.FAIL_USER_LOCK);
            }
            flag = sysUserService.checkPass(sysUser, userInfoLoginDto.getPassword());
        }
        if (flag) {
            sysUser.setUserIp(StringUtil.getIp(request));//设备id
            WebSession webSession = siteLoginService.setWebSession(sysUser, request, RedisKey.WEB_TOKEN_TIMEOUT);
            return new JsonResult(EnumReturnCode.SUCCESS_LOGIN, webSession);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_LOGIN_ERROR);
        }
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public JsonResult checkLogin(HttpServletRequest request) {
        redisUtil.del(RedisKey.WEBSESSION + request.getHeader("X-Token"));
        return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
    }
}
