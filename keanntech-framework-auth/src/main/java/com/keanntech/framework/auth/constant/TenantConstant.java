package com.keanntech.framework.auth.constant;

/**
 * @Author
 * @Create 2022-02-28 15:14:40
 * @Desc ...
 */
public enum TenantConstant {

    /**
     * 系统默认租户CODE
     */
    DEFAULT_TENANT_CODE("000000");

    private String code;

    TenantConstant(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
