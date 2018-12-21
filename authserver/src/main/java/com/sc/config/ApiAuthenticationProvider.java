package com.sc.config;

import com.sc.sys.service.SysUserDetailsService;
import com.sc.util.session.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * what:
 *
 * @author 孙超 created on 2018/12/15
 */
@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SysUserDetailsService sysUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Principal是一个包含用户的标识和用户的所属角色的对象
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = sysUserDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new DisabledException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
