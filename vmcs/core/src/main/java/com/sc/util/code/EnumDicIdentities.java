package com.sc.util.code;

/**
 * 用户身份了类型
 *
 * @author 王晓安
 */
public enum EnumDicIdentities {


    /**
     * 注册用户
     */
    REGISTER("01", "注册用户");

    EnumDicIdentities(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 代码
     */
    private String code;

    /**
     * 身份名称
     * 00	网站工作人员
     * 01	注册用户
     * 02	注册摄影师
     * 03	特殊贡献摄影师
     * 04	点评摄影师
     * 05	后期处理摄影师
     * 06	被推荐摄影师
     * 07	被推荐摄影导游
     * 08	自荐摄影师
     * 09	自荐摄影导游
     * 10	住宿酒店联系人
     * 11	住家或楼顶拍摄联系人
     * 12	旅游达人
     * 13	观察名单
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
