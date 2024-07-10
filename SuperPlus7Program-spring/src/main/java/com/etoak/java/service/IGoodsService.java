package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Goods;

import java.util.List;

public interface IGoodsService extends IService<Goods> {
    List<Goods> list();
    List<Goods> list(Long user_id);
    int add(Goods goods);
    int update(Goods goods);
    int remove(Long id);
}
