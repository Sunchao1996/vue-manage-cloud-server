package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysRoleGroup;
import com.sc.sys.model.SysRolesResources;
import com.sc.sys.vo.SysRoleSearchVO;
import com.sc.util.page.PageUtil;
import com.sc.util.string.StringUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * what:    系统角色
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysRoleDao extends BaseDao<SysRole, SysRoleSearchVO> {
    public static String BASE_FIELD = "id,roleName,roleCode,roleStatus,createTime,roleType";
    public static String INSERT_FIELD = "roleName,roleCode,roleStatus,createTime,roleType";
    public static String INSERT_VALUES_FIELD = ":roleName,:roleCode,:roleStatus,now(),:roleType";
    public static String UPDATE_FIELD = "roleName=:roleName,roleCode=:roleCode,roleStatus=:roleStatus";

    /**
     * 新增角色
     */
    public Integer save(SysRole sysRole) {
        String sql = "insert into td_sys_roles (" + INSERT_FIELD + ") values (" + INSERT_VALUES_FIELD + ")";
        return insertForId(sql, sysRole, "id");
    }

    /**
     * 修改角色
     */
    public int updateById(SysRole sysRole) {
        String sql = "update td_sys_roles set  " + UPDATE_FIELD + " where id=:id";
        return update(sql, sysRole);
    }

    /**
     * 删除角色
     */
    public int deleteById(Integer id) {
        String sql = "delete from td_sys_roles where id=?";
        return delete(sql, id);
    }

    /**
     * 查询所有普通角色
     */
    public List<SysRole> listAllRoles() {
        String sql = "select " + BASE_FIELD + "  from td_sys_roles where roleType=1 ";
        return list(sql);
    }

    /**
     * 查询角色组所有角色
     */
    public List<SysRole> listAllGroup() {
        String sql = "select " + BASE_FIELD + "  from td_sys_roles where roleType=2 ";
        return list(sql);
    }

    /**
     * 获取角色组所有角色
     */
    public List<SysRole> listAllGroupRoles(Integer roleId) {
        String sql = "select " + BASE_FIELD + " from td_sys_roles where id in (select roleId from td_sys_roles_group where groupRoleId=?)";
        return list(sql, roleId);
    }

    /**
     * 根据id查询
     */
    public SysRole getById(Integer id) {
        String sql = "select  " + BASE_FIELD + " from td_sys_roles where id=?";
        return get(sql, id);
    }

    /**
     * 根据角色代码获取
     */
    public SysRole getByRoleCode(String roleCode) {
        String sql = "select " + BASE_FIELD + " from td_sys_roles where roleCode=?";
        return get(sql, roleCode);
    }

    /**
     * 批量插入角色组角色
     *
     * @param sysRoleGroups
     */
    public int batchAdd(final List<SysRoleGroup> sysRoleGroups) {
        String sql = "insert into td_sys_roles_group (groupRoleId,roleId) value(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SysRoleGroup sysRoleGroup = sysRoleGroups.get(i);
                ps.setInt(1, sysRoleGroup.getGroupRoleId());
                ps.setInt(2, sysRoleGroup.getRoleId());
            }

            @Override
            public int getBatchSize() {
                return sysRoleGroups.size();
            }
        });
        return 1;
    }

    /**
     * 删除角色组所有角色
     */
    public int deleteGroupRole(Integer groupRoleId) {
        String sql = "delete from td_sys_roles_group where groupRoleId=?";
        return delete(sql, groupRoleId);
    }

    /**
     * 根据条件查询角色组信息
     *
     * @param sysRoleSearchVO
     * @return
     */
    public List<SysRole> listGroupBySearch(SysRoleSearchVO sysRoleSearchVO) {
        String sql = "select " + BASE_FIELD + " from td_sys_roles where roleType=2 ";
        sql += createGroupSearch(sysRoleSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, sysRoleSearchVO.getPageIndex(), sysRoleSearchVO.getPageSize());
        return list(sql, sysRoleSearchVO);
    }

    private String createGroupSearch(SysRoleSearchVO sysRoleSearchVO) {
        String search = "";
        if (StringUtil.isNotNullOrEmpty(sysRoleSearchVO.getRoleCode())) {
            search += " and roleCode =:roleCode ";
        }
        if (StringUtil.isNotNullOrEmpty(sysRoleSearchVO.getRoleName())) {
            search += " and roleName like :roleNameLike ";
        }
        if (sysRoleSearchVO.getRoleStatus() != null) {
            search += " and roleStatus =:roleStatus ";
        }
        search += " order by createtime desc";
        return search;
    }

    /**
     * 统计角色组数量
     */
    public int countGroupBySearch(SysRoleSearchVO sysRoleSearchVO) {
        String sql = "select count(*) from td_sys_roles where roleType=2 ";
        sql += createGroupSearch(sysRoleSearchVO);
        return count(sql,sysRoleSearchVO);
    }
}
