package com.sc.sys.service;

import com.sc.sys.dao.SysRoleDao;
import com.sc.sys.dao.SysRoleResourceDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysRolesResources;
import com.sc.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * what:
 *
 * @author 孙超 created on 2018/11/8
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 新增角色
     */
    @Transactional
    public int save(SysRole sysRole) {
        Integer roleId = sysRoleDao.save(sysRole);
        if (roleId == null || roleId == 0) {
            return 0;
        }
        int flag = sysRoleResourceDao.batchAdd(createRolesResourcesList(roleId, sysRole.getResourcesIds()));
        return flag;
    }

    /**
     * 修改角色
     */
    public int updateById(SysRole sysRole) {
        int flag = sysRoleDao.updateById(sysRole);
        flag += sysRoleResourceDao.deleteByRoleId(sysRole.getId());
        flag += sysRoleResourceDao.batchAdd(createRolesResourcesList(sysRole.getId(), sysRole.getResourcesIds()));
        return flag;
    }

    /**
     * 构成RolesResources对象
     */
    public List<SysRolesResources> createRolesResourcesList(Integer roleId, String resources) {
        String[] resourceArray = resources.split("@");
        List<SysRolesResources> list = new ArrayList<>();
        if (resourceArray.length == 1 && StringUtil.isNullOrEmpty(resourceArray[0])) {
            return new ArrayList<>();
        }
        for (String temp : resourceArray) {
            SysRolesResources sysRolesResources = new SysRolesResources();
            sysRolesResources.setRoleId(roleId);
            sysRolesResources.setResourceId(Integer.valueOf(temp));
            list.add(sysRolesResources);
        }
        return list;
    }

    /**
     * 删除角色
     */
    public int deleteById(Integer id) {
        int flag = sysRoleDao.deleteById(id);
        flag += sysRoleResourceDao.deleteByRoleId(id);
        return flag;
    }

    /**
     * 查询所有
     */
    public List<SysRole> listAll() {
        return sysRoleDao.listAll();
    }

    /**
     * 根据id查询
     */
    public SysRole getById(Integer id) {
        SysRole sysRole = sysRoleDao.getById(id);
        List<SysResource> resourceList = sysRoleResourceDao.listResourcesByRoleId(id);
        StringBuffer resourcesIdsBuf = new StringBuffer();
        for (SysResource sysResource : resourceList) {
            resourcesIdsBuf.append(sysResource.getId());
            resourcesIdsBuf.append("@");
        }
        sysRole.setResourcesIds(resourcesIdsBuf.toString());
        return sysRole;
    }

    /**
     * 根据角色代码获取
     */
    public SysRole getByRoleCode(String roleCode) {
        return sysRoleDao.getByRoleCode(roleCode);
    }
}
