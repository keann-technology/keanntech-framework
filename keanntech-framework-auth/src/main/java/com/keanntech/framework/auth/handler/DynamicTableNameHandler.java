package com.keanntech.framework.auth.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.keanntech.framework.common.utils.ServletUtils;

/**
 * @author miaoqingfu
 * @date 2022年02月25日 4:57 PM
 */
public class DynamicTableNameHandler implements TableNameHandler {

    private static final String DEFAULT_TENANT_CODE = "000000";

    private final String tenantHeader;
    private final String tablePre;

    public DynamicTableNameHandler(String tenantHeader, String tablePre) {
        this.tenantHeader = tenantHeader;
        this.tablePre = tablePre;
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        String tenantCode = ServletUtils.getRequest().getHeader(this.tenantHeader);
        String[] tableNameAry = tableName.split("_");
        if (DEFAULT_TENANT_CODE.equals(tenantCode)) {
            return this.tablePre + tableNameAry[2];
        }
        return this.tablePre + tenantCode + "_" + tableNameAry[2];
    }


}
