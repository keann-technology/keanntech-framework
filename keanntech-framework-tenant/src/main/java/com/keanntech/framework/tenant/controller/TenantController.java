package com.keanntech.framework.tenant.controller;

import com.keanntech.framework.common.web.ResultJson;
import com.keanntech.framework.tenant.service.TenantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqingfu
 * @date 2022年03月07日 10:59 AM
 */
@RestController
@RequestMapping("/api/tenant/{version}")
public class TenantController {

    final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("regTenant")
    public ResultJson regTenant() {
        return ResultJson.ok(tenantService.regTenant());
    }
}
