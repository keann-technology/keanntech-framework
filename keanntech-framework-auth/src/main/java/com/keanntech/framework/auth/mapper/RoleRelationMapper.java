package com.keanntech.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.auth.domain.RoleRelation;
import com.keanntech.framework.common.DataSourceKey;
import com.keanntech.framework.common.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-03-01 10:42:20
 * @Desc ...
 */
@Mapper
public interface RoleRelationMapper extends BaseMapper<RoleRelation> {
    int updateBatch(List<RoleRelation> list);

    int updateBatchSelective(List<RoleRelation> list);

    int batchInsert(@Param("list") List<RoleRelation> list);

    int insertOrUpdate(RoleRelation record);

    int insertOrUpdateSelective(RoleRelation record);

    /**
     * 查询给定用户的角色
     * @param userId
     * @return
     */
    @DataSource(value = DataSourceKey.SLAVE)
    List<RoleRelation> findRoleByUserId(@Param("userId") Long userId);
}