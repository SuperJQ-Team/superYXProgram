package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Orders;
import com.etoak.java.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface IOrdersService extends IService<Orders> {
    List<Orders> list(Long userID);
    List<Map<String, Object>> map(Long userID);
    int add(Orders orders);
    int add(List<Orders> orders);
    int update(Orders orders);
    int remove(Long id, Long userid);
}
