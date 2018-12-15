package com.sc.config;

import com.sc.sys.service.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * what:  Spring Security Config
 *
 * @author 孙超 created on 2018/12/11
 */
@EnableWebSecurity
@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserDetailsService sysUserDetailsService;
    @Autowired
    private ApiAuthenticationProvider apiAuthenticationProvider;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService);
        auth.authenticationProvider(apiAuthenticationProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
