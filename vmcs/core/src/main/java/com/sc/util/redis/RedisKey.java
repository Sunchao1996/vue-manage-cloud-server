package com.sc.util.redis;

/**
 * @autho 孔垂云
 * @date 2017/7/27.
 */
public class RedisKey {

    /**
     * 在线人数
     */
    public static final String ONLINE_USER = "WXAINN:ONLINE";

    /**
     * 参数配置
     */
    public static final String DIC_PARAM = "WXAINN:DIC:PARAM";

    /**
     * 系统资源
     */
    public static final String SYS_RESOURCE = "WXAINN:SYS:RESOURCE:";

    /**
     * 公共系统资源
     */
    public static final String SYS_RESOURCE_PATTERN = "WXAINN:SYS:RESOURCE:*";

    /**
     * 系统功能
     */
    public static final String SYS_FUNCTION = "WXAINN:SYS:FUNCTION:";

    /**
     * 公共系统功能
     */
    public static final String SYS_FUNCTION_PATTERN = "WXAINN:SYS:FUNCTION:*";

    /**
     * 系统菜单
     */
    public static final String SYS_MENU = "WXAINN:SYS:MENU:";

    /**
     * 公共系统菜单
     */
    public static final String SYS_MENU_PATTERN = "WXAINN:SYS:MENU:*";


    /**
     * 微信access_token
     */
    public static final String ACCESS_TOKEN = "WXAINN:WX:ACCESS_TOKEN";
    /**
     * 微信jsapi_ticket
     */
    public static final String JSAPI_TICKET = "WXAINN:WX:JSAPI_TICKET";

    /**
     * 用户注册时验证码
     */
    public static final String REGISTER_MOBILE = "WXAINN:REGISTER:MOBILE:";

    /**
     * spring session
     */
    public static final String SPRING_SESSION = "spring:session:sessions:";

    /**
     * 短信模板
     */
    public static final String MSG_SHORT_MODEL = "WXAINN:MSG:SHORT:MODEL";



    /**
     * 更换总管理员手机号的验证码
     */
    public static final String UPDATE_CHIEFADMIN_PHONE = "WXAINN:UPDATE:CHIEFADMIN:PHONE:";
    public static final String UPDATE_CHIEFADMIN_PHONE_NEW = "WXAINN:UPDATE:CHIEFADMIN:PHONE:NEW:";
    /**
     * 更换企业手机号码验证码
     */
    public static final String UPDATE_CORPBIND_PHONE = "UPDATE:CORPBIND:PHONE:";
    /**
     * APP更换企业手机号码验证码
     */
    public static final String UPDATE_CORPBIND_APP_PHONE = "UPDATE:CORPBIND:APP:PHONE:";

    public static final String FINDPWD_MOBILE = "WXAINN:FINDPWD:MOBILE:";//找回密码时的验证码
    public static final String WELCODE_MOBILE = "WXAINN:WELCODE:MOBILE:";//员工被邀请加入的验证码
    public static final int VERIFYCODE_TIME = 300;//验证码有效时间

    public static final String WEBSESSION = "SC:WEBSESSION:";//app的token对应的key


    public static final int WEB_TOKEN_TIMEOUT = 15 * 24 * 60 * 60;//app的token过期时间


    public static final String STAT_DATA_REFRESH = "WXAINN:STAT:REFRESH:";//大数据刷新数据，防止同时刷新处理

    /**
     * 美元汇率
     */
    public static final String DOLLAR_VALUE = "WXA:PARAM:DOLLAR_VALUE";

    /**
     * 交易所数据
     */
    public static final String TICKER = "WXA:PARAM:TICKER:";


}
