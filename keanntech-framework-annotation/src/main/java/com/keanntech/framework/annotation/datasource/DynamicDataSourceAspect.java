package com.keanntech.framework.annotation.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author miaoqingfu
 * @date 2022年02月17日 4:39 下午
 */
@Component
@Aspect
@Slf4j
public class DynamicDataSourceAspect {

    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint joinPoint, DataSource dataSource){
        if ( !DynamicDataSourceContextHolder.containDataSourceKey( dataSource.value().getName() ) ) {
            log.error("DataSource [{}] doesn't exist, use default DataSource [{}]", dataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey( dataSource.value().getName() );
            log.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
        }
    }

    @After("@annotation(dataSource))")
    public void restoreDataSource(JoinPoint joinPoint,DataSource dataSource){
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
    }

}
