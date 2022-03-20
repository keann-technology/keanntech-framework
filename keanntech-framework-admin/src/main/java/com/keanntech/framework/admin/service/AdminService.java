package com.keanntech.framework.admin.service;

import com.keanntech.framework.entity.domain.Admin;

/**
 * @Author
 * @Create 2022-03-19 18:00:50
 * @Desc ...
 */
public interface AdminService {

    Admin findByUserName(String name);

}
