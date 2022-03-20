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

    @GetMapping("/api/admin/v1/getByUserName")
    Admin findByUserNameV1(@RequestParam("name") String name);

}
