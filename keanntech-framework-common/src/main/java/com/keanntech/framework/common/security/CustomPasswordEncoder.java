package com.keanntech.framework.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author miaoqingfu
 * @date 2022年01月25日 10:09 上午
 */
public class CustomPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword, encodedPassword);
    }
}
