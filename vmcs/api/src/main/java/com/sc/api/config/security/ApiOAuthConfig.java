package com.sc.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * what:  OAuth2 Config 资源服务器和授权服务器配置
 *
 * @author 孙超 created on 2018/12/11
 */
@Configuration
public class ApiOAuthConfig {
    @Configuration
    @EnableResourceServer
    public static class ResrouceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;
        @Autowired
        private ConsumterAccessDeniedHandler ConsumterAccessDeniedHandler;
        @Autowired
        private ConsumterAuthenticationEntryPoint consumterAuthenticationEntryPoint;
        @Autowired
        private ConsumterExpressionHandler consumterExpressionHandler;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore()).resourceId("manage").stateless(true);
            resources.accessDeniedHandler(ConsumterAccessDeniedHandler);
            resources.authenticationEntryPoint(consumterAuthenticationEntryPoint);
        }

        @Bean
        public TokenStore tokenStore() {
            ApiRedisTokenStore apiRedisTokenStore = new ApiRedisTokenStore(redisConnectionFactory);
            apiRedisTokenStore.setPrefix("authserver:oauth:");
            return apiRedisTokenStore;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/login/login").permitAll();
            http.authorizeRequests().antMatchers("/login/logout").authenticated();
            http.authorizeRequests().anyRequest().authenticated() ;
            http.csrf().disable();
        }

    }
}
