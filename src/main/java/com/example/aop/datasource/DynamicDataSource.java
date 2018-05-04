package com.example.aop.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为:====", DynamicDataSourceHolder.getDataSourceKey());
        System.out.println("数据源为:===="+DynamicDataSourceHolder.getDataSourceKey());
        return DynamicDataSourceHolder.getDataSourceKey();
    }
}
