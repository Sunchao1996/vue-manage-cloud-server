package com.sc.api.sys.controller;

import com.sc.sys.model.SysRole;
import com.sc.sys.service.SysRoleService;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * what:    角色管理
 *
 * @author 孙超 created on 2018/11/10
 */
@RestController
@RequestMapping("/sys/roles")
@Validated
@PreAuthorize("hasAuthority('admin')")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表查询
     */
    @GetMapping
    public JsonResult list() {
        List<SysRole> list = sysRoleService.listAll();
        if (list == null) {
            list = new ArrayList<>();
        }
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, list);
    }

    /**
     * 添加角色
     */
    @PostMapping
    public JsonResult add(@RequestBody @Valid SysRole sysRole) {
        int flag = sysRoleService.save(sysRole);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }

    }

    /**
     * 根据代码验证
     */
    @GetMapping("/code/{roleCode}")
    public JsonResult checkRoleCode(@PathVariable String roleCode) {
        SysRole sysRole = sysRoleService.getByRoleCode(roleCode);
        if (sysRole == null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, true);
        } else {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, false);
        }
    }

    /**
     * 根据id删除
     */
    @DeleteMapping
    public JsonResult delete(@RequestBody Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.get("roleId") == null) {
            return new JsonResult(EnumReturnCode.FAIL_ARGS);
        }
        int flag = sysRoleService.deleteById(Integer.valueOf(paramsMap.get("roleId")));
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    /**
     * 根据id获得数据
     */
    @GetMapping("/{id}")
    public JsonResult get(@PathVariable String id) {
        SysRole sysRole = sysRoleService.getById(Integer.valueOf(id));
        if (sysRole != null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, sysRole);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_INFO_GET);
        }
    }

    /**
     * 修改角色
     */
    @PutMapping
    public JsonResult update(@RequestBody @Valid SysRole sysRole) {
        int flag = sysRoleService.updateById(sysRole);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

}
