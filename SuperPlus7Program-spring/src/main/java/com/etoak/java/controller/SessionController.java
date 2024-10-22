package com.etoak.java.controller;

import com.etoak.java.domain.AjaxResult;
import com.etoak.java.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/user")
    @ResponseBody
    public AjaxResult get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        if (user != null) {
            return AjaxResult.success(user);
        } else {
            return AjaxResult.error(user);
        }
    }
}
