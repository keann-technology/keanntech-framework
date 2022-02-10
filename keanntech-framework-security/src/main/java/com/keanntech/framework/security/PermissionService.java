package com.keanntech.framework.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:38 下午
 */
@Service("perms")
public class PermissionService {

    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role) || StringUtils.isBlank(role)) {
            return false;
        }
        return true;
    }

}
