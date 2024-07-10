package com.etoak.java.controller;

import com.etoak.java.domain.AjaxResult;
import com.etoak.java.entity.SysUser;
import com.etoak.java.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @GetMapping("/login")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        HttpSession session = request.getSession();
        log.info("username:" + username + "login.");
        if ("".equals(username)) {
            return AjaxResult.error("用户名不能为空");
        }
        if ("".equals(password)) {
            return AjaxResult.error("密码不能为空");
        }
        SysUser user = loginService.login(username, password);
        if (user == null) {
            return AjaxResult.error("用户名或密码错误");
        }
        user.setPassword(null);
        session.setAttribute("user", user);
        return AjaxResult.success(user);
    }

    @GetMapping("/logout")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return AjaxResult.success("登出成功");
    }

    @GetMapping("/signup")
    @ResponseBody
    public AjaxResult signup(String username, String password, String role) {

        int inf = loginService.signup(username, password, role);
        if (inf == -1) {
            return AjaxResult.error("用户名重复！");
        }if (inf > 0) {
            return AjaxResult.success("注册成功");
        } else {
            return AjaxResult.error("注册失败");
        }
    }


    @PostMapping("/writeoff")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request, String password) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        String username = user.getUserName();
        int inf = 0;
        if (Objects.equals(password, user.getPassword())) {
            inf = loginService.writeoff(username, password);
        }
        if (inf > 0) {
            return AjaxResult.success("注销成功");
        } else {
            return AjaxResult.error("注销成功");
        }
    }
}
