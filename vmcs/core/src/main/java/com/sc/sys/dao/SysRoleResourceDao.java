package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.model.SysRolesResources;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * what:    角色资源关系
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysRoleResourceDao extends BaseDao<SysRolesResources, SysRolesResources> {
    public static String RESOURCE_BASE_FIELD = "resource.id,resource.resourceName,resource.resourceCode,resource.resourceParentId,resource.resourceWebUrl,resource.resourceManagerUrl";

    /**
     * 根据角色id获取所有资源
     */
    public List<SysResource> listResourcesByRoleId(Integer roleId) {
        String sql = "select " + RESOURCE_BASE_FIELD + " from td_sys_resources resource,td_sys_roles_resources rr where rr.roleId=? and rr.resourceId=resource.id";
        return jdbcTemplate.query(sql, new Object[]{roleId}, BeanPropertyRowMapper.newInstance(SysResource.class));
    }

    /**
     * 根据角色id删除所有资源对应关系
     */
    public int deleteByRoleId(Integer roleId) {
        String sql = "delete from td_sys_roles_resources where roleId=?";
        return delete(sql, roleId);
    }

    /**
     * 批量插入
     *
     * @param sysRolesResourcesList
     */
    public int batchAdd(final List<SysRolesResources> sysRolesResourcesList) {
        String sql = "insert into td_sys_roles_resources (roleId,resourceId) value(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SysRolesResources sysRolesResources = sysRolesResourcesList.get(i);
                ps.setInt(1, sysRolesResources.getRoleId());
                ps.setInt(2, sysRolesResources.getResourceId());
            }

            @Override
            public int getBatchSize() {
                return sysRolesResourcesList.size();
            }
        });
        return 1;
    }

    /**
     * 删除对应资源id对应的记录
     */
    public int deleteByResourceId(Integer resourceId) {
        String sql = "delete from td_sys_roles_resources where resourceId=?";
        return delete(sql, resourceId);
    }
}
