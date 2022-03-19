package com.keanntech.framework.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author miaoqingfu
 * @date 2022年02月07日 9:39 上午
 */
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * 路径中版本的前缀， 这里用 /v[1-9]/的形式
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        log.info("combine方法({})",other);
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getServletPath());
        Integer version;
        if (m.find()) {
            version = Integer.valueOf(m.group(1));
            if (version >= this.apiVersion) {
                return this;
            }
        } else {
            return this;
        }
        log.info("compareTo方法({})",version);
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        log.info("compareTo方法({})",other);
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }
}
