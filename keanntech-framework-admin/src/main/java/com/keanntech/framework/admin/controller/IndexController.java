package com.keanntech.framework.admin.controller;

import com.keanntech.framework.common.web.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqingfu
 * @date 2022年01月25日 9:33 上午
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/login")
    public ResultJson login(String userName, String passWord) {
        return ResultJson.ok();
    }

}
