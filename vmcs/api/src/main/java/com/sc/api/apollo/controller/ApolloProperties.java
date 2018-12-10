package com.sc.api.apollo.controller;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.sc.core.pub.PubConfig;
import com.sc.util.code.EnumReturnCode;
import com.sc.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * what:    阿波罗使用
 *
 * @author 孙超 created on 2018/12/4
 */
@EnableApolloConfig
@RestController
@RequestMapping("/apollo")
public class ApolloProperties {
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/get")
    public JsonResult get() {
        return new JsonResult(EnumReturnCode.SUCCESS_INFO_GET, pubConfig.getImageServer());
    }
}
