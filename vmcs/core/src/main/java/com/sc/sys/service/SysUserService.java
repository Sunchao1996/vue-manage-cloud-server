package com.sc.sys.service;

import com.sc.core.pub.PubConfig;
import com.sc.sys.dao.SysUserDao;
import com.sc.sys.dao.SysUserRoleDao;
import com.sc.sys.model.*;
import com.sc.sys.vo.SysUserSearchVO;
import com.sc.util.base64.Base64Util;
import com.sc.util.code.RandomCodeUtil;
import com.sc.util.date.DateUtil;
import com.sc.util.encrypt.Md5SaltUtil;
import com.sc.util.page.PageUtil;
import com.sc.util.string.StringUtil;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * what:   系统用户
 *
 * @author 孙超 created on 2018/11/8
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 新增用户
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int save(SysUser sysUser) {
        addPwd(sysUser);
        addAvatorPath(sysUser);
        int key = sysUserDao.save(sysUser);
        int flag = sysUserRoleDao.batchAdd(createUsersRolesList(key, sysUser.getRoles()));
        return flag;
    }

    /**
     * 填充密码和随机数
     */
    private void addPwd(SysUser sysUser) {
        sysUser.setRandomCode(RandomCodeUtil.createRandomCode(6));
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(sysUser.getRandomCode());
        if (StringUtil.isNullOrEmpty(sysUser.getUserPassword())) {
            sysUser.setUserPassword(md5SaltUtil.encode("123456"));
        } else {
            sysUser.setUserPassword(md5SaltUtil.encode(sysUser.getUserPassword()));
        }
    }

    /**
     * 填充用户头像地址，并上传头像
     */
    public void addAvatorPath(SysUser sysUser) {
        //保存头像
        String file = sysUser.getUserAvatar();
        if (StringUtil.isNullOrEmpty(file)) {
            //默认头像
            sysUser.setUserAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        } else if (file.indexOf("base64") > -1) {
            //配置文件图片上传路径
            String filePath = pubConfig.getImageUploadPath();
            String saveUrl = File.separator + "users" + File.separator + DateUtil.getShortSystemDate() + File.separator;
            //后缀
            String ext = file.substring(file.indexOf("/") + 1, file.indexOf(";"));
            //数据部分
            String fileImageData = file.substring(file.indexOf(",") + 1);
            String newFileName = new Date().getTime() + "." + ext;
            File saveDirFile = new File(filePath + saveUrl);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            Base64Util.generateImage(fileImageData, filePath + saveUrl + newFileName);
            sysUser.setUserAvatar(pubConfig.getImageServer() + saveUrl + newFileName);
        }
    }

    /**
     * 修改用户信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int updateById(SysUser sysUser) {
        addAvatorPath(sysUser);
        int flag = sysUserDao.updateById(sysUser);
        flag += sysUserRoleDao.deleteByUserId(sysUser.getId());
        flag += sysUserRoleDao.batchAdd(createUsersRolesList(sysUser.getId(), sysUser.getRoles()));
        return flag;
    }

    /**
     * 构成SysUsersRoles对象
     */
    public List<SysUsersRoles> createUsersRolesList(Integer userId, String roles) {
        String[] roleArray = roles.split("@");
        List<SysUsersRoles> list = new ArrayList<>();
        for (String temp : roleArray) {
            if (StringUtil.isNullOrEmpty(temp)) {
                continue;
            }
            SysUsersRoles sysUsersRoles = new SysUsersRoles();
            sysUsersRoles.setUserId(userId);
            sysUsersRoles.setRoleId(Integer.valueOf(temp));
            list.add(sysUsersRoles);
        }
        return list;
    }

    /**
     * 修改用户状态
     */
    public int updateStatus(Integer userId) {
        if (userId == null) {
            return -1;
        }
        SysUser sysUser = sysUserDao.getById(userId);
        if (sysUser == null) {
            return -1;
        }
        Integer userStatus = sysUser.getUserStatus();
        if (userStatus == null) {
            return -1;
        }
        if (userStatus == 0) {
            userStatus = 1;
        } else {
            userStatus = 0;
        }
        return sysUserDao.updateStatus(userStatus, userId);
    }

    /**
     * 删除用户
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int deleteById(Integer id) {
        int flag = sysUserDao.deleteById(id);
        flag += sysUserRoleDao.deleteByUserId(id);
        return flag;
    }


    /**
     * 根据id查询
     */
    public SysUser getById(Integer id) {
        List<SysRole> roleList = sysUserRoleDao.listRolesByUserId(id);
        StringBuffer roles = new StringBuffer();
        for (SysRole role : roleList) {
            roles.append(role.getId());
            roles.append("@");
        }
        SysUser sysUser = sysUserDao.getById(id);
        if (sysUser != null && StringUtil.isNotNullOrEmpty(roles.toString())) {
            sysUser.setRoles(roles.toString().substring(0, roles.length() - 1));
        } else {
            sysUser.setRoles("");
        }
        return sysUser;
    }

    /**
     * 查询所有用户
     */
    public List<SysUser> listAll() {
        return sysUserDao.listAll();
    }

    /**
     * 根据用户名查询
     */
    public SysUser getByUserName(String userName) {
        return sysUserDao.getByUserName(userName);
    }


    /**
     * 根据条件统计个数
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        return sysUserDao.count(sysUserSearchVO);
    }

    /**
     * 根据条件查询
     */
    public List<SysUser> listBySearch(SysUserSearchVO sysUserSearchVO) {
        return sysUserDao.listBySearch(sysUserSearchVO);
    }

    /**
     * 验证密码是否正确
     */
    public boolean checkPass(SysUser sysUser, String inputPassword) {
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(sysUser.getRandomCode());
        return md5SaltUtil.isPasswordValid(sysUser.getUserPassword(), inputPassword);
    }

    public static void main(String[] args) {
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil("@@@@@");
        System.out.println(md5SaltUtil.encode("123456"));
    }

    /**
     * 根据id重置密码为123456
     *
     * @param userId
     * @return
     */
    public int updateResetPwd(Integer userId) {
        if (userId == null) {
            return -1;
        }
        SysUser sysUser = sysUserDao.getById(userId);
        if (sysUser == null) {
            return -1;
        }
        String random = RandomCodeUtil.createRandomCode(6);
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(random);
        String pwd = md5SaltUtil.encode("123456");
        return sysUserDao.updateResetPwd(userId, pwd, random);
    }

    /**
     * 用户修改密码
     *
     * @param sysPwd
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int updateUserPwd(SysPwd sysPwd) {
        if (StringUtil.isNullOrEmpty(sysPwd.getNewUserPwd()) || StringUtil.isNullOrEmpty(sysPwd.getSureNewUserPwd()) || StringUtil.isNullOrEmpty(sysPwd.getUserName())) {
            return -1;
        }
        if (!sysPwd.getNewUserPwd().equals(sysPwd.getSureNewUserPwd())) {
            return -1;
        }
        SysUser sysUser = sysUserDao.getByUserName(sysPwd.getUserName());
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(sysUser.getRandomCode());
        if (sysUser.getUserPassword().equals(md5SaltUtil.encode(sysPwd.getUserPwd()))) {
            sysUser.setUserPassword(sysPwd.getNewUserPwd());
            addPwd(sysUser);
            return sysUserDao.updateResetPwd(sysUser.getId(), sysUser.getUserPassword(), sysUser.getRandomCode());
        } else {
            return -1;
        }
    }
}
