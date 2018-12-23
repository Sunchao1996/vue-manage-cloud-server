package com.sc.sys.service;

import com.sc.sys.dao.SysRoleResourceDao;
import com.sc.sys.dao.SysUserDao;
import com.sc.sys.dao.SysUserRoleDao;
import com.sc.sys.model.SysResource;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysUser;
import com.sc.sys.model.SysUsersRoles;
import com.sc.util.session.WebSession;
import com.sc.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * what:   自定义UserDetailsService
 *
 * @author 孙超 created on 2018/12/11
 */
@Service
public class SysUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public WebSession loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.getByUserName(userName);
        if (sysUser == null) {
            throw new UsernameNotFoundException(userName + "not found");
        }
        List<SysRole> roleList = sysUserRoleDao.listRolesByUserId(sysUser.getId());
        return new WebSession(sysUser.getUserName(), sysUser.getUserPassword(), roleList);
    }
}
