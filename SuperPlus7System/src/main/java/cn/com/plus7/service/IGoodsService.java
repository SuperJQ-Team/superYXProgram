package cn.com.plus7.service;

import cn.com.plus7.pojo.Goods;

import java.util.List;

public interface IGoodsService {
    List<Goods> list();
    List<Goods> list(Long user_id);
    int add(Goods goods);
    int update(Goods goods);
    int remove(Long id);
}
