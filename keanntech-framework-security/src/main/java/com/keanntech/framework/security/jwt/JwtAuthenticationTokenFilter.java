package com.keanntech.framework.security.jwt;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.keanntech.framework.security.domain.UserDetail;
import com.keanntech.framework.security.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:28 上午
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @NacosValue(value = "${jwt.header}")
    private String jwtHeader;

    @NacosValue("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader(this.jwtHeader);
        final String authTokenStart = this.tokenHead;

        if (StringUtils.isNotEmpty(authToken)) {
            if (authToken.startsWith(authTokenStart)) {
                authToken = authToken.substring(authTokenStart.length());
            }
        } else {
            // 不按规范,不允许通过验证
            authToken = null;
        }

        UserDetail userDetail = jwtUtils.getUserDetailFromToken(authToken);

        if (userDetail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtils.validateToken(authToken, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated user %s, setting security context", userDetail));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
