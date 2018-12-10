package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysRole;
import com.sc.sys.vo.SysRoleSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    系统角色
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysRoleDao extends BaseDao<SysRole, SysRoleSearchVO> {
    public static String BASE_FIELD = "id,roleName,roleCode,roleStatus,createTime";
    public static String INSERT_FIELD = "roleName,roleCode,roleStatus,createTime";
    public static String INSERT_VALUES_FIELD = ":roleName,:roleCode,:roleStatus,now()";
    public static String UPDATE_FIELD = "roleName=:roleName,roleCode=:roleCode,roleStatus=:roleStatus";

    /**
     * 新增角色
     */
    public Integer save(SysRole sysRole) {
        String sql = "insert into td_sys_roles (" + INSERT_FIELD + ") values (" + INSERT_VALUES_FIELD + ")";
        return insertForId(sql, sysRole,"id");
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
     * 查询所有
     */
    public List<SysRole> listAll() {
        String sql = "select " + BASE_FIELD + "  from td_sys_roles";
        return list(sql);
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
}
