package com.keanntech.framework.common.datasource;

import com.keanntech.framework.common.DataSourceKey;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年02月17日 4:24 下午
 */
public class DynamicDataSourceContextHolder {

    private static ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(() -> DataSourceKey.MASTER.getName());

    public static List<Object> dataSourceKeys = new ArrayList<Object>();

    public static void setDataSourceKey(String key){
        CONTEXT_HOLDER.set(key);
    }

    public static Object getDataSourceKey(){
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey(){
        CONTEXT_HOLDER.remove();
    }

    public static Boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }

}
