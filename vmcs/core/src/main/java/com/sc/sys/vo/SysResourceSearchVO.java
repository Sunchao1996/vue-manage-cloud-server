package com.sc.sys.vo;

/**
 * what:   系统资源查询条件
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysResourceSearchVO {
    private Integer id;
    private String resourceCode;

    @Override
    public String toString() {
        return "SysResourceVO{" +
                "id=" + id +
                ", resourceCode='" + resourceCode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SysResourceSearchVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public SysResourceSearchVO setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
        return this;
    }
}
