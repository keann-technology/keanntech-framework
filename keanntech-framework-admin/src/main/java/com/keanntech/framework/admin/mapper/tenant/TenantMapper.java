package com.keanntech.framework.admin.mapper.tenant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.entity.domain.Tenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
    @Author
    @Create 2022-02-28 16:31:51
    @Desc ...
*/
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
    int updateBatch(List<Tenant> list);

    int updateBatchSelective(List<Tenant> list);

    int batchInsert(@Param("list") List<Tenant> list);

    int insertOrUpdate(Tenant record);

    int insertOrUpdateSelective(Tenant record);
}