package com.keanntech.framework.auth.service.impl;

import com.keanntech.framework.auth.service.AdminService;
import com.keanntech.framework.entity.domain.Admin;
import com.keanntech.framework.entity.mapper.auth.AdminMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author miaoqingfu
 * @date 2022年02月14日 8:34 上午
 */
@Service
public class AdminServiceImpl implements AdminService {

    final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerAdmin(Admin admin) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        admin.setPassWord(bCryptPasswordEncoder.encode(admin.getPassWord()));
        return adminMapper.insert(admin);
    }

    @Override
    public Admin adminDetail(Long id) {
        return adminMapper.selectById(id);
    }
}
