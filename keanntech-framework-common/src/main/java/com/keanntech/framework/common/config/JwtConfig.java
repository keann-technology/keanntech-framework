package com.keanntech.framework.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author miaoqingfu
 * @date 2022年03月04日 2:09 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String header;
    private String secret;
    private Long accessTokenExpiration;
    private Long refreshTokenExpiration;
    private String tokenHead;

}
