package com.keanntech.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.auth.domain.Tenant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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