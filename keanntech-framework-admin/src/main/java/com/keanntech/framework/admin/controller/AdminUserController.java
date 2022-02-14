package com.keanntech.framework.admin.controller;

import com.keanntech.framework.admin.domain.Admin;
import com.keanntech.framework.admin.service.AdminService;
import com.keanntech.framework.common.annotation.ApiVersion;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author miaoqingfu
 * @date 2022年02月09日 1:54 下午
 */
@RestController
@Api("管理员注册、详情等")
@RequestMapping("/api/admin/{version}")
@Slf4j
public class AdminUserController {

    final AdminService adminService;

    public AdminUserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    @ApiVersion()
    @PreAuthorize("@perms.hasRole('super_admin')")
    public ResultJson register(@RequestBody Admin admin) {
        log.info("注册管理员 admin = {}", admin.toString());
        try {
            int result = adminService.registerAdmin(admin);
            if (result > 0) {
                return ResultJson.ok();
            } else {
                return ResultJson.failure(ResultCode.REGISTER_ADMIN_ERROR);
            }
        } catch (Exception e) {
            log.error("注册管理员失败： {}", e.getMessage());
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @GetMapping("/detail")
    @ApiOperation("管理详情信息")
    @ApiVersion()
    public ResultJson<Admin> adminDetail(@RequestParam("adminId") Long adminId) {
        try {
            return ResultJson.ok(adminService.adminDetail(adminId));
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }
}
