package com.keanntech.framework.auth.controller;

import com.keanntech.framework.auth.service.AuthService;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import com.keanntech.framework.entity.domain.dto.LoginAdminDto;
import com.keanntech.framework.entity.domain.dto.RegAdminDto;
import com.keanntech.framework.entity.domain.vo.AdminUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:28 下午
 */
@RestController
@Api("登录及刷新TOKEN")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultJson<AdminUserVo> login(@RequestBody LoginAdminDto admin){
        try {
            final AdminUserVo adminUserVo = authService.login(admin.getUserName(), admin.getPassWord());
            return ResultJson.ok(adminUserVo);
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.LOGIN_ERROR);
        }
    }

    @PostMapping("/register")
    @ApiOperation("注册租户管理员")
    public ResultJson register(@RequestBody RegAdminDto admin) {
        try{
            return ResultJson.ok();
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @GetMapping("/refreshToken")
    @ApiOperation("用刷新token换取token")
    public ResultJson<AdminUserVo> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        try {
            return ResultJson.ok(authService.refreshToken(refreshToken));
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @PostMapping(value = "/loginOut")
    @ApiOperation(value = "退出")
    public ResultJson loginOut() {
        try {
            authService.loginOut();
            return ResultJson.ok();
        } catch (Exception e) {
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

}
