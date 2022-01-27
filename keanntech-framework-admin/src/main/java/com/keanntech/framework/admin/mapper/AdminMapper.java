package com.keanntech.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author
 * @Create 2022-01-25 16:57:46
 * @Desc ...
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    Admin findByUserName(@Param("userName") String userName);
}