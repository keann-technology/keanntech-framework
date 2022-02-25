package com.keanntech.framework.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:32 下午
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {

    private String token;
    private UserDetail userDetail;

}
