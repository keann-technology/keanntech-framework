package com.keanntech.framework.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:57 下午
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private long id;
    private String userName;
    private String passWord;
    private List<Roles> roleList;

    public UserDetails(long id, String userName, List<Roles> roleList, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.roleList = roleList;
    }

    /**
     * 返回分配给用户的角色列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(roleList) && roleList.size() > 0) {
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
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


    public List<Roles> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Roles> roleList) {
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
