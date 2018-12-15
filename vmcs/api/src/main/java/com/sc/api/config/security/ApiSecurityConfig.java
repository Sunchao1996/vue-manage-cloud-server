//package com.sc.api.config.security;
//
//import com.sc.sys.service.SysUserDetailsService;
//import org.bouncycastle.jcajce.provider.keystore.BC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import sun.security.provider.SecureRandom;
//
//import java.security.NoSuchAlgorithmException;
//
///**
// * what:  Spring Security Config
// *
// * @author 孙超 created on 2018/12/11
// */
//@EnableWebSecurity
//@Configuration
//public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private SysUserDetailsService sysUserDetailsService;
//    @Autowired
//    private ApiAuthenticationProvider apiAuthenticationProvider;
//
//    @Override
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(sysUserDetailsService);
//        auth.authenticationProvider(apiAuthenticationProvider);
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    }
//}
