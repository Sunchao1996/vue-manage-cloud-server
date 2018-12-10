package com.sc.api.config.listener;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * what: apollo配置监听
 *
 * @author 孙超 created on 2018/12/7
 */
@Component
public class ApolloListener {
    private static final Logger logger = LoggerFactory.getLogger(ApolloListener.class);

    @Autowired
    private RefreshScope refreshScope;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        refreshScope.refresh("pubConfig");
    }

}
