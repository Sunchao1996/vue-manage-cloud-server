package com.sc.sys.vo;

import com.sc.util.page.PageSearchVO;

/**
 * 用户查询VO
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysUserSearchVO extends PageSearchVO {
    private String userName;
    private String userRealName;
    private Integer userStatus;

    public String getUserNameStr() {
        return "%" + this.userName + "%";
    }
    public String getUserRealNameStr() {
        return "%" + this.userRealName + "%";
    }

    @Override
    public String toString() {
        return "SysUserSearchVO{" +
                "userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public SysUserSearchVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public SysUserSearchVO setUserRealName(String userRealName) {
        this.userRealName = userRealName;
        return this;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public SysUserSearchVO setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
}
