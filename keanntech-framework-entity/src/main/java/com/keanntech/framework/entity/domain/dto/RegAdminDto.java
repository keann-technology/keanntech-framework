package com.keanntech.framework.entity.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author miaoqingfu
 * @date 2022年03月04日 11:19 AM
 */
@Data
@ApiModel("租户管理员注册")
public class RegAdminDto {

    @ApiModelProperty(value = "租户CODE")
    private String tenantCode;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickName;

}
