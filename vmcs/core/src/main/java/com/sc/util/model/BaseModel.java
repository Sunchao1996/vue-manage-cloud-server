package com.sc.util.model;

import java.util.Date;

/**
 * @autho 孔垂云
 * @date 2017/7/15.
 */
public class BaseModel {
    private String createdBy;//创建人
    private Date createdAt;//创建时间
    private String lastModifiedBy;//最后修改人
    private Date lastModifiedAt;//最后修改时间

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
