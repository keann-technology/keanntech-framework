package com.keanntech.framework.auth.service;

import com.keanntech.framework.auth.domain.Admin;

/**
 * @Author
 * @Create 2022-02-14 08:33:22
 * @Desc ...
 */
public interface AdminService {

    /**
     * 注册管理员
     * @param admin
     * @return
     */
    int registerAdmin(Admin admin);

    /**
     * 管理员详情信息
     * @param id
     * @return
     */
    Admin adminDetail(Long id);

}
