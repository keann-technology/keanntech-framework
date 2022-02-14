package com.keanntech.framework.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
    @Author
    @Create 2022-02-11 15:34:49
    @Desc ...
*/

@ApiModel(value = "后台用户 ")
@Data
@TableName(value = "t_admin")
public class Admin implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "pass_word")
    @ApiModelProperty(value = "密码")
    private String passWord;

    /**
     * 头像
     */
    @TableField(value = "head_portrait")
    @ApiModelProperty(value = "头像")
    private String headPortrait;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 状态 1-启用0未启用
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "状态 1-启用0未启用")
    private Boolean enabled;

    /**
     * 过期 1-过期0-未过期
     */
    @TableField(value = "expired")
    @ApiModelProperty(value = "过期 1-过期0-未过期")
    private Boolean expired;

    /**
     * 锁定 1-锁定0-未锁定
     */
    @TableField(value = "locked")
    @ApiModelProperty(value = "锁定 1-锁定0-未锁定")
    private Boolean locked;

    /**
     * 是否超级管理员1-是0-否
     */
    @TableField(value = "super_admin")
    @ApiModelProperty(value = "是否超级管理员1-是0-否")
    private Boolean superAdmin;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 删除 1-删除0未删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value = "删除 1-删除0未删除")
    private Boolean delFlag;

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