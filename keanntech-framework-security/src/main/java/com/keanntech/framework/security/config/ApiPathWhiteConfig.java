package com.keanntech.framework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年01月27日 2:45 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "keanntech")
public class ApiPathWhiteConfig {

    private List<String> apiPathWhiteConfig;

}
