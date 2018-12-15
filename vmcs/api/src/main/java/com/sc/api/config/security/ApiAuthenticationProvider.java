//package com.sc.api.config.security;
//
//import com.sc.sys.model.SysUser;
//import com.sc.sys.service.SysUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * what:
// *
// * @author 孙超 created on 2018/12/15
// */
//@Component
//public class ApiAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private SysUserDetailsService sysUserDetailsService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = (String) authentication.getCredentials();
//        UserDetails user = sysUserDetailsService.loadUserByUsername(username);
//
//        //加密过程在这里体现
//        System.out.println("结果CustomUserDetailsService后，已经查询出来的数据库存储密码:" + user.getPassword());
//        if (!user.getPassword().equals(password)) {
//            throw new DisabledException("Wrong password.");
//        }
//
//        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//        return new UsernamePasswordAuthenticationToken(user, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
