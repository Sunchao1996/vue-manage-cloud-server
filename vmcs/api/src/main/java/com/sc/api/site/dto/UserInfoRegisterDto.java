package com.sc.api.site.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ycf on 2017/9/7.
 *
 * @function 邀请员工Dto
 */
public class UserInfoRegisterDto {
    private String areaCode;// 区号
    @NotBlank
    @Length(min = 11, max = 11)
    private String mobile;//手机号
    @NotBlank
    @Length(min = 1, max = 20)
    private String realName;// 姓名
    @NotBlank
    @Length(min = 1, max = 1)
    private String gender;// 性别
    @NotBlank
    private String birthday;// 出生日期
    @NotBlank
    @Length(min = 6, max = 6)
    private String smsCode;// 短信验证码 长度6位
    @NotBlank
    private String corpId;// 企业Id
    @NotBlank
    private String corpDepId;// 部门Id

    private String equipmentId;//设备id

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CorpUserRegisteDto{");
        sb.append("areaCode='").append(areaCode).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", smsCode='").append(smsCode).append('\'');
        sb.append(", corpId='").append(corpId).append('\'');
        sb.append(", corpDepId='").append(corpDepId).append('\'');
        sb.append(", equipmentId='").append(equipmentId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpDepId() {
        return corpDepId;
    }

    public void setCorpDepId(String corpDepId) {
        this.corpDepId = corpDepId;
    }
}
