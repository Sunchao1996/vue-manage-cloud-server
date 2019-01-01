package com.sc.sys.model;

import com.sc.sys.dao.SysRoleDao;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * what:  系统角色
 *
 * @author 孙超 created on 2018/11/8
 */
@Data
public class SysRole implements Serializable {
    private Integer id;
    private String roleName;
    @NotBlank
    private String roleCode;
    /**
     * 0可用1禁用
     */
    private Integer roleStatus;
    private Date createTime;
    private String roleIds;
    /**
     * 1.普通角色 2.角色组
     */
    private Integer roleType;

}
