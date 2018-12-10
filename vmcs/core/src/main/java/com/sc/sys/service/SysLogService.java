package com.sc.sys.service;

import com.sc.sys.dao.SysLogDao;
import com.sc.sys.model.SysLog;
import com.sc.sys.model.SysResource;
import com.sc.sys.vo.SysLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统日志service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
@Configuration
@EnableAsync
public class SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private SysResourceService sysResourceService;


    /**
     * 操作时记录日志
     */
    @Async
    public void addLog(int userId, String url, String parameters, String operaIp) {
        List<SysResource> list = sysResourceService.listAll();//获取所有资源
        HashMap<String, SysResource> hashMap = new HashMap<>();
        for (SysResource s : list) {
            hashMap.put(s.getResourceManagerUrl(), s);
        }
        SysLog sysLog = new SysLog();
        sysLog.setUserId(userId);
        sysLog.setOperaUrl(url);
        if (parameters.length() > 1000)
            parameters = parameters.substring(0, 1000);
        sysLog.setOperaParams(parameters);
        sysLog.setOperaDate(new Date());
        sysLog.setOperaIp(operaIp);

        SysResource sysResource = hashMap.get(url);
        if (sysResource != null) {
            sysLog.setModuleName(String.valueOf(sysResource.getResourceParentId()));
            sysLog.setOperaName(sysResource.getResourceName());
        }
        sysLogDao.add(sysLog);
    }

}
