package com.sc.sys.service;

import com.sc.sys.dao.SysUserDao;
import com.sc.sys.dao.SysUserRoleDao;
import com.sc.sys.model.SysRole;
import com.sc.sys.model.SysUser;
import com.sc.sys.model.SysUsersRoles;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.getByUserName(userName);
        if (sysUser == null) {
            throw new UsernameNotFoundException(userName + "not found");
        }
        List<SysRole> roleList = sysUserRoleDao.listRolesByUserId(sysUser.getId());
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysRole.getRoleCode());
            grantedAuthorities.add(grantedAuthority);
        }
        return new User(sysUser.getUserName(), sysUser.getUserPassword(), grantedAuthorities);
    }
}
