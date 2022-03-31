package com.keanntech.framework.admin.controller;

import com.keanntech.framework.admin.service.AdminService;
import com.keanntech.framework.entity.domain.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqingfu
 * @date 2022年03月19日 5:40 PM
 */
@RestController
@Api("管理端服务")
@RequestMapping("/api/admin/{version}")
public class AdminController {

    final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation("根据用户名查找用户")
    @GetMapping("/getAdminByUserName")
    public Admin getAdminByUserName(@RequestParam("userName") String userName) {
        try {
            return adminService.findByUserName(userName);
        } catch (Exception e) {
            return null;
        }
    }

}
