package com.keanntech.framework.admin.domain;

import com.keanntech.framework.security.domain.Roles;
import com.keanntech.framework.security.domain.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 9:05 上午
 */
public class UserDetail extends UserDetails implements Serializable {

    private long id;
    private String userName;
    private String passWord;
    private List<Roles> roleList;

    public UserDetail(long id, String userName, List<Roles> roleList, String passWord) {
        super(id, userName, roleList, passWord);
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.roleList = roleList;
    }


}
