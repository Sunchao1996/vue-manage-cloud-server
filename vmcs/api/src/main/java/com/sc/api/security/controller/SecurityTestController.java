package com.sc.api.security.controller;

import com.sc.api.config.security.ApiRedisTokenStore;
import com.sc.sys.model.SysUser;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * what:
 *
 * @author 孙超 created on 2018/12/21
 */
@RestController
@RequestMapping("/security")
@PreAuthorize("hasAuthority('admin')")
public class SecurityTestController {
    @RequestMapping("/test")
    public JsonResult test(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int i=1;
        return new JsonResult(EnumReturnCode.SUCCESS_OPERA,authentication);
    }
    @PutMapping
    public JsonResult putTest(@RequestBody SysUser sysUser){
        return new JsonResult("1",null,sysUser);
    }
}
