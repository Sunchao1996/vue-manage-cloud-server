package com.sc.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 孔垂云 on 2017/7/31.
 */
public class LogUtil {
    private static Logger sysLog = LoggerFactory.getLogger("sysLog");
    private static Logger httpLog = LoggerFactory.getLogger("httpLog");

    /**
     * 功能描述:记录系统日志
     *
     * @param str 要输出的字符串
     */
    public static void info(String str) {
        sysLog.info(str);
    }

    /**
     * 功能描述:记录系统异常
     *
     * @param str 要输出的字符串
     */
    public static void error(String str) {
        sysLog.error(str);
    }

    /**
     * 功能描述:记录系统异常
     *
     * @param e 系统异常
     */
    public static void error(Exception e) {
        sysLog.error(e.getStackTrace().toString());
    }


    /**
     * 功能描述:记录请求的http
     *
     * @param str 要输出的字符串
     */
    public static void infoHttp(String str) {
        httpLog.info(str);
    }
}
