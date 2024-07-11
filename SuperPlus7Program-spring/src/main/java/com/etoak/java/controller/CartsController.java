package com.etoak.java.controller;

import com.etoak.java.domain.AjaxResult;
import com.etoak.java.entity.Goods;
import com.etoak.java.entity.SysUser;
import com.etoak.java.service.ICartsService;
import com.etoak.java.service.impl.CartServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/carts")
public class CartsController {

    @Autowired
    ICartsService cartsService;

    @GetMapping("/get")
    @ResponseBody
    public AjaxResult get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        try{
            String item = cartsService.get(user.getId());
            return AjaxResult
                    .builder()
                    .data(Objects.requireNonNullElse(item, ""))
                    .code(200)
                    .msg("查询成功")
                    .build();
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return AjaxResult.error("查询错误");
        }
    }

    @PostMapping("/set")
    @ResponseBody
    public AjaxResult set(HttpServletRequest request, @RequestBody String items){
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        try{
            int info = cartsService.set(user.getId(), items);
            if(info > 0) {
                return AjaxResult.success(null);
            } else {
                return AjaxResult.error("更新购物车信息错误");
            }
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return AjaxResult.error("更新购物车信息错误");
        }
    }
}
