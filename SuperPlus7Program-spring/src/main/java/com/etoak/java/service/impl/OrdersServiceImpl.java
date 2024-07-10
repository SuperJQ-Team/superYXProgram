package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.mapper.OrdersMapper;
import com.etoak.java.service.IOrdersService;
import com.etoak.java.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl
        extends ServiceImpl<OrdersMapper, Orders>
        implements IOrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orders> list(Long userID) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userID);
        wrapper.eq("del_flag", false);

        return ordersMapper.selectList(wrapper);
    }

    public List<Map<String, Object>> map(Long userID){
        return ordersMapper.selectNoDelWithOrder(userID);
    }

    @Override
    public int add(Orders orders) {
        return ordersMapper.insert(orders);
    }

    @Override
    public int add(List<Orders> list) {
        return ordersMapper.insertList(list);
    }

    @Override
    public int update(Orders orders) {
        orders.setUserId(null);

        return ordersMapper.updateById(orders);
    }

    @Override
    public int remove(Long id, Long userid) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("user_id", userid);
        wrapper.eq("del_flag", false);

        return ordersMapper.update(
                Orders
                        .builder()
                        .delFlag(true)
                        .updateBy("system")
                        .updateTime(new Date())
                        .build()
                , wrapper);
    }
}
