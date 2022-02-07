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
    private String userName;
    private String passWord;
    private List<AdminRole> roleList;

    public UserDetail(
            long id,
            String userName,
            List<AdminRole> roleList,
            String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.roleList = roleList;
    }

    public UserDetail(String userName, String passWord, List<AdminRole> roleList) {
        this.userName = userName;
        this.passWord = passWord;
        this.roleList = roleList;
    }

    public UserDetail(long id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
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
        return passWord;
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
        return true;
    }


    public List<AdminRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AdminRole> roleList) {
        this.roleList = roleList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

}
