package com.keanntech.framework.admin.mapper.tenant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.annotation.datasource.DataSource;
import com.keanntech.framework.annotation.datasource.DataSourceKey;
import com.keanntech.framework.entity.domain.Tenantcode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-02-28 16:32:17
 * @Desc ...
 */
@Mapper
public interface TenantcodeMapper extends BaseMapper<Tenantcode> {
    int updateBatch(List<Tenantcode> list);

    int updateBatchSelective(List<Tenantcode> list);

    int batchInsert(@Param("list") List<Tenantcode> list);

    int insertOrUpdate(Tenantcode record);

    int insertOrUpdateSelective(Tenantcode record);

    int countByUsed(@Param("used") Integer used);

    /**
     * 查询一个未使用的租户CODE
     * @return
     */
    @DataSource(value = DataSourceKey.MASTER)
    Tenantcode getUnusedTenantCode();
}