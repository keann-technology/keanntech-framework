package com.keanntech.framework.auth.config;

import com.keanntech.framework.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:21 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthWebSecurityConfig extends WebSecurityConfig {
}
