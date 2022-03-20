package com.keanntech.framework.auth.service;

import com.keanntech.framework.adminapi.AdminFeignClient;
import com.keanntech.framework.entity.domain.Admin;
import com.keanntech.framework.security.domain.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:26 上午
 */
@Component(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    final AdminFeignClient adminFeignClient;

    public CustomUserDetailsService(AdminFeignClient adminFeignClient) {
        this.adminFeignClient = adminFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Admin admin = adminFeignClient.findByUserNameV1(name);
        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (admin.getAdminType().equals(0)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("*"));
        } else {
//            grantedAuthorities = roleRelationMapper.findRoleByUserId(admin.getId()).stream()
//                    .map(role -> new SimpleGrantedAuthority(roleMapper.findById(role.getId()).getRoleCode()))
//                    .collect(Collectors.toSet());
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
