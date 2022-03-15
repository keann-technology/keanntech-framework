package com.keanntech.framework.auth;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author miaoqingfu
 * @date 2022年02月25日 11:04 AM
 */
@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = DataSourceAutoConfiguration.class
)
@MapperScan(basePackages = "com.keanntech.framework.*.mapper.*")
@EnableLeafServer
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
