package com.etoak.java.controller;

import com.etoak.java.domain.AjaxResult;
import com.etoak.java.entity.Orders;
import com.etoak.java.entity.SysUser;
import com.etoak.java.service.IGoodsService;
import com.etoak.java.service.IOrdersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrdersService orderFormService;

    @Autowired
    IGoodsService goodsService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        log.info(user == null ? "null" : user.toString());
        if (user == null) {
            return AjaxResult.error("用户未登录");
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 7;
        }
        try (Page<Orders> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<Map<String, Object>> list = orderFormService.map(user.getId());
            PageInfo<Map<String, Object>> info = new PageInfo<>(list);
            log.info("查询到用户{}的{}条订单信息", user.getUserName(), list.size());
            return AjaxResult.success(info);
        } catch (Exception e) {
            log.info("订单信息查询错误:" + Arrays.toString(e.getStackTrace()));
            return AjaxResult.error("查询错误");
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, @RequestBody Orders order) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        order.setId(null);
        order.setUserId(user.getId());
        order.setDelFlag(false);
        order.setCreatedBy("system");
        order.setCreatedTime(new Date());
        int inf = orderFormService.add(order);
        if (inf > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    @PostMapping("/addlist")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, @RequestBody List<Orders> orders) {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        log.info(user.toString());
        for (Orders order : orders) {
            order.setId(null);
            order.setUserId(user.getId());
            order.setDelFlag(false);
            order.setCreatedBy("system");
            order.setCreatedTime(new Date());
        }
        log.info(orders.toString());
        int inf = orderFormService.add(orders);
        if (inf > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(HttpServletRequest request, Orders orders) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        orders.setUserId(sysUser.getId());

        int inf = orderFormService.update(orders);
        if (inf > 0) {
            return AjaxResult.success("订单信息更新成功");
        } else {
            return AjaxResult.error("订单信息更新失败");
        }
    }

    @GetMapping("/remove")
    @ResponseBody
    public AjaxResult remove(HttpServletRequest request, Long id) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        int inf = orderFormService.remove(id, sysUser.getId());
        if (inf > 0) {
            return AjaxResult.success("订单信息删除成功");
        } else {
            return AjaxResult.error("订单信息删除失败");
        }
    }
}
