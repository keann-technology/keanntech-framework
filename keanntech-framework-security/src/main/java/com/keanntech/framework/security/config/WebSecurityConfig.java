package com.keanntech.framework.security.config;

import com.keanntech.framework.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author miaoqingfu
 * @date 2022年01月25日 9:47 上午
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("jwtAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("restAuthenticationAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private ApiPathWhiteConfig apiPathWhiteConfig;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(apiPathWhiteConfig.getApiPathWhiteConfig().toArray(new String[apiPathWhiteConfig.getApiPathWhiteConfig().size()])).anonymous()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(apiPathWhiteConfig.getApiPathWhiteConfig().toArray(new String[apiPathWhiteConfig.getApiPathWhiteConfig().size()]));
    }

}
