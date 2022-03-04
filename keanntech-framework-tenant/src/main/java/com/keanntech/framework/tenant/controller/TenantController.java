package com.keanntech.framework.tenant.controller;

import com.keanntech.framework.common.annotation.ApiVersion;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import com.keanntech.framework.tenant.constant.TenantConstant;
import com.keanntech.framework.tenant.domain.Tenant;
import com.keanntech.framework.tenant.service.TenantService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqingfu
 * @date 2022年03月01日 8:13 AM
 */

@RestController
@Slf4j
@Api("租户操作")
@RequestMapping("/api/tenant/{version}")
public class TenantController {

    final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/regTenant")
    @PreAuthorize("authentication.principal.tenantCode == '" + TenantConstant.DEFAULT_TENANT_CODE +  "'")
    @ApiVersion()
    public ResultJson regTenant(@RequestBody Tenant tenant) {
        log.info("注册租户：{}", tenant.toString());
        try {
            tenantService.regTenant(tenant);
            return ResultJson.ok();
        } catch (Exception e) {
            log.error("注册租户失败：{}", e.getMessage());
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

}
