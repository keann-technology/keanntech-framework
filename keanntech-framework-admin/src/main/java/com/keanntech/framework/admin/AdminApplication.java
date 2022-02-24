package com.keanntech.framework.admin;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author miaoqingfu
 * @date 2022年01月07日 3:05 下午
 */
@SpringBootApplication(
        scanBasePackages = {"com.keanntech.framework"},
        exclude = DataSourceAutoConfiguration.class
)
@MapperScan(basePackages = {"com.keanntech.framework.admin.mapper"})
@EnableLeafServer
public class AdminApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath*:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath*:/META-INF/resources/webjars/");
    }
}
