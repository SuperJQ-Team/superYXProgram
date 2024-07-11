package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Carts;
import com.etoak.java.mapper.CartsMapper;
import com.etoak.java.service.ICartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl
        extends ServiceImpl<CartsMapper, Carts>
        implements ICartsService {
    @Autowired
    CartsMapper cartsMapper;

    @Override
    public String get(Long userId) {
        QueryWrapper<Carts> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);

        Carts cart = cartsMapper.selectOne(wrapper);

        if(cart != null)
            return cart.getItems();
        else
            return null;
    }

    @Override
    public int set(Long userId, String Items) {
        Carts cart = new Carts();
        cart.setUserId(userId);
        cart.setItems(Items);

        return cartsMapper.setCart(cart);
    }
}
