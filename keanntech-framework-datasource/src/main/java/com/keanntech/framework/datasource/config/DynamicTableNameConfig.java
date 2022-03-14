package com.keanntech.framework.datasource.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.keanntech.framework.datasource.handler.DynamicTableNameHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author miaoqingfu
 * @date 2022年02月25日 4:54 PM
 */
@Configuration
public class DynamicTableNameConfig {

    @Value("${keanntech.tenant.header}")
    private String tenantHeader;

    @Value("${keanntech.table-pre}")
    private String tablePre;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        DynamicTableNameHandler dynamicTableNameHandler = new DynamicTableNameHandler(this.tenantHeader, this.tablePre);
        dynamicTableNameInnerInterceptor.setTableNameHandler(dynamicTableNameHandler);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }

}
