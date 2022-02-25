package com.keanntech.framework.auth.service;

import com.keanntech.framework.auth.domain.Admin;
import com.keanntech.framework.auth.domain.UserDetail;
import com.keanntech.framework.auth.mapper.AdminMapper;
import com.keanntech.framework.auth.mapper.AdminRoleMapper;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    private AdminRoleMapper adminRoleMapper;

    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        Admin admin = adminMapper.findByUserName(name);
        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        }
//        List<Roles> roleList = new ArrayList<>();
//        List<AdminRole> adminRoleList = adminRoleMapper.findRoleByUserId(admin.getId());
//        if (Objects.nonNull(adminRoleList)) {
//            adminRoleList.forEach(adminRole -> {
//                Roles roles = new Roles(adminRole.getId(), adminRole.getRoleName(), adminRole.getRoleCode());
//                roleList.add(roles);
//            });
//        }
        UserDetail userDetail = new UserDetail(admin.getId(), name, admin.getPassWord(), null, admin.getTenantCode(), admin.getAdminType());
        return userDetail;
    }

}
