package com.etoak.java.controller;

import com.etoak.java.domain.AjaxResult;
import com.etoak.java.entity.Goods;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/carts")
public class CartsController {

    @Autowired


    @GetMapping("/list")
    @ResponseBody
    public AjaxResult get() {
        try (Page<Goods> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<Goods> list = goodsService.list();
            log.info("查询到{}条商品信息", list.size());
            PageInfo<Goods> info = new PageInfo<>(list);
            return AjaxResult.success(info);
        } catch (Exception e) {
            log.info("商品信息查询错误");
            return AjaxResult.error("查询错误");
        }
    }
}
