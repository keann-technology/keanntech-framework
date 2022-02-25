package com.keanntech.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.admin.domain.Admin;
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
    Admin findByUserName(@Param("userName") String userName);
}