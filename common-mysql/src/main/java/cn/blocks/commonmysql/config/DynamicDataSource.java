package cn.blocks.commonmysql.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description
 *          动态datasource
 *
 * @author Somnus丶y
 * @date 2019/9/10 12:20
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
