package com.sc.util.code;

/**
 * @autho 孔垂云
 * @date 2017/7/17.
 */
public class ReturnMsg {
    private String code;//返回码
    private String cnMsg;//中文说明
    private String twMsg;//繁体说明

    public ReturnMsg(String code, String cnMsg, String twMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.twMsg = twMsg;
    }

    public ReturnMsg(String code, String cnMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
    }

    @Override
    public String toString() {
        return "ReturnMsg{" +
                "code='" + code + '\'' +
                ", cnMsg='" + cnMsg + '\'' +
                ", twMsg='" + twMsg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnMsg() {
        return cnMsg;
    }

    public void setCnMsg(String cnMsg) {
        this.cnMsg = cnMsg;
    }

    public String getTwMsg() {
        return twMsg;
    }

    public void setTwMsg(String twMsg) {
        this.twMsg = twMsg;
    }
}
