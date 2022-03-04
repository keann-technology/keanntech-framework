package com.keanntech.framework.auth.controller;

import com.keanntech.framework.auth.domain.dto.LoginAdminDto;
import com.keanntech.framework.auth.domain.dto.RegAdminDto;
import com.keanntech.framework.auth.domain.vo.AdminUserVo;
import com.keanntech.framework.auth.service.AuthService;
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
 * @date 2022年01月26日 2:28 下午
 */
@RestController
@Api("登录及刷新TOKEN")
@RequestMapping("/api/auth/{version}")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    @ApiVersion()
    public ResultJson<AdminUserVo> login(@RequestBody LoginAdminDto admin){
        try {
            final AdminUserVo adminUserVo = authService.login(admin.getUserName(), admin.getPassWord());
            return ResultJson.ok(adminUserVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.failure(ResultCode.LOGIN_ERROR);
        }
    }

    @PostMapping("/register")
    @ApiOperation("注册租户管理员")
    @ApiVersion()
    @PreAuthorize("authentication.principal.adminType == 0")
    public ResultJson register(@RequestBody RegAdminDto admin) {
        try{
            return ResultJson.ok();
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @PostMapping(value = "/loginOut")
    @ApiOperation(value = "退出")
    @ApiVersion
    public ResultJson loginOut() {
        try {
            authService.loginOut();
            return ResultJson.ok();
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

}
