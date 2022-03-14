package com.keanntech.framework.entity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author miaoqingfu
 * @date 2022年02月28日 1:18 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAdminDto {

    private String userName;
    private String passWord;

}
