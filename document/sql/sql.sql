CREATE TABLE t_admin
(
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    user_name     VARCHAR(32)  NOT NULL COMMENT '用户名',
    pass_word     VARCHAR(256) NOT NULL COMMENT '密码',
    head_portrait VARCHAR(256) DEFAULT '' COMMENT '头像',
    email         VARCHAR(32)  DEFAULT '' COMMENT '邮箱',
    mobile        VARCHAR(32)  DEFAULT '' COMMENT '手机',
    nick_name     VARCHAR(32)  DEFAULT '' COMMENT '昵称',
    enabled       tinyint(1)   DEFAULT 1 COMMENT '状态 1-启用0未启用',
    expired       tinyint(1)   DEFAULT 0 COMMENT '过期 1-过期0-未过期',
    locked        tinyint(1)   DEFAULT 0 COMMENT '锁定 1-锁定0-未锁定',
    remark        VARCHAR(256) DEFAULT '' COMMENT '备注',
    super_admin   tinyint(1)   DEFAULT 0 COMMENT '是否超级管理员 1-是0-否',
    del_flag      tinyint(1)   DEFAULT 0 COMMENT '删除 1-删除0未删除',
    created_by    VARCHAR(32)  DEFAULT '' COMMENT '创建人',
    created_time  DATETIME COMMENT '创建时间',
    updated_by    VARCHAR(32)  DEFAULT '' COMMENT '更新人',
    updated_time  DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '后台用户 ';
ALTER TABLE t_admin
    ADD UNIQUE unique_user_name (user_name);


CREATE TABLE t_admin_role
(
    id           BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    role_name    VARCHAR(32) NOT NULL COMMENT '角色名',
    role_code    VARCHAR(32) NOT NULL COMMENT '角色编码',
    description  VARCHAR(256) DEFAULT '' COMMENT '描述',
    admin_count  INT          DEFAULT 0 COMMENT '后台用户数',
    status       tinyint(1)   DEFAULT 0 COMMENT '状态 0-启用1-禁用',
    sort         INT          DEFAULT 1 COMMENT '排序',
    del_flag     tinyint(1)   DEFAULT 0 COMMENT '删除标识 0-未删除1-删除',
    created_by   VARCHAR(32)  DEFAULT '' COMMENT '创建人',
    created_time DATETIME COMMENT '创建时间',
    updated_by   VARCHAR(32)  DEFAULT '' COMMENT '更新人',
    updated_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '后台角色 ';
ALTER TABLE t_admin_role ADD UNIQUE unique_role (role_name);

CREATE TABLE t_admin_role_relation
(
    id           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    admin_id     BIGINT NOT NULL COMMENT '用户ID',
    role_id      BIGINT NOT NULL COMMENT '角色ID',
    created_by   VARCHAR(32) DEFAULT '' COMMENT '创建人',
    created_time DATETIME COMMENT '创建时间',
    updated_by   VARCHAR(32) DEFAULT '' COMMENT '更新人',
    updated_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '后台用户、角色关系';


CREATE TABLE t_keann_tenant
(
    id           BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    tenant_name  VARCHAR(32) NOT NULL COMMENT '租户名称',
    tenant_code  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '租户CODE',
    contacts     VARCHAR(32)          DEFAULT '' COMMENT '联系人',
    contacts_tel VARCHAR(32)          DEFAULT '' COMMENT '联系人电话',
    address      VARCHAR(32)          DEFAULT '' COMMENT '地址',
    domain       VARCHAR(32)          DEFAULT '' COMMENT '绑定域名',
    deleted      tinyint(1)           DEFAULT 0 COMMENT '是否删除 1-删除0未删除',
    status       tinyint(1)           DEFAULT 1 COMMENT '状态 1-启用0未启用',
    remark       VARCHAR(256)         DEFAULT '' COMMENT '备注',
    create_by    VARCHAR(32)          DEFAULT '' COMMENT '创建人',
    create_time  DATETIME COMMENT '创建时间',
    update_by    VARCHAR(32)          DEFAULT '' COMMENT '更新人',
    update_time  DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '租户表';
ALTER TABLE t_keann_tenant ADD UNIQUE unique_code (tenant_code);

CREATE TABLE t_keann_admin
(
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    tenant_code   VARCHAR(32)  NOT NULL COMMENT '租户CODE',
    user_name     VARCHAR(32)  NOT NULL COMMENT '用户名',
    pass_word     VARCHAR(256) NOT NULL COMMENT '密码',
    head_portrait VARCHAR(256) DEFAULT '' COMMENT '头像',
    email         VARCHAR(32)  DEFAULT '' COMMENT '邮箱',
    mobile        VARCHAR(32)  DEFAULT '' COMMENT '手机',
    nick_name     VARCHAR(32)  DEFAULT '' COMMENT '昵称',
    expired       tinyint(1)   DEFAULT 0 COMMENT '过期 1-过期0-未过期',
    locked        tinyint(1)   DEFAULT 0 COMMENT '锁定 1-锁定0-未锁定',
    enabled       tinyint(1)   DEFAULT 1 COMMENT '状态 1-启用0未启用',
    remark        VARCHAR(256) DEFAULT '' COMMENT '备注',
    admin_type    int          DEFAULT 1 COMMENT '管理员类型 0-超级管理员1-租户管理员',
    deleted       tinyint(1)   DEFAULT 0 COMMENT '删除 1-删除0未删除',
    created_by    VARCHAR(32)  DEFAULT '' COMMENT '创建人',
    created_time  DATETIME COMMENT '创建时间',
    updated_by    VARCHAR(32)  DEFAULT '' COMMENT '更新人',
    updated_time  DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '管理用户 ';
ALTER TABLE t_keann_admin ADD UNIQUE unique_name(user_name);

CREATE TABLE t_keann_tenantcode
(
    id          BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    tenant_code VARCHAR(32) NOT NULL COMMENT '租户CODE',
    used        tinyint(1)  NOT NULL DEFAULT 0 COMMENT '是否使用 0-未使用1使用',
    PRIMARY KEY (id)
) COMMENT = '租户CODE表 ';