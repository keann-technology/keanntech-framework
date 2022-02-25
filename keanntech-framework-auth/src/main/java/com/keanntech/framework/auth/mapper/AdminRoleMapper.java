package com.keanntech.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keanntech.framework.auth.domain.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Create 2022-01-26 09:39:24
 * @Desc ...
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    List<AdminRole> findRoleByUserId(@Param("userId") Long userId);

}