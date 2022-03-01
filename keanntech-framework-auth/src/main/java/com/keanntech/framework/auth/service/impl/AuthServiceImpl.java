package com.keanntech.framework.auth.service.impl;

import com.keanntech.framework.auth.domain.vo.AdminUserVo;
import com.keanntech.framework.auth.exception.CustomException;
import com.keanntech.framework.auth.service.AuthService;
import com.keanntech.framework.common.web.ResultCode;
import com.keanntech.framework.common.web.ResultJson;
import com.keanntech.framework.security.domain.UserDetail;
import com.keanntech.framework.security.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:36 下午
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public AdminUserVo login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetail);
        Set<GrantedAuthority> grantedAuthorities = userDetail.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toSet());
        return AdminUserVo.builder()
                .id(userDetail.getId())
                .userName(userDetail.getUsername())
                .tenantCode(userDetail.getTenantCode())
                .adminType(userDetail.getAdminType())
                .token(token)
                .refreshToken(refreshToken)
                .authorities(grantedAuthorities)
                .build();

    }

    @Override
    public void loginOut() {
        UserDetail userDetail = jwtTokenUtil.getUserDetailFromAuthContext();
        jwtTokenUtil.removeToken(userDetail.getId());
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            e.printStackTrace();
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}
