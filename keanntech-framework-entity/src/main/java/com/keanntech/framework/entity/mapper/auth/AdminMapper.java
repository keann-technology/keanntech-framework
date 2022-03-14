package com.keanntech.framework.entity.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.annotation.datasource.DataSource;
import com.keanntech.framework.annotation.datasource.DataSourceKey;
import com.keanntech.framework.entity.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-02-25 09:48:28
 * @Desc ...
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    int updateBatch(List<Admin> list);

    int updateBatchSelective(List<Admin> list);

    int batchInsert(@Param("list") List<Admin> list);

    int insertOrUpdate(Admin record);

    int insertOrUpdateSelective(Admin record);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @DataSource(value = DataSourceKey.MASTER)
    Admin findByUserName(@Param("userName") String userName);
}