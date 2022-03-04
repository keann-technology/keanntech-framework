package com.keanntech.framework.tenant.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    @Author
    @Create 2022-02-28 16:32:17
    @Desc ...
*/

@ApiModel(value="租户CODE表 ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_keann_tenantcode")
public class Tenantcode implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 租户CODE
     */
    @TableField(value = "tenant_code")
    @ApiModelProperty(value="租户CODE")
    private String tenantCode;

    /**
     * 是否使用 0-未使用1使用
     */
    @TableField(value = "used")
    @ApiModelProperty(value="是否使用 0-未使用1使用")
    private Boolean used;

    private static final long serialVersionUID = 1L;
}