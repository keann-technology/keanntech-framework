package com.keanntech.framework.common.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:57 下午
 */
@Builder
@Data
public class UserDetail implements org.springframework.security.core.userdetails.UserDetails {

    private long id;
    private String userName;
    private String passWord;
    private String tenantCode;
    private Integer adminType;
    private boolean enabled;
    private Set<GrantedAuthority> authorities;

    /**
     * 返回分配给用户的角色列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
