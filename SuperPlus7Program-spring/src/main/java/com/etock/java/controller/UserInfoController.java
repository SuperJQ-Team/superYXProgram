package com.etock.java.controller;

import com.etock.java.domain.AjaxResult;
import com.etock.java.entity.SysUser;
import com.etock.java.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult update(HttpServletRequest request, @RequestBody SysUser userInfo) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        userInfo.setId(sysUser.getId());
        int inf = userInfoService.update(userInfo);

        if (inf > 0) {
            sysUser = userInfoService.get(sysUser.getId());
            session.setAttribute("user", sysUser);
            return AjaxResult.success("更新用户信息成功!");
        } else {
            return AjaxResult.error("更新用户信息失败!");
        }
    }

    @GetMapping("/setRole")
    @ResponseBody
    public AjaxResult changeRole(HttpServletRequest request, Long id, String role) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        if (!Objects.equals(sysUser.getRole(), "administrator")) {
            return AjaxResult.error("权限不足!");
        }
        int inf = userInfoService.update(
                SysUser
                        .builder()
                        .id(id)
                        .role(role)
                        .build()
        );

        if (inf > 0) {
            sysUser = userInfoService.get(sysUser.getId());
            session.setAttribute("user", sysUser);
            return AjaxResult.success("更新用户权限成功!");
        } else {
            return AjaxResult.error("更新用户权限失败!");
        }
    }
}
