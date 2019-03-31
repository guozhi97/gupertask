package com.mvc.controller;

import com.mvc.annotation.MyAntowired;
import com.mvc.annotation.MyController;
import com.mvc.annotation.MyRequestMapping;
import com.mvc.annotation.MyRequestParam;
import com.mvc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/user")
public class UserController
{
    @MyAntowired
    public UserService userService;
    
    @MyRequestMapping("/queryUser")
    public void queryUser(HttpServletRequest req, HttpServletResponse res, @MyRequestParam(value="name") String userName)
    {
        String result = userService.queryUser(userName);
        try
        {
            res.getWriter().write(result);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
    }
    
    @MyRequestMapping("/addUser")
    public void addUser(HttpServletRequest req, HttpServletResponse res, @MyRequestParam("name") String userName)
    {
        try
        {
            res.getWriter().write("Add User:" + userName + " is Successed.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
    }
}
