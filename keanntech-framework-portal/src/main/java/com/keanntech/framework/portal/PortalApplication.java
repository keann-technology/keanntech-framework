package com.keanntech.framework.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author miaoqingfu
 * @date 2022年03月07日 11:14 AM
 */
@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = DataSourceAutoConfiguration.class

)
@MapperScan(basePackages = "com.keanntech.framework.*.mapper.*")
@EnableDiscoveryClient
@EnableFeignClients
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

}
