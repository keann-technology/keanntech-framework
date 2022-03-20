package com.keanntech.framework.admin.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.annotation.datasource.DataSource;
import com.keanntech.framework.annotation.datasource.DataSourceKey;
import com.keanntech.framework.entity.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-03-04 16:31:25
 * @Desc ...
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(List<Role> list);

    /**
     * 批量更新（值为空的不操作）
     * @param list
     * @return
     */
    int updateBatchSelective(List<Role> list);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<Role> list);

    /**
     * 批量更新或插入
     * @param record
     * @return
     */
    int insertOrUpdate(Role record);

    /**
     * 批量更新或插入
     * @param record
     * @return
     */
    int insertOrUpdateSelective(Role record);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @DataSource(DataSourceKey.MASTER)
    Role findById(@Param("id") Long id);
}