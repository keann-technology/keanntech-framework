package com.keanntech.framework.auth.service;

import com.keanntech.framework.auth.domain.Tenant;

/**
 * @Author
 * @Create 2022-03-01 08:17:28
 * @Desc ...
 */
public interface TenantService {

    /**
     * 注册租户
     * @param tenant
     * @return
     */
    int regTenant(Tenant tenant);

}
