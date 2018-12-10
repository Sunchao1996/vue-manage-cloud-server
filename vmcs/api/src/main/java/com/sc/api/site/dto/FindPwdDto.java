package com.sc.api.site.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by ycf on 2017/9/6.
 *
 * @author 超凡
 * @function 找回密码Dto
 */
public class FindPwdDto {
    @NotBlank
    @Length(min = 11,max = 11)
    private String mobile;//找回手机号
    private Integer userId;
    @Pattern(regexp = "^[0-9A-Za-z_*]{6,20}$")
    private String newPass;//新密码
    private String smsCode;//短信验证码,找回密码验证时使用
    private String areaCode;//区号，暂时无用

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String toString() {
        return "FindPwdDto{" +
                "mobile='" + mobile + '\'' +
                ", userId=" + userId +
                ", newPass='" + newPass + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
