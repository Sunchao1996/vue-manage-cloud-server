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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

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
    private TokenStore tokenStore;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 校验登录
     *
     * @param userInfoLoginDto
     * @return
     */
    @PostMapping(value = "/login")
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
            //授权请求信息
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("client_id", Collections.singletonList("client"));
            map.put("client_secret", Collections.singletonList("123456"));
            map.put("username", Collections.singletonList(userInfoLoginDto.getUsername()));
            map.put("password", Collections.singletonList(userInfoLoginDto.getPassword()));
            map.put("grant_type", Collections.singletonList("password"));
            //HttpEntity
            HttpEntity httpEntity = new HttpEntity(map, null);
            //获取 Token
            try {
                ResponseEntity<OAuth2AccessToken> body = restTemplate.exchange("http://localhost:8080/oauth/token", HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
                OAuth2AccessToken oAuth2AccessToken = body.getBody();
                return new JsonResult(EnumReturnCode.SUCCESS_LOGIN, oAuth2AccessToken);
            } catch (Exception e) {
                e.printStackTrace();
                return new JsonResult(EnumReturnCode.FAIL_LOGIN_ERROR);
            }
        } else {
            return new JsonResult(EnumReturnCode.FAIL_LOGIN_ERROR);
        }
    }

    /**
     * 注销
     *
     * @return
     */
    @PostMapping(value = "/logout")
    public JsonResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        OAuth2AccessToken token = tokenStore.getAccessToken(oAuth2Authentication);
        tokenStore.removeAccessToken(token);
        return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
    }
}
