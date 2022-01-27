package com.keanntech.framework.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author miaoqingfu
 * @date 2022年01月07日 3:05 下午
 */
@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = DataSourceAutoConfiguration.class
)
@MapperScan(basePackages = {"com.keanntech.framework.admin.mapper"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
