package com.sc.sys.service;

import com.sc.sys.dao.SysRoleDao;
import com.sc.sys.dao.SysRoleResourceDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysRoleGroup;
import com.sc.sys.model.SysRolesResources;
import com.sc.sys.vo.SysRoleSearchVO;
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

    /**
     * 新增普通角色/角色组
     */
    @Transactional
    public int save(SysRole sysRole) {
        if (sysRole.getRoleType() == null) {
            return -1;
        }

        Integer roleId = sysRoleDao.save(sysRole);
        if (roleId == null || roleId == 0) {
            return 0;
        }
        if (sysRole.getRoleType() == 2) {
            int flag = sysRoleDao.batchAdd(createGroupRolesList(roleId, sysRole.getRoleIds()));
            return flag;
        } else {
            return roleId;
        }
    }

    /**
     * 修改普通角色/角色组
     */
    public int updateById(SysRole sysRole) {
        if (sysRole.getRoleType() == null) {
            sysRole.setRoleType(1);
        }
        int flag = sysRoleDao.updateById(sysRole);
        if (sysRole.getRoleType() == 2) {
            flag += sysRoleDao.deleteGroupRole(sysRole.getId());
            flag += sysRoleDao.batchAdd(createGroupRolesList(sysRole.getId(), sysRole.getRoleIds()));
        }
        return flag;
    }

    /**
     * 构成RolesGroup对象
     */
    public List<SysRoleGroup> createGroupRolesList(Integer roleId, String roleIds) {
        String[] roleArrays = roleIds.split("@");
        List<SysRoleGroup> list = new ArrayList<>();
        if (roleArrays.length == 1 && StringUtil.isNullOrEmpty(roleArrays[0])) {
            return new ArrayList<>();
        }
        for (String temp : roleArrays) {
            SysRoleGroup sysRoleGroup = new SysRoleGroup();
            sysRoleGroup.setRoleId(Integer.valueOf(temp));
            sysRoleGroup.setGroupRoleId(roleId);
            list.add(sysRoleGroup);
        }
        return list;
    }

    /**
     * 删除角色
     */
    public int deleteById(Integer id) {
        int flag = sysRoleDao.deleteById(id);
        flag += sysRoleDao.deleteGroupRole(id);
        return flag;
    }

    /**
     * 查询所有
     */
    public List<SysRole> list(Integer roleType) {
        if (roleType == null || roleType > 2) {
            roleType = 1;
        }
        if (roleType == 1) {
            return sysRoleDao.listAllRoles();
        } else {
            return sysRoleDao.listAllGroup();
        }
    }

    /**
     * 根据id查询
     */
    public SysRole getById(Integer id) {
        SysRole sysRole = sysRoleDao.getById(id);
        if (sysRole.getRoleType() == 1) {
            return sysRole;
        }
        List<SysRole> roleList = sysRoleDao.listAllGroupRoles(id);
        StringBuffer roleIdsBuf = new StringBuffer();
        for (SysRole sysRole1 : roleList) {
            roleIdsBuf.append(sysRole1.getId());
            roleIdsBuf.append("@");
        }
        sysRole.setRoleIds(roleIdsBuf.toString());
        return sysRole;
    }

    /**
     * 根据角色代码获取
     */
    public SysRole getByRoleCode(String roleCode) {
        return sysRoleDao.getByRoleCode(roleCode);
    }

    /**
     * 根据条件获取角色组数据
     *
     * @param sysRoleSearchVO
     * @return
     */
    public List<SysRole> groupList(SysRoleSearchVO sysRoleSearchVO) {
        List<SysRole> groupList = sysRoleDao.listGroupBySearch(sysRoleSearchVO);
        for (SysRole sysRole : groupList) {
            List<SysRole> subRoleList = sysRoleDao.listAllGroupRoles(sysRole.getId());
            sysRole.setSubRoles(subRoleList);
        }
        return groupList;
    }

    /**
     * 获取角色组数量
     */
    public int countGroupBySearch(SysRoleSearchVO sysRoleSearchVO) {
        return sysRoleDao.countGroupBySearch(sysRoleSearchVO);
    }
}
