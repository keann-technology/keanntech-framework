package com.keanntech.framework.admin.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 9:05 上午
 */
public class UserDetail implements UserDetails {
    private long id;
    private String username;
    private String password;
    private List<AdminRole> roleList;

    public UserDetail(
            long id,
            String username,
            List<AdminRole> roleList,
            String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public UserDetail(String username, String password, List<AdminRole> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public UserDetail(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(roleList) && roleList.size() > 0) {
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
            });
        }
        return authorities;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }


    public List<AdminRole> getRole() {
        return roleList;
    }

    public void setRole(List<AdminRole> roleList) {
        this.roleList = roleList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
