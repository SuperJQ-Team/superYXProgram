package cn.com.plus7.controller;

import cn.com.plus7.domain.AjaxResult;
import cn.com.plus7.pojo.SysUser;
import cn.com.plus7.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    HttpSession session;

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult update(@RequestBody SysUser userInfo) {
        SysUser sysUser = (SysUser) session.getAttribute("user");
        userInfo.setId(sysUser.getId());
        int inf = userInfoService.updata(userInfo);

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
    public AjaxResult changeRole(Long id, String role) {
        SysUser sysUser = (SysUser) session.getAttribute("user");
        if (!Objects.equals(sysUser.getRole(), "administrator")) {
            return AjaxResult.error("权限不足!");
        }
        int inf = userInfoService.updata(
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
