package com.cqu.sso.controller;

import com.cqu.sso.mapper.UserMapper;
import com.cqu.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;  //自动注入userMapper

    @PostMapping("login")  //表单提交的login
    public String Login(String username, String password, Model model, HttpServletRequest request){
        User correctUser=userMapper.queryUserByName(username);
        //判断用户名是否存在，不存在回到登录页
        if (correctUser==null){
            model.addAttribute("msg","此账号不存在");
            return "login";
        }
        //判断密码是否正确，不正确回到登录页
        else if (!password.equals(correctUser.getPassword())){
            model.addAttribute("msg","密码错误");
            return "login";
        }
        //jsp转发用户名并加入缓存
        else{
            model.addAttribute("username",username);
            request.getSession().setAttribute("username",username);
            return "main";
        }
    }

    @RequestMapping("")  //前往登录页
    public String begin(){
        return "login";
    }

    @RequestMapping("toLogin")  //注销并前往登录页
    public String toLogin(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @PostMapping("register")  //表单提交的register
    public String register(String username, String password, String password2, Model model){
        //两次密码不一致则回到注册页
        if (!password.equals(password2)){
            model.addAttribute("msg","两次密码不一致");
            return "register";
        }
        User existUser=userMapper.queryUserByName(username);
        //用户名已存在则回到注册页
        if (existUser!=null){
            model.addAttribute("msg","该用户名已存在");
            return "register";
        }
        //数据库中添加用户并回到登录页显示成功信息
        userMapper.addUser(new User(username,password));
        model.addAttribute("msg","注册成功");
        return "login";
    }

    @RequestMapping("toRegister")  //前往注册页
    public String toRegister(){
        return "register";
    }

    @RequestMapping("toMain")  //前往主页面，判断session里是否有用户名
    public String toMain(HttpServletRequest request,Model model){
        Object username=request.getSession().getAttribute("username");
        if (username==null){
            model.addAttribute("msg","请先登录");
            return "login";
        }
        else{
            model.addAttribute("username",(String)username);
            return "main";
        }
    }

    @RequestMapping("toA")  //前往A页面，判断session里是否有用户名
    public String toA(HttpServletRequest request,Model model){
        Object username=request.getSession().getAttribute("username");
        if (username==null){
            model.addAttribute("msg","请先登录");
            return "login";
        }
        else{
            model.addAttribute("username",(String)username);
            return "a";
        }
    }

    @RequestMapping("toB")  //前往B页面，判断session里是否有用户名
    public String toB(HttpServletRequest request,Model model){
        Object username=request.getSession().getAttribute("username");
        if (username==null){
            model.addAttribute("msg","请先登录");
            return "login";
        }
        else{
            model.addAttribute("username",(String)username);
            return "b";
        }
    }

}
