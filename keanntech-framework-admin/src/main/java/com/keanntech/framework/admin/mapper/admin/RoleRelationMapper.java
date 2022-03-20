package com.keanntech.framework.admin.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.annotation.datasource.DataSource;
import com.keanntech.framework.annotation.datasource.DataSourceKey;
import com.keanntech.framework.entity.domain.RoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-03-04 16:32:18
 * @Desc ...
 */

@Mapper
public interface RoleRelationMapper extends BaseMapper<RoleRelation> {
    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(List<RoleRelation> list);

    /**
     * 批量更新(值为空的不被更新)
     * @param list
     * @return
     */
    int updateBatchSelective(List<RoleRelation> list);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<RoleRelation> list);

    /**
     * 插入或更新
     * @param record
     * @return
     */
    int insertOrUpdate(RoleRelation record);

    /**
     * 插入或更新(值为空的不操作)
     * @param record
     * @return
     */
    int insertOrUpdateSelective(RoleRelation record);

    /**
     * 查询给定用户的角色
     * @param userId
     * @return
     */
    @DataSource(value = DataSourceKey.MASTER)
    List<RoleRelation> findRoleByUserId(@Param("userId") Long userId);
}