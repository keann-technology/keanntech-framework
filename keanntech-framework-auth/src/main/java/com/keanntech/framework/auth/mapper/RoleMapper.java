package com.keanntech.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.auth.domain.Role;
import com.keanntech.framework.common.DataSourceKey;
import com.keanntech.framework.common.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-03-01 10:35:25
 * @Desc ...
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    int updateBatch(List<Role> list);

    int updateBatchSelective(List<Role> list);

    int batchInsert(@Param("list") List<Role> list);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);

    @DataSource(DataSourceKey.SLAVE)
    Role findById(@Param("id") Long id);
}