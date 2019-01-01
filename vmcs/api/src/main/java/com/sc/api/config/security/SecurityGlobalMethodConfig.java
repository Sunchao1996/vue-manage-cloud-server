package com.sc.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityGlobalMethodConfig extends GlobalMethodSecurityConfiguration {
//    @Autowired
//    private ConsumterExpressionHandler consumterExpressionHandler;
//
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
//        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(consumterExpressionHandler);
//        return defaultMethodSecurityExpressionHandler;
//    }
}
