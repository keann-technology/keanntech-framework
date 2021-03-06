package com.keanntech.framework.common.security;

import com.keanntech.framework.common.domain.UserDetail;
import com.keanntech.framework.common.utils.JwtUtils;
import com.keanntech.framework.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:38 下午
 */
@Service("perms")
@RefreshScope
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
        UserDetail userDetails = jwtUtils.getUserDetailFromToken(token);
        Set<String> roles = userDetails.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toSet());

        return roles.contains(role);
    }

}
