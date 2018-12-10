package com.sc.util.wx;

import org.springframework.stereotype.Component;

/**微信公众号的公共配置信息
 * Created by 孔垂云 on 2017/7/31.
 */
@Component
public class WxConfig {
    private String chatId;//原始id
    private String name;//公众号名称
    private String code;//微信号
    private String appid;//appid
    private String appSecret;//appsecret
    private String token;//token
    private String mchId;//商户号
    private String mchKey;//商户key
    private String mchCertificate;//证书地址

    private String timestamp;
    private String nonceStr;
    private String signature;
    private String jsapi_ticket;//

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getMchCertificate() {
        return mchCertificate;
    }

    public void setMchCertificate(String mchCertificate) {
        this.mchCertificate = mchCertificate;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

    @Override
    public String toString() {
        return "WxConfig [appid=" + appid + ", timestamp=" + timestamp + ", nonceStr=" + nonceStr + ", signature=" + signature + ", jsapi_ticket=" + jsapi_ticket + "]";
    }
}
