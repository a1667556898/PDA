package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 21/03/2018.
 */

public class UserBean implements Serializable {

    /**
     * token : sdadasd223dfgg41fwertyuw245wrth5
     * userRole : 1
     */

    private String token;
    private int userRole;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}
