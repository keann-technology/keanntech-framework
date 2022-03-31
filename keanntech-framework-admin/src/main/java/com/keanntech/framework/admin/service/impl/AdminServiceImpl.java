package com.keanntech.framework.admin.service.impl;

import com.keanntech.framework.admin.mapper.admin.AdminMapper;
import com.keanntech.framework.admin.service.AdminService;
import com.keanntech.framework.entity.domain.Admin;
import org.springframework.stereotype.Service;

/**
 * @author miaoqingfu
 * @date 2022年03月19日 6:01 PM
 */
@Service
public class AdminServiceImpl implements AdminService {

    final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin findByUserName(String name) {
        Admin admin = adminMapper.findByUserName(name);
        return admin;
    }
}
