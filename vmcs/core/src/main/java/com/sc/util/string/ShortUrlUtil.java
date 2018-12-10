package com.sc.util.string;

import com.sc.util.http.HttpUtil;
import com.sc.util.json.JsonUtil;
import com.sc.util.log.LogUtil;

import java.util.Map;
import java.util.Random;

/**
 * 调用新浪的短链接
 * Created by 孔垂云 on 2017/10/31.
 */
public class ShortUrlUtil {
    /**
     * 新浪短链接请求地址
     */
    private static final String BASE_ADDRESS = "http://api.t.sina.com.cn/short_url/shorten.json?source=2623271769";

    /**
     * 把网站长链接转换为新浪短链接
     *
     * @param longUrl
     * @return
     */
    public static String createUrl(String longUrl) {
        String jsonStr = HttpUtil.getJsonFromUrl(BASE_ADDRESS + "&url_long=" + longUrl);
        if (StringUtil.isNullOrEmpty(jsonStr)) {
            LogUtil.error("短链接请求失败，请确认。" + BASE_ADDRESS);
        }
        if (StringUtil.isNotNullOrEmpty(jsonStr)) {
            Map<String, String> map = JsonUtil.toMap(jsonStr.substring(1, jsonStr.length() - 1));
            return map.get("url_short");
        } else return "";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            System.out.println(ShortUrlUtil.createUrl("http://s1.ruitecloud.com/w/F68059166"+new Random().nextInt(99999)));
        }
    }

}