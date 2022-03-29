package com.keanntech.framework.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author miaoqingfu
 * @date 2022年03月19日 4:32 PM
 */
@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = { DataSourceAutoConfiguration.class }
)
@MapperScan(basePackages = "com.keanntech.framework.*.mapper.*")
@EnableDiscoveryClient
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
