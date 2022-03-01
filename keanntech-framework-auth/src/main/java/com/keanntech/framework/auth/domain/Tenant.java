package com.keanntech.framework.auth.domain;

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
import java.util.Date;

/**
    @Author
    @Create 2022-02-28 16:31:51
    @Desc ...
*/

@ApiModel(value="租户表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_keann_tenant")
public class Tenant implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 租户名称
     */
    @TableField(value = "tenant_name")
    @ApiModelProperty(value="租户名称")
    private String tenantName;

    /**
     * 租户CODE
     */
    @TableField(value = "tenant_code")
    @ApiModelProperty(value="租户CODE")
    private String tenantCode;

    /**
     * 联系人
     */
    @TableField(value = "contacts")
    @ApiModelProperty(value="联系人")
    private String contacts;

    /**
     * 联系人电话
     */
    @TableField(value = "contacts_tel")
    @ApiModelProperty(value="联系人电话")
    private String contactsTel;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * 绑定域名
     */
    @TableField(value = "`domain`")
    @ApiModelProperty(value="绑定域名")
    private String domain;

    /**
     * 是否删除 1-删除0未删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value="是否删除 1-删除0未删除")
    private Boolean deleted;

    /**
     * 状态 1-启用0未启用
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态 1-启用0未启用")
    private Boolean status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}