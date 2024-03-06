package cn.com.plus7.controller;

import cn.com.plus7.domain.AjaxResult;
import cn.com.plus7.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/session")
public class SessionController {

    @Autowired
    HttpSession session;

    @GetMapping("/user")
    @ResponseBody
    public AjaxResult get() {
        SysUser user = (SysUser) session.getAttribute("user");
        if (user != null) {
            return AjaxResult.success(user);
        } else {
            return AjaxResult.error(user);
        }
    }
}
