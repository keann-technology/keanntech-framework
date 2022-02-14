package com.keanntech.framework.security;

import com.keanntech.framework.common.utils.ServletUtils;
import com.keanntech.framework.security.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:38 下午
 */
@Service("perms")
public class PermissionService {

    final JwtUtils jwtUtils;

    @Value("${jwt.header}")
    private String jwtHeader;

    public PermissionService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role) || StringUtils.isBlank(role)) {
            return false;
        }
        String token = ServletUtils.getRequest().getHeader(this.jwtHeader);
        if (StringUtils.isBlank(token) || StringUtils.isEmpty(token)) {
            return false;
        }
        List<String> roles = new ArrayList<>();
        UserDetails userDetails = jwtUtils.getUserFromToken(token);
        for(GrantedAuthority ga : userDetails.getAuthorities()) {
            String[] roleAry = ga.getAuthority().substring(1, ga.getAuthority().lastIndexOf("]")).split(",");
            Collections.addAll(roles, roleAry);
        }

        return roles.contains(role);
    }

}
