package com.example.aop.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    //这两个必须与DatasourceConfig类中dataSource() 方法中hashmap的key一致，他通过key来判断数据源
    private static final String MASTER = "masterDataSource";
    private static final String SLAVE = "slaveDataSource";
    private static final String[] defaultSlaveMethod = new String[]{ "query", "find", "get" };
    //切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
    @Pointcut("execution( * com.example.aop.service.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        // 获取到当前执行的方法名
        String methodName = joinPoint.getSignature().getName();
        boolean isSlave = false;
        isSlave = isSlave(methodName);
        System.out.println("是否从库："+isSlave);
        if (isSlave) {
            // 标记为读库
            DynamicDataSourceHolder.putDataSourceKey(SLAVE);
        } else {
            // 标记为写库
            DynamicDataSourceHolder.putDataSourceKey(MASTER);
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("执行完毕！");
        DynamicDataSourceHolder.removeDataSourceKey();
    }

    /**
     * 判断是否为读库
     *
     * @param methodName
     * @return
     */
    private Boolean isSlave(String methodName) {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName, defaultSlaveMethod);
    }

}
