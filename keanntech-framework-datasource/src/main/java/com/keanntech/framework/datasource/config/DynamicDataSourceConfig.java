package com.keanntech.framework.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.keanntech.framework.annotation.datasource.DataSourceKey;
import com.keanntech.framework.annotation.datasource.DynamicDataSourceContextHolder;
import com.keanntech.framework.annotation.datasource.DynamicRoutingDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miaoqingfu
 * @date 2022年02月17日 4:06 下午
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {

        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceKey.MASTER.getName(),master());
        dataSourceMap.put(DataSourceKey.SLAVE.getName(),slave());

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        return new DynamicRoutingDataSource(master(), dataSourceMap);
    }

    /**
     * 事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }



}
