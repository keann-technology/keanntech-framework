package com.keanntech.framework.admin.service;

import com.keanntech.framework.admin.domain.ResponseUserToken;

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
    ResponseUserToken login(String username, String password);
}
