package com.keanntech.framework.auth.service;

import com.keanntech.framework.auth.constant.TenantConstant;
import com.keanntech.framework.auth.domain.Admin;
import com.keanntech.framework.auth.mapper.AdminMapper;
import com.keanntech.framework.auth.mapper.RoleMapper;
import com.keanntech.framework.auth.mapper.RoleRelationMapper;
import com.keanntech.framework.security.domain.UserDetail;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:26 上午
 */
@Component(value = "customUserDetailsService")
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RoleRelationMapper roleRelationMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Admin admin = adminMapper.findByUserName(name);
        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (admin.getTenantCode().equals(TenantConstant.DEFAULT_TENANT_CODE)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("*"));
        } else {
            grantedAuthorities = roleRelationMapper.findRoleByUserId(admin.getId()).stream()
                    .map(role -> new SimpleGrantedAuthority(roleMapper.findById(role.getId()).getRoleCode()))
                    .collect(Collectors.toSet());
        }

        return UserDetail.builder()
                .id(admin.getId())
                .userName(admin.getUserName())
                .passWord(admin.getPassWord())
                .enabled(admin.getEnabled())
                .tenantCode(admin.getTenantCode())
                .adminType(admin.getAdminType())
                .authorities(Collections.unmodifiableSet(grantedAuthorities))
                .build();
    }

}
