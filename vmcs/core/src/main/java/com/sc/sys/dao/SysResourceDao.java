package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.vo.SysResourceSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:  系统资源
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysResourceDao extends BaseDao<SysResource, SysResourceSearchVO> {
    public static String SAVE_FIELD = "resourceName,resourceCode,resourceParentId,resourceWebUrl,resourceManagerUrl";
    public static String SAVE_VALUE_FIELD = ":resourceName,:resourceCode,:resourceParentId,:resourceWebUrl,:resourceManagerUrl";
    public static String SELECT_FIELD = "id,resourceName,resourceCode,resourceParentId,resourceWebUrl,resourceManagerUrl";
    public static String UPDATE_FIELD = "resourceName=:resourceName,resourceCode=:resourceCode,resourceParentId=:resourceParentId,resourceWebUrl=:resourceWebUrl,resourceManagerUrl=:resourceManagerUrl";

    /**
     * 添加
     */
    public int save(SysResource sysResource) {
        String sql = "insert into td_sys_resources (" + SAVE_FIELD + ") values (" + SAVE_VALUE_FIELD + ")";
        return insert(sql, sysResource);
    }

    /**
     * 根据id获取
     */
    public SysResource getById(Integer id) {
        String sql = "select  " + SELECT_FIELD + " from td_sys_resources where id=?";
        return get(sql, id);
    }

    /**
     * 根据id删除
     */
    public int deleteById(Integer id) {
        String sql = "delete from td_sys_resources where id=?";
        return delete(sql, id);
    }

    /**
     * 获取全部
     */
    public List<SysResource> listAll() {
        String sql = "select " + SELECT_FIELD + " from td_sys_resources";
        return list(sql);
    }

    /**
     * 修改资源
     */
    public int updateById(SysResource sysResource) {
        String sql = "update td_sys_resources set " + UPDATE_FIELD + " where id=:id";
        return update(sql, sysResource);
    }

    /**
     * 根据资源代码获取
     */
    public SysResource getByResourceCode(String resourceCode) {
        String sql = "select " + SELECT_FIELD + " from td_sys_resources where resourceCode=?";
        return get(sql, resourceCode);
    }

}
