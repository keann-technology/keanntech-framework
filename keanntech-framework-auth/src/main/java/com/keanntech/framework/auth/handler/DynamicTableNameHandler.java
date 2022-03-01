package com.keanntech.framework.auth.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.keanntech.framework.common.DataSourceKey;
import com.keanntech.framework.common.datasource.DynamicDataSourceContextHolder;
import com.keanntech.framework.common.utils.ServletUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author miaoqingfu
 * @date 2022年02月25日 4:57 PM
 */
public class DynamicTableNameHandler implements TableNameHandler {

    private final String tenantHeader;
    private final String tablePre;

    public DynamicTableNameHandler(String tenantHeader, String tablePre) {
        this.tenantHeader = tenantHeader;
        this.tablePre = tablePre;
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        String tenantCode = ServletUtils.getRequest().getHeader(this.tenantHeader);
        Object dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        if (StringUtils.isEmpty(tenantCode) || dataSourceKey.equals(DataSourceKey.MASTER.getName())) {
            return tableName;
        }
        String subTableName = tableName.substring(tablePre.length());
        return this.tablePre + tenantCode + "_" + subTableName;
    }


}
