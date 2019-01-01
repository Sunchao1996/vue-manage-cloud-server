package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysUser;
import com.sc.sys.model.SysUsersRoles;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * what:  用户角色关系
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysUserRoleDao extends BaseDao<SysUsersRoles, SysUsersRoles> {
    public static String ROLE_BASE_FIELD = "role.id,role.roleName,role.roleCode,role.roleStatus,role.createTime";

    /**
     * 根据用户id获取所有角色
     */
    public List<SysRole> listRolesByUserId(Integer userId) {
//        String sql = "select " + ROLE_BASE_FIELD + " from td_sys_roles role,td_sys_users_roles ur where ur.userId=? and ur.roleId=role.id and role.roleStatus=0";
        String sql = "select "+ROLE_BASE_FIELD+" from td_sys_roles role where role.roleStatus=0 and role.id in " +
                "(select roleId from td_sys_roles_group where groupRoleId in (select roleId from td_sys_users_roles  ur where userId=?))";
        return jdbcTemplate.query(sql, new Object[]{userId}, BeanPropertyRowMapper.newInstance(SysRole.class));
    }


    /**
     * 根据用户id删除角色对应关系
     */
    public int deleteByUserId(Integer userId) {
        String sql = "delete from td_sys_users_roles where userId=?";
        return delete(sql, userId);
    }

    /**
     * 批量插入
     *
     * @param sysUsersRolesList
     */
    public int batchAdd(final List<SysUsersRoles> sysUsersRolesList) {
        String sql = "insert into td_sys_users_roles (userId,roleId) value(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SysUsersRoles sysUsersRoles = sysUsersRolesList.get(i);
                ps.setInt(1, sysUsersRoles.getUserId());
                ps.setInt(2, sysUsersRoles.getRoleId());
            }

            @Override
            public int getBatchSize() {
                return sysUsersRolesList.size();
            }
        });
        return 1;
    }
}
