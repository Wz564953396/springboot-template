package com.wz.example.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zz_Wang
 * @since 2023-01-28
 */
@ApiModel(value = "User对象", description = "")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户是否可用：1-可用，0-不可用")
    private Integer enabled;

    @ApiModelProperty("用户是否过期：1-正常，0-过期")
    private Integer accountNonExpired;

    @ApiModelProperty("用户是否锁定：1-正常，0-锁定")
    private Integer accountNonLocked;

    @ApiModelProperty("密码是否失效：1-正常，0-失效")
    private Integer credentialsNonExpired;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("最近登录时间")
    private LocalDateTime latestLoginTime;

    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired == 1 ? true : false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked == 1 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired == 1 ? true : false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled == 1 ? true : false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = new HashSet<>();
        if (Objects.isNull(this.roles)) {
            return simpleGrantedAuthoritySet;
        }
        this.roles.stream().forEach(item -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(item.getName());
            simpleGrantedAuthoritySet.add(simpleGrantedAuthority);
        });
        return simpleGrantedAuthoritySet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
    public Integer getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Integer accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public Integer getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Integer accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public Integer getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Integer credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public LocalDateTime getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(LocalDateTime latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", enabled=" + enabled +
            ", accountNonExpired=" + accountNonExpired +
            ", accountNonLocked=" + accountNonLocked +
            ", credentialsNonExpired=" + credentialsNonExpired +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", latestLoginTime=" + latestLoginTime +
        "}";
    }
}
