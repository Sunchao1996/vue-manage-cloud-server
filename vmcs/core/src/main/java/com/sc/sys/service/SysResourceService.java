package com.sc.sys.service;

import com.sc.sys.dao.SysResourceDao;
import com.sc.sys.dao.SysRoleResourceDao;
import com.sc.sys.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:  系统资源
 *
 * @author 孙超 created on 2018/11/8
 */
@Service
public class SysResourceService {
    @Autowired
    private SysResourceDao sysResourceDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 添加
     */
    public int save(SysResource sysResource) {
        return sysResourceDao.save(sysResource);
    }

    /**
     * 根据id获取
     */
    public SysResource getById(Integer id) {
        return sysResourceDao.getById(id);
    }

    /**
     * 根据id删除
     */
    public int deleteById(Integer id) {
        int flag = sysRoleResourceDao.deleteByResourceId(id);
        flag += sysResourceDao.deleteById(id);
        return flag;
    }

    /**
     * 获取全部
     */
    public List<SysResource> listAll() {
        return sysResourceDao.listAll();
    }

    /**
     * 修改角色
     */
    public int updateById(SysResource sysResource) {
        return sysResourceDao.updateById(sysResource);
    }

    /**
     * 根据资源代码获取
     */
    public SysResource getByResourceCode(String resourceCode) {
        return sysResourceDao.getByResourceCode(resourceCode);
    }

}
