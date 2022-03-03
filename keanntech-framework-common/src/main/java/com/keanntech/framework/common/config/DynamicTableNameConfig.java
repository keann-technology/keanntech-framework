package com.keanntech.framework.common.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.keanntech.framework.common.handler.DynamicTableNameHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author miaoqingfu
 * @date 2022年02月25日 4:54 PM
 */
@Configuration
public class DynamicTableNameConfig {

    @NacosValue("${keanntech.tenant.header}")
    private String tenantHeader;

    @NacosValue("${keanntech.table-pre}")
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
