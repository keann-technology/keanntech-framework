package com.keanntech.framework.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author miaoqingfu
 * @date 2022年02月28日 11:20 AM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserVo {

    private Long id;
    private String userName;
    private String tenantCode;
    private Integer adminType;
    private String token;
    private String refreshToken;
    private Set<GrantedAuthority> authorities;

}
