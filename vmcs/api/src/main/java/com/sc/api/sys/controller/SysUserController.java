package com.sc.api.sys.controller;

import com.sc.api.site.service.SiteLoginService;
import com.sc.common.page.PageDto;
import com.sc.sys.model.SysPwd;
import com.sc.sys.model.SysUser;
import com.sc.sys.service.SysUserService;
import com.sc.sys.vo.SysUserSearchVO;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import com.sc.util.session.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * what:  用户信息
 *
 * @author 孙超 created on 2018/11/8
 */
@RestController
@RequestMapping("/sys/users")
public class SysUserController {
    @Autowired
    private SiteLoginService siteLoginService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/info")
    @PreAuthorize("permitAll()")
    public JsonResult info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails object = (UserDetails) authentication.getPrincipal();
        System.out.println(object instanceof WebSession);
        SysUser sysUser = sysUserService.getByUserName(object.getUsername());
        WebSession webSession = new WebSession();
        if (sysUser == null) {
            return new JsonResult(EnumReturnCode.FAIL_INFO_GET);
        }
        siteLoginService.setWebSession(sysUser, webSession);
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, webSession);
    }

    /**
     * 根据条件查询
     */
    @GetMapping
    @PreAuthorize("hasAuthority('users')")
    public JsonResult list(  SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserService.listBySearch(sysUserSearchVO);
        Integer count = sysUserService.count(sysUserSearchVO);
        PageDto pageDto = new PageDto(sysUserSearchVO.getPageIndex(), count, list);
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, pageDto);
    }

    /**
     * 根据id修改状态
     */
    @PutMapping("/status/{usid}")
    @PreAuthorize("hasAuthority('usersstatus')")
    public JsonResult updateStatus(@PathVariable String usid) {
        int flag = sysUserService.updateStatus(Integer.valueOf(usid));
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 根据id重置密码为123456
     */
    @PutMapping(value = "/password/{usid}")
    @PreAuthorize("hasAuthority('usersupdate')")
    public JsonResult updateResetPwd(@PathVariable String usid) {
        int flag = sysUserService.updateResetPwd(Integer.valueOf(usid));
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 检验用户名是否可用
     */
    @GetMapping(value = "/code/{userName}")
    @PreAuthorize("permitAll()")
    public JsonResult checkUserName(@PathVariable String userName) {
        SysUser sysUser = sysUserService.getByUserName(userName);
        if (sysUser != null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, false);
        } else {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, true);
        }
    }

    /**
     * 新增用户
     */
    @PostMapping
    @PreAuthorize("hasAuthority('usersadd')")
    public JsonResult save(@RequestBody SysUser sysUser) {
        int flag = sysUserService.save(sysUser);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 根据用户id删除用户
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('usersdelete')")
    public JsonResult delete(@PathVariable Integer id) {
        int flag = sysUserService.deleteById(id);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    @PreAuthorize("hasAuthority('usersupdate')")
    public JsonResult updateUser(@RequestBody SysUser sysUser) {
        int flag = sysUserService.updateById(sysUser);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 根据用户id获取用户信息
     */
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('usersupdate')")
    public JsonResult getById(@PathVariable Integer id) {
        SysUser getObj = sysUserService.getById(id);
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, getObj);
    }

    /**
     * 用户修改密码
     *
     * @param sysPwd
     * @return
     */
    @PutMapping(value = "/password")
    @PreAuthorize("hasAuthority('userspassword')")
    public JsonResult updateUserPwd(@RequestBody SysPwd sysPwd) {
        int flag = sysUserService.updateUserPwd(sysPwd);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }
}
