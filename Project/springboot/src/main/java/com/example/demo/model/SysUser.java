/*******************************************************************************
 * Copyright (c) 2019 - 3 -20 我公司
 ******************************************************************************/

package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class SysUser
{

    private String userid;
    private String loginname;
    private String username;

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }


    public String getLoginname()
    {
        return loginname;
    }

    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
