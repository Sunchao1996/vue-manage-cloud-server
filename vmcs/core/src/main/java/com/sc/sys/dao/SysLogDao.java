package com.sc.sys.dao;

import com.sc.core.dao.BaseDao;
import com.sc.sys.model.SysLog;
import com.sc.sys.vo.SysLogSearchVO;
import com.sc.util.page.PageUtil;
import com.sc.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统日志dao
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Repository
public class SysLogDao extends BaseDao<SysLog, SysLogSearchVO> {

    public int add(SysLog sysLog) {
        String sql = "insert into td_log_sys(userId,operaDate,operaIp,moduleName,operaName,operaUrl,operaParams)"
                + " values( :userId,now(),:operaIp,:moduleName,:operaName,:operaUrl,:operaParams)";
        return insert(sql, sysLog);
    }
}
