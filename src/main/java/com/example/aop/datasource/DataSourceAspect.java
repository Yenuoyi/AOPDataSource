package com.example.aop.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 通过切面以及注解判断是否选择数据源
 * @author yebing
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 切入点
     */
    @Pointcut("execution( * com.example.aop.service.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getTarget().getClass();
        DataSource classAnnotation =  aClass.getAnnotation(DataSource.class);
        if(classAnnotation != null){
            String dataSource = classAnnotation.value();
            logger.info("This is datasource："+dataSource);
            DynamicDataSource.putDataSourceKey(dataSource);
        }else{
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            DataSource methodAnnotation = method.getAnnotation(DataSource.class);
            if(methodAnnotation != null){
                String dataSource = methodAnnotation.value();
                logger.info("This is datasource："+dataSource);
                DynamicDataSource.putDataSourceKey(dataSource);
            }

        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     * @param joinPoint
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        logger.info("执行完毕！");
        DynamicDataSource.removeDataSourceKey();
    }

}
