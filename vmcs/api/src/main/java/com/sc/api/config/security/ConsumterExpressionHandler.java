package com.sc.api.config.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class ConsumterExpressionHandler implements PermissionEvaluator {
    /**
     * 返回true 表示验证通过
     * @param authentication
     * @param targetDomainObject
     * @param permission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
