package com.keanntech.framework.admin.controller;

import com.keanntech.framework.admin.domain.Admin;
import com.keanntech.framework.admin.domain.ResponseUserToken;
import com.keanntech.framework.admin.service.AuthService;
import com.keanntech.framework.common.annotation.ApiVersion;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/admin/{version}")
public class AuthController {

    private final AuthService authService;

    @Value("${jwt.header}")
    private String tokenHeader;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    @ApiVersion()
    public ResultJson<ResponseUserToken> login(@RequestBody Admin admin){
        try {
            final ResponseUserToken response = authService.login(admin.getUserName(), admin.getPassWord());
            return ResultJson.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.failure(ResultCode.LOGIN_ERROR);
        }
    }

}
