package com.sc.sys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * what:   id,路由名称，路由代码，路由路径，父节点
 *
 * @author 孙超 created on 2018/11/6
 */
public class SysResource {
    private Integer id;
    private String resourceName;
    private String resourceCode;
    private String resourceUrl;
    private Integer resourceParentId;
    private String resourceWebUrl = "";
    private String resourceManagerUrl = "";
    private List<SysResource> children = new ArrayList<>();

    public SysResource(Integer id) {
        this.id = id;
    }

    public SysResource(Integer id, String resourceName, String resourceCode, String resourceUrl, Integer resourceParentId, String resourceWebUrl, String resourceManagerUrl, List<SysResource> children) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourceCode = resourceCode;
        this.resourceUrl = resourceUrl;
        this.resourceParentId = resourceParentId;
        this.resourceWebUrl = resourceWebUrl;
        this.resourceManagerUrl = resourceManagerUrl;
        this.children = children;
    }

    public SysResource setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }


    public String getResourceName() {
        return resourceName;
    }

    public SysResource setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public SysResource setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public SysResource setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public Integer getResourceParentId() {
        return resourceParentId;
    }

    public SysResource setResourceParentId(Integer resourceParentId) {
        this.resourceParentId = resourceParentId;
        return this;
    }

    public String getResourceWebUrl() {
        return resourceWebUrl;
    }

    public SysResource setResourceWebUrl(String resourceWebUrl) {
        this.resourceWebUrl = resourceWebUrl;
        return this;
    }

    public String getResourceManagerUrl() {
        return resourceManagerUrl;
    }

    public SysResource setResourceManagerUrl(String resourceManagerUrl) {
        this.resourceManagerUrl = resourceManagerUrl;
        return this;
    }

    public List<SysResource> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return children;
    }

    public SysResource setChildren(List<SysResource> children) {
        this.children = children;
        return this;
    }

    public SysResource() {
    }
}
