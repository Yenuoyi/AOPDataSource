package com.example.aop.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    //使用线程安全的ThreadLocal记录当前线程数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 设置数据库key
     * @param key
     */
    public static void putDataSourceKey(String key){
        log.debug("切换到{}数据源", key);
        System.out.println("数据源：" + key);
        holder.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey(){
        return holder.get();
    }

    /**
     * 删除数据源key
     */
    public static void removeDataSourceKey(){
        holder.remove();
    }
}
