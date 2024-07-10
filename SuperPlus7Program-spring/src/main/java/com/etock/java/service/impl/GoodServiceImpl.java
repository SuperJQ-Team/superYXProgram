package com.etock.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etock.java.mapper.GoodsMapper;
import com.etock.java.entity.Goods;
import com.etock.java.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodServiceImpl
        extends ServiceImpl< GoodsMapper, Goods >
        implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> list() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",false);

        return goodsMapper.selectList(wrapper);
    }

    @Override
    public List<Goods> list(Long user_id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        wrapper.eq("user_id", user_id);
        return goodsMapper.selectList(wrapper);
    }

    @Override
    public int add(Goods orderForm) {
        return goodsMapper.insert(orderForm);
    }

    @Override
    public int update(Goods orderForm) {
        return goodsMapper.updateById(orderForm);
    }

    @Override
    public int remove(Long id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("del_flag",false);

        return goodsMapper.update(
                Goods
                        .builder()
                        .delFlag(true)
                        .updateBy("system")
                        .updateTime(new Date())
                        .build()
                , wrapper);
    }

}
