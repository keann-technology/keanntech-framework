package com.keanntech.framework.admin.controller;

import com.keanntech.framework.admin.domain.Admin;
import com.keanntech.framework.common.annotation.ApiVersion;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqingfu
 * @date 2022年02月09日 1:54 下午
 */
@RestController
@Api("用户注册、详情等")
@RequestMapping("/api/admin/{version}")
public class AdminUserController {

    @PostMapping("/register")
    @ApiOperation("注册")
    @ApiVersion()
    @PreAuthorize("@perms.hasRole('super_admin')")
    public ResultJson register(@RequestBody Admin admin) {
        try {
            return ResultJson.ok();
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

}
