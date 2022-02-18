package com.keanntech.framework.common.annotation;

import com.keanntech.framework.common.DataSourceKey;

import java.lang.annotation.*;

/**
 * @Author
 * @Create 2022-02-17 15:53:35
 * @Desc ...
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceKey value() default DataSourceKey.MASTER;
}
