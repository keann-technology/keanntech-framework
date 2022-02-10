package com.keanntech.framework.admin.config;

import com.keanntech.framework.security.jwt.JwtAuthenticationTokenFilter;
import com.keanntech.framework.security.config.ApiPathWhiteConfig;
import com.keanntech.framework.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:21 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AdminWebSecurityConfig extends WebSecurityConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;
    private final ApiPathWhiteConfig apiPathWhiteConfig;

    public AdminWebSecurityConfig(@Qualifier("jwtAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint,
                                  @Qualifier("restAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
                                  @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
                                  JwtAuthenticationTokenFilter authenticationTokenFilter, ApiPathWhiteConfig apiPathWhiteConfig) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.authenticationTokenFilter = authenticationTokenFilter;
        this.apiPathWhiteConfig = apiPathWhiteConfig;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(apiPathWhiteConfig.getApiPathWhiteConfig().toArray(new String[apiPathWhiteConfig.getApiPathWhiteConfig().size()])).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(apiPathWhiteConfig.getApiPathWhiteConfig().toArray(new String[apiPathWhiteConfig.getApiPathWhiteConfig().size()]));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }
}
