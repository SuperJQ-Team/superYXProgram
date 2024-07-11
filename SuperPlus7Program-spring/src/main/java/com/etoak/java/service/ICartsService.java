package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Carts;

public interface ICartsService extends IService<Carts> {
    String get(Long userId);
    int set(Long userId, String Item);
}
