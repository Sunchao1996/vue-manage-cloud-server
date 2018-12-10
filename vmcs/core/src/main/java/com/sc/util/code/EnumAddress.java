package com.sc.util.code;

/**
 * 行政等级
 *
 * @author 王晓安
 */
public enum EnumAddress {


    /**
     * 全国
     */
    COUNTRY("country", "全国"),

    /**
     * 省
     */
    PROVINCE("province", "省"),

    /**
     * 市
     */
    CITY("city", "市"),

    /**
     * 区
     */
    DISTRICT("district", "区");

    EnumAddress(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     * country	全国
     * province	省
     * city	市
     * district	区
     */
    private String name;


    @Override
    public String toString() {
        return "EnumDicIdentities{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
