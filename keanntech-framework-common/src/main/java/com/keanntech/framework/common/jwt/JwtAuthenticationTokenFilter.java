package com.keanntech.framework.common.jwt;

import com.keanntech.framework.common.config.JwtConfig;
import com.keanntech.framework.common.domain.UserDetail;
import com.keanntech.framework.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private JwtConfig jwtConfig;

    @Resource
    private JwtUtils jwtUtils;

    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader(jwtConfig.getHeader());
        final String authTokenStart = jwtConfig.getTokenHead();

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
