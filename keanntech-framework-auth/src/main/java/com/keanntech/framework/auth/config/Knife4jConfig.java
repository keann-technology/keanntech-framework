package com.keanntech.framework.auth.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:09 下午
 */
@EnableOpenApi
@EnableKnife4j
@Configuration
public class Knife4jConfig {

    private final Knife4jProperties knife4jProperties;
    private static final Set<String> protocolsSet = new HashSet<>();

    static {
        protocolsSet.add("https");
        protocolsSet.add("http");

    }

    public Knife4jConfig(Knife4jProperties knife4jProperties) {
        this.knife4jProperties = knife4jProperties;
    }

    @Bean(value = "authRestApi")
    public Docket authRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .groupName("权限认证服务")
                //定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(knife4jProperties.getEnable())
                //将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(authApiInfo())
                //接口调试地址
                .host(knife4jProperties.getTryHost())
                //选择哪些接口作为swagger的doc发布
                .select()
                //表示任何包
                .apis(RequestHandlerSelectors.basePackage("com.keanntech.framework.auth"))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(protocolsSet)
                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())
                // 授权信息全局应用
                .securityContexts(securityContexts());
    }

    private ApiInfo authApiInfo() {
        return new ApiInfoBuilder()
                .title("权限认证服务")
                .description(knife4jProperties.getApplicationDescription())
                .termsOfServiceUrl(knife4jProperties.getTryHost())
                .contact(new Contact("keanntech", knife4jProperties.getTryHost(), "keanntech@aliyun.com"))
                .version("Application Version: " + knife4jProperties.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

}
