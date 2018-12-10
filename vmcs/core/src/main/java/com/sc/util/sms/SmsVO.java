package com.sc.util.sms;

/**   短信内容VO
 * Created by 孔垂云 on 2017/8/13.
 */
public class SmsVO {
    private String mobile;//手机号
    private String type;//类型 01注册 02找回密码 03员工被邀请加入
    private String random;//随机数
    private int minute;//分钟

    public SmsVO(String mobile, String type, String random, int minute) {
        super();
        this.mobile = mobile;
        this.type = type;
        this.random = random;
        this.minute = minute;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    @Override
    public String toString() {
        return "ShortMessageVO [mobile=" + mobile + ", type=" + type + ", random=" + random + ", minute=" + minute + "]";
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
