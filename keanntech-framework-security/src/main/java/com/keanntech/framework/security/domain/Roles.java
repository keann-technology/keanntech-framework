package com.keanntech.framework.security.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author miaoqingfu
 * @date 2022年02月10日 3:59 下午
 */
@Data
@Builder
public class Roles {

    private Long id;

    private String roleName;

    private String roleCode;

    public Roles(Long id, String roleName, String roleCode) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    public Roles() {}

}
