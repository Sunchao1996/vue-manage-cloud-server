package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysUser;
import com.sc.sys.vo.SysUserSearchVO;
import com.sc.util.page.PageUtil;
import com.sc.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    系统用户
 *
 * @author 孙超 created on 2018/11/8
 */
@Repository
public class SysUserDao extends BaseDao<SysUser, SysUserSearchVO> {
    public static String INSERT_FIELD = "userName,userMobile,userRealName,userAvatar,userIntroduction,userStatus,userPassword,randomCode";
    public static String INSERT_VALUES_FIELD = ":userName,:userMobile,:userRealName,:userAvatar,:userIntroduction,:userStatus,:userPassword,:randomCode";
    public static String UPDATE_FIELD = "userMobile=:userMobile,userRealName=:userRealName,userAvatar=:userAvatar,userIntroduction=:userIntroduction,userStatus=:userStatus ";
    public static String BASE_FIELD = "id,userName,userMobile,userRealName,userAvatar,userIntroduction,userStatus,userPassword,randomCode";

    /**
     * 新增用户
     */
    public int save(SysUser sysUser) {
        String sql = "insert into td_sys_users (" + INSERT_FIELD + ") values (" + INSERT_VALUES_FIELD + ")";
        return insertForId(sql, sysUser,"id");
    }

    /**
     * 修改用户信息
     */
    public int updateById(SysUser sysUser) {
        String sql = "update td_sys_users set " + UPDATE_FIELD + " where id=:id";
        return update(sql, sysUser);
    }

    /**
     * 删除用户
     */
    public int deleteById(Integer id) {
        String sql = "delete from td_sys_users where id=?";
        return delete(sql, id);
    }

    /**
     * 修改用户状态
     */
    public int updateStatus(Integer userStatus, Integer userId) {
        String sql = "update td_sys_users set userStatus=? where id=?";
        return update(sql, userStatus, userId);
    }

    /**
     * 根据id查询
     */
    public SysUser getById(Integer id) {
        String sql = "select " + BASE_FIELD + " from td_sys_users where id=?";
        return get(sql, id);
    }

    /**
     * 查询所有用户
     */
    public List<SysUser> listAll() {
        String sql = "select " + BASE_FIELD + " from td_sys_users ";
        return list(sql);
    }

    /**
     * 根据用户名查询
     */
    public SysUser getByUserName(String userName) {
        String sql = "select " + BASE_FIELD + " from td_sys_users where userName=?";
        return get(sql, userName);
    }

    /**
     * 构造条件
     */
    private String createSearchSQL(SysUserSearchVO sysUserSearchVO) {
        String search = "";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUserName())) {
            search += " and userName like :userNameStr ";
        }
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUserRealName())) {
            search += " and userRealName like :userRealNameStr ";
        }
        if (sysUserSearchVO.getUserStatus() != null) {
            search += " and userStatus =:userStatus ";
        }
        return search;
    }

    /**
     * 根据条件统计个数
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        String sql = "select count(*) from td_sys_users where 1=1 ";
        sql += createSearchSQL(sysUserSearchVO);
        return count(sql, sysUserSearchVO);
    }

    /**
     * 根据条件查询
     */
    public List<SysUser> listBySearch(SysUserSearchVO sysUserSearchVO) {
        String sql = "select " + BASE_FIELD + " from td_sys_users where 1=1 ";
        sql += createSearchSQL(sysUserSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, sysUserSearchVO.getPageIndex());
        return list(sql, sysUserSearchVO);
    }

    /**
     * 修改用户token
     */
    public int updateToken(Integer userId, String userToken) {
        String sql = "update td_sys_users set userToken=? where id=?";
        return update(sql, userToken, userId);
    }

    /**
     * 修改用户密码
     */
    public int updateResetPwd(Integer userId, String pwd,String random) {
        String sql = "update td_sys_users set userPassword=?,randomCode=? where id=?";
        return update(sql, pwd, random,userId);
    }

}
