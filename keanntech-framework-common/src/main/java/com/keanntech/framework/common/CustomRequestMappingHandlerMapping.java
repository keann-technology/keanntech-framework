package com.keanntech.framework.common;

import com.keanntech.framework.common.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author miaoqingfu
 * @date 2022年02月07日 9:42 上午
 */
@Slf4j
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return createCondition(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return createCondition(AnnotationUtils.findAnnotation(method,ApiVersion.class));
    }

    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        log.info("开始构建请求条件({})",apiVersion);
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }

}
