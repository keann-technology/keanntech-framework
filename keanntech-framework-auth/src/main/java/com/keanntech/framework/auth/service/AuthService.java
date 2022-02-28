package com.keanntech.framework.auth.service;

import com.keanntech.framework.auth.domain.vo.AdminUserVo;

/**
 * @Author
 * @Create 2022-01-26 14:36:16
 * @Desc ...
 */
public interface AuthService {

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    AdminUserVo login(String username, String password);

    /**
     * 退出
     */
    void loginOut();
}
