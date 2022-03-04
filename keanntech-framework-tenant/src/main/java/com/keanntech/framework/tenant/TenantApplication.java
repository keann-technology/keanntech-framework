package com.keanntech.framework.tenant;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author miaoqingfu
 * @date 2022年03月04日 9:21 AM
 */

@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = DataSourceAutoConfiguration.class
)
@MapperScan(basePackages = {"com.keanntech.framework.tenant.mapper"})
@EnableLeafServer
public class TenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantApplication.class, args);
    }

}
