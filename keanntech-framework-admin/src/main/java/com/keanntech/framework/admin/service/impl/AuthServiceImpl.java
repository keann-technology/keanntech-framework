package com.keanntech.framework.admin.service.impl;

import com.keanntech.framework.admin.domain.ResponseUserToken;
import com.keanntech.framework.admin.domain.UserDetail;
import com.keanntech.framework.admin.exception.CustomException;
import com.keanntech.framework.admin.mapper.AdminMapper;
import com.keanntech.framework.admin.service.AuthService;
import com.keanntech.framework.admin.utils.JwtUtils;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:36 下午
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtTokenUtil;
    private final AdminMapper adminMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtTokenUtil, AdminMapper adminMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.adminMapper = adminMapper;
    }

    @Override
    public ResponseUserToken login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        //存储token
        jwtTokenUtil.putToken(username, token);
        return new ResponseUserToken(token, userDetail);

    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}
