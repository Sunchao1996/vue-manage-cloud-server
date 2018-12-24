package com.sc.util.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sc.sys.model.SysRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * web用户Session信息 该类可以根据实际信息进行修改
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class WebSession implements Serializable, UserDetails, Principal {
    private String user;
    private String status;
    private String token;
    private String name;
    private String avatar;
    private String introduction;
    private String[] roles;
    private String resources;//用@分隔
    private String ip;
    private Integer userId;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private List<SysRole> roleList;
    @JsonIgnore
    private String manageUrl;//用来校验是否允许访问这个请求地址
    private String userRealName;//页面上显示的用户真实姓名
    private String roleName;//页面上显示的用户角色名称用@分隔

    public WebSession() {
    }

    public WebSession(String name, String password, List<SysRole> roleList) {
        this.name = name;
        this.password = password;
        this.roleList = roleList;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public WebSession setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
        return this;
    }

    public WebSession setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "WebSession{" +
                "user='" + user + '\'' +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", introduction='" + introduction + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", resources='" + resources + '\'' +
                ", ip='" + ip + '\'' +
                ", userId=" + userId +
                ", manageUrl='" + manageUrl + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public String getUserRealName() {
        return userRealName;
    }

    public WebSession setUserRealName(String userRealName) {
        this.userRealName = userRealName;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public WebSession setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getUser() {
        return user;
    }

    public WebSession setUser(String user) {
        this.user = user;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public WebSession setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getToken() {
        return token;
    }

    public WebSession setToken(String token) {
        this.token = token;
        return this;
    }

    public String getName() {
        return name;
    }

    public WebSession setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public WebSession setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public WebSession setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String[] getRoles() {
        return roles;
    }

    public WebSession setRoles(String[] roles) {
        this.roles = roles;
        return this;
    }

    public String getResources() {
        return resources;
    }

    public WebSession setResources(String resources) {
        this.resources = resources;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public WebSession setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public WebSession setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getManageUrl() {
        return manageUrl;
    }

    public WebSession setManageUrl(String manageUrl) {
        this.manageUrl = manageUrl;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SysRole> roleList = this.roleList;
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (roleList == null) {
            return grantedAuthorities;
        }
        for (SysRole sysRole : roleList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysRole.getRoleCode());
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
