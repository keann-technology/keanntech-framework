package com.keanntech.framework.adminapi;

import com.keanntech.framework.entity.domain.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author
 * @Create 2022-03-19 18:26:14
 * @Desc ...
 */
@FeignClient(value = "administrator-server")
public interface AdminFeignClient {

    /**
     * 根据用户名查询管理员信息
     * @param userName
     * @return
     */
    @GetMapping("/api/admin/v1/getAdminByUserName")
    Admin findAdminByUserNameV1(@RequestParam("userName") String userName);

}
