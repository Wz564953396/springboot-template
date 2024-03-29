package com.wz.example.template.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
public class ClientUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -8600778251588922943L;

    private Integer id;

    private String username;

    private String password;

    private String authority;

    private String role;

    private String usertype;

    private String realname;

    private String area;

    private Set<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    //账号是否过期
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    //账号凭证是否未过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
