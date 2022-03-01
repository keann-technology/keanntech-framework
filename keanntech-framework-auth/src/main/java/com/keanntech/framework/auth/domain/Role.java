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
    @Create 2022-03-01 10:35:25
    @Desc ...
*/

@ApiModel(value = "后台角色 ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_keann_role")
public class Role implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 角色编码
     */
    @TableField(value = "role_code")
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 状态 0-启用 1-禁用
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态 0-启用 1-禁用")
    private Boolean status;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 删除标识 0-未删除 1-删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value = "删除标识 0-未删除 1-删除")
    private Boolean deleted;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}