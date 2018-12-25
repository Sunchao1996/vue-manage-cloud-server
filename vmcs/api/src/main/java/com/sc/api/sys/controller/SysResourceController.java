package com.sc.api.sys.controller;

import com.sc.sys.model.SysResource;
import com.sc.sys.service.SysResourceService;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * what:资源，id,所有路由，路由名称，路由代码，路由路径，父节点
 *
 * @author 孙超 created on 2018/11/6
 */
@RestController
@RequestMapping("/sys/resources")
@PreAuthorize("hasAuthority('admin')")
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping
    public JsonResult list() {
        List<SysResource> list = sysResourceService.listAll();
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, careateResources(list));
    }

    @GetMapping("/{id}")
    public JsonResult get(@PathVariable Integer id) {
        SysResource sysResource = sysResourceService.getById(id);
        if (sysResource != null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, sysResource);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_INFO_GET);
        }
    }

    @PostMapping(produces = "application/json;charset=utf-8")
    public JsonResult add(@RequestBody SysResource resources) {
        int flag = sysResourceService.save(resources);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    @PutMapping
    public JsonResult update(@RequestBody SysResource resource) {
        int flag = sysResourceService.updateById(resource);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }

    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        int flag = sysResourceService.deleteById(id);
        if (flag > 0) {
            return new JsonResult(EnumReturnCode.SUCCESS_OPERA);
        } else {
            return new JsonResult(EnumReturnCode.FAIL_OPERA);
        }
    }


    @GetMapping("/code/{resourcesCode}")
    public JsonResult checkCode(@PathVariable String resourcesCode) {
        SysResource sysResource = sysResourceService.getByResourceCode(resourcesCode);
        if (sysResource == null) {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, true);
        } else {
            return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, false);
        }
    }

    /**
     * 构建资源管理菜单树
     *
     * @param resourcesList
     * @return
     */
    public List<SysResource> careateResources(List<SysResource> resourcesList) {
        // 最后的结果
        List<SysResource> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < resourcesList.size(); i++) {
            // 一级菜单没有parentId
            if (0 == resourcesList.get(i).getResourceParentId()) {
                menuList.add(resourcesList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (SysResource menu : menuList) {
            menu.setChildren(getChild(menu.getId(), resourcesList));
        }
        return menuList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<SysResource> getChild(Integer id, List<SysResource> rootMenu) {
        // 子菜单
        List<SysResource> childList = new ArrayList<>();
        for (SysResource menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getResourceParentId().equals(id)) {
                childList.add(menu);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (SysResource menu : childList) {// 没有url子菜单还有子菜单
            // 递归
            menu.setChildren(getChild(menu.getId(), rootMenu));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
