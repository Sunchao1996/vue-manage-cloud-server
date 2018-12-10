package com.sc.util.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回码枚举类
 *
 * @author 王晓安
 */

public enum EnumReturnCode {
    /**
     * 成功类
     */
    SUCCESS_LOGIN("01000", "登录成功~", true),
    SUCCESS_OPERA("01001", "操作成功~", true),
    SUCCESS_AUTH("01001", "权限校验成功~", true),
    SUCCESS_INFO_GET("01002", "获取信息成功~", true),
    /**
     * 失败类
     */
    FAIL_LOGIN("02000", "登录失败~", false),
    FAIL_OPERA("02001", "操作失败~", false),
    FAIL_AUTH("02002", "权限校验失败~", false),
    FAIL_JSON("02003", "JSON校验失败~", false),
    FAIL_ARGS("02004", "参数格式错误~", false),
    FAIL_USER_LOCK("02005", "您的帐号已锁定，请联系管理员~", false),
    FAIL_ERROR("02006", "系统异常~", false),
    FAIL_INTERCEPTOR1("02007", "您的账号登录异常，若非本人操作请修改密码", false),
    FAIL_INTERCEPTOR2("02008", "您的账号登录ip异常，若非本人操作请修改密码", false),
    FAIL_NOAUTH("02009", "无权限访问，请联系管理员~", false),
    FAIL_NOLOGIN("02010", "未登录~", false),
    FAIL_LOGIN_ERROR("02011", "账号或密码错误~", false),
    FAIL_INFO_GET("02012", "获取信息失败~", false);

    EnumReturnCode(String code, String value, boolean success) {
        this.code = code;
        this.value = value;
        this.success = success;
    }

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回码对应值
     */
    private String value;

    /**
     * 返回正确与否
     */
    private boolean success;

    /**
     * code-value映射map
     */
    private static Map<String, String> map;

    static {
        map = new HashMap<>();
        for (EnumReturnCode enumReturnCode : EnumReturnCode.values()) {
            map.put(enumReturnCode.getCode(), enumReturnCode.getValue());
        }
    }

    /**
     * 根据code返回对应值
     *
     * @param code 返回码
     * @return 返回值
     */
    public static String getValue(String code) {
        return map.get(code);
    }

    @Override
    public String toString() {
        return "EnumReturnCode{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", success=" + success +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
