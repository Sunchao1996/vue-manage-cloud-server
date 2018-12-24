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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * what:  用户信息
 *
 * @author 孙超 created on 2018/11/8
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SiteLoginService siteLoginService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public JsonResult info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails object = (UserDetails) authentication.getPrincipal();
//        System.out.println(authentication.getPrincipal());
//        WebSession webSession = (WebSession) object;
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
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public JsonResult list(@RequestBody SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserService.listBySearch(sysUserSearchVO);
        Integer count = sysUserService.count(sysUserSearchVO);
        PageDto pageDto = new PageDto(sysUserSearchVO.getPageIndex(), count, list);
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, pageDto);
    }

    /**
     * 根据id修改状态
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public JsonResult updateStatus(@RequestBody Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.get("usid") == null) {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
        int flag = sysUserService.updateStatus(Integer.valueOf(paramsMap.get("usid")));
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 根据id重置密码为123456
     */
    @RequestMapping(value = "/updateResetPwd", method = RequestMethod.POST)
    public JsonResult updateResetPwd(@RequestBody Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.get("usid") == null) {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
        int flag = sysUserService.updateResetPwd(Integer.valueOf(paramsMap.get("usid")));
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 检验用户名是否可用
     */
    @RequestMapping(value = "/checkUserCode", method = RequestMethod.POST)
    public JsonResult checkUserName(@RequestBody SysUserSearchVO sysUserSearchVO) {
        SysUser sysUser = sysUserService.getByUserName(sysUserSearchVO.getUserName());
        if (sysUser != null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, false);
        } else {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, true);
        }
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
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
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public JsonResult delete(@RequestBody SysUser sysUser) {
        int flag = sysUserService.deleteById(sysUser.getId());
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
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
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public JsonResult getById(@RequestBody SysUser sysUser) {
        SysUser getObj = sysUserService.getById(sysUser.getId());
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, getObj);
    }

    /**
     * 用户修改密码
     *
     * @param sysPwd
     * @return
     */
    @RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST)
    public JsonResult updateUserPwd(@RequestBody SysPwd sysPwd) {
        int flag = sysUserService.updateUserPwd(sysPwd);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }
}
