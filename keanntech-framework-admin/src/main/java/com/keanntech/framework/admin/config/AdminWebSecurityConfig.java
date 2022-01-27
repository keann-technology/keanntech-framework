package com.keanntech.framework.admin.config;

import com.keanntech.framework.admin.jwt.JwtAuthenticationTokenFilter;
import com.keanntech.framework.security.CustomPasswordEncoder;
import com.keanntech.framework.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public AdminWebSecurityConfig(@Qualifier("jwtAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint,
                                  @Qualifier("restAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
                                  @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
                                  JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/csrf",
                        "/service-status/v1/task/status",
                        "/swagger-ui.html",
                        "/*.html",
                        "/*.js",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.png",
                        "/webjars/**",
                        "/configuration/**",
                        "/v2/**",
                        "/swagger-resources/**",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/api/v1/login").permitAll()
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
        web
                .ignoring()
                .antMatchers(
                        "swagger-ui.html",
                        "**/swagger-ui.html",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.gif",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/**/*.ttf"
                );
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/api/v1/login"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
}
