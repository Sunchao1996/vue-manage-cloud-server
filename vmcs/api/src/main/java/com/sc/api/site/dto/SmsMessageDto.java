package com.sc.api.site.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ycf on 2017/9/6.
 *
 * @author 超凡
 * @function 手机短信Dto
 */
public class SmsMessageDto {
    @NotBlank
    @Length(min = 11,max = 11)
    private String mobile;// 手机号
    @NotNull
    private Integer type;//短信类型：1.注册 2.找回密码
    private String areaCode;//手机区号(暂时无用)
    private String content;// 短信内容(预留，暂时无用)
    private String verifyCode;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "SmsMessageDto{" +
                "mobile='" + mobile + '\'' +
                ", type=" + type +
                ", areaCode='" + areaCode + '\'' +
                ", content='" + content + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
