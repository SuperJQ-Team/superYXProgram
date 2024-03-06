package cn.com.plus7.controller;

import cn.com.plus7.domain.AjaxResult;
import cn.com.plus7.pojo.Goods;
import cn.com.plus7.pojo.SysUser;
import cn.com.plus7.service.IGoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/goods")
public class GoodsConroller {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    HttpSession session;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(Integer pageNum, Integer pageSize) {
        SysUser user = (SysUser) session.getAttribute("user");
        log.info(user!=null?user.toString():"null");
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 7;
        }
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

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Goods orderForm) {
        SysUser user = (SysUser) session.getAttribute("user");
        orderForm.setId(null);
        orderForm.setDelFlag(false);
        orderForm.setCreatedBy("system");
        orderForm.setCreatedTime(new Date());
        int inf = goodsService.add(orderForm);
        if (inf > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Goods orderForm) {
        SysUser sysUser = (SysUser) session.getAttribute("user");

        int inf = goodsService.update(orderForm);
        if (inf > 0) {
            return AjaxResult.success("商品信息更新成功");
        } else {
            return AjaxResult.error("商品信息更新失败");
        }
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove( Long id) {
        SysUser sysUser = (SysUser) session.getAttribute("user");

        int inf = goodsService.remove(id);
        if (inf > 0) {
            return AjaxResult.success("商品信息删除成功");
        } else {
            return AjaxResult.error("商品信息删除失败");
        }
    }
}
