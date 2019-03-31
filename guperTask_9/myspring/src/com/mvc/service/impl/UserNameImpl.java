package com.mvc.service.impl;


import com.mvc.annotation.MyService;
import com.mvc.service.UserService;

@MyService("userService")
public class UserNameImpl implements UserService
{
    @Override
    public String queryUser(String userName)
    {
        return "Hello,My name is " + userName;
    }

}
