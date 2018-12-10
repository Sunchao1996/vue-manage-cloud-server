package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysUserLogin;
import com.sc.sys.vo.SysUserloginSearchVO;
import com.sc.util.page.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户登录DAO
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Repository
public class SysUserLoginDao extends BaseDao<SysUserLogin, SysUserloginSearchVO> {

    /**
     * 插入登录日志
     *
     * @param sysUserLogin
     */
    public void add(SysUserLogin sysUserLogin) {
        String sql = "insert into td_log_user_login(userId,loginDate,loginIp,terminal,explorerType,explorerVersion)";
        sql += " values(:userId,now(),:loginIp,:terminal,:explorerType,:explorerVersion)";
        insert(sql, sysUserLogin);
    }

    /**
     * 取得最后登录信息
     *
     * @param userId
     * @return
     */
    public SysUserLogin getLastLogin(int userId) {
        String sql = "select t.id,t.userId,t.loginDate,t.loginIp,t.terminal,t.explorerType,t.explorerVersion from td_log_user_login t where userId=? order by loginDate desc limit 0,1";
        return get(sql, userId);
    }

    public List<SysUserLogin> list(SysUserloginSearchVO sysUserloginSearchVO) {
        String sql = "select t.id,t.userId,t.loginDate,t.loginIp,t.terminal,t.explorerType,t.explorerVersion from td_log_user_login t where userId=? order by loginDate desc ";
        sql = PageUtil.createMysqlPageSql(sql, sysUserloginSearchVO.getPageIndex());
        return list(sql, sysUserloginSearchVO.getUserId());
    }

    /**
     * 查询用户登录总数
     *
     * @param userId
     * @return
     */
    public int count(int userId) {
        String sql = "select count(*) from td_log_user_login where userId=? ";
        return count(sql, userId);
    }

}
