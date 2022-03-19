package com.keanntech.framework.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author miaoqingfu
 * @date 2022年03月01日 3:33 PM
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Data
public class Knife4jProperties {

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    private Boolean enable;

    /**
     * 项目应用名
     */
    private String applicationName;

    /**
     * 项目版本信息
     */
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    private String applicationDescription;

    /**
     * 接口调试地址
     */
    private String tryHost;

}
