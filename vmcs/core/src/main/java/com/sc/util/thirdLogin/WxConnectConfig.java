package com.sc.util.thirdLogin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ycf on 2017/8/30.
 *
 * @author 超凡
 * @function 微信配置类
 */
public class WxConnectConfig {
    private static Properties props = new Properties();

    public WxConnectConfig() {
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wxconnectconfig.properties"));
        } catch (FileNotFoundException var1) {
            var1.printStackTrace();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

}
