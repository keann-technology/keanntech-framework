package com.keanntech.framework.common;

/**
 * @Author
 * @Create 2022-02-17 16:03:13
 * @Desc ...
 */
public enum DataSourceKey {

    MASTER("master"),
    SLAVE("slave");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private DataSourceKey(String name) {
        this.name = name;
    }

}
