package com.keanntech.framework.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    @Author
    @Create 2022-01-26 11:16:58
    @Desc ...
*/
/**
    * 后台用户、角色关系
    */
@ApiModel(value="后台用户、角色关系")
@Data
@TableName(value = "t_admin_role_relation")
public class AdminRoleRelation implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "admin_id")
    @ApiModelProperty(value="用户ID")
    private Long adminId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    @ApiModelProperty(value="创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @ApiModelProperty(value="创建时间")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    @ApiModelProperty(value="更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    @ApiModelProperty(value="更新时间")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}