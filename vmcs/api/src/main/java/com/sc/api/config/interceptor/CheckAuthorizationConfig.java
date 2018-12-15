package com.sc.api.config.interceptor;

import com.sc.sys.service.SysLogService;
import com.sc.sys.service.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 校验登录拦截器配置
 * Created by 孔垂云 on 2017/8/19.
 */
@Configuration
public class CheckAuthorizationConfig extends WebMvcConfigurerAdapter {

    @Bean
    SysUserService sysUserService() {
        return new SysUserService();
    }

    @Bean
    SysLogService sysLogService() {
        return new SysLogService();
    }

    @Bean
    CheckTokenInterceptor checkVersionInterceptor() {
        return new CheckTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //判断是否带X-Token
//        registry.addInterceptor(checkVersionInterceptor())
//                .excludePathPatterns("/login/login")
//                .excludePathPatterns("/apollo/*")
//                .excludePathPatterns("/security/*")
//                .excludePathPatterns("/oauth/*")
//                .addPathPatterns("/*")
//                .addPathPatterns("/*/*")
//                .addPathPatterns("/*/*/*")
//        ;
//
//        registry.addInterceptor(new CheckAuthorizationInterceptor(sysUserService(), sysLogService()))
//                .addPathPatterns("/*")
//                .addPathPatterns("/*/*")
//                .addPathPatterns("/*/*/*")
//                .excludePathPatterns("/login/login")
//                .excludePathPatterns("/oauth/*")
//                .excludePathPatterns("/apollo/*")
//                .excludePathPatterns("/security/*")
//        ;
//
//        //记录日志
//        registry.addInterceptor(new LogRequestInterceptor()).addPathPatterns("/*")
//                .excludePathPatterns("/apollo/*")
//                .excludePathPatterns("/oauth/*")
//                .addPathPatterns("/*")
//                .addPathPatterns("/*/*")
//                .addPathPatterns("/*/*/*");
        super.addInterceptors(registry);
    }
}
