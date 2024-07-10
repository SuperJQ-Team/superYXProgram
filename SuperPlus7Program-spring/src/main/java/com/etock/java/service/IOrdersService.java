package com.etock.java.service;

import com.etock.java.entity.Orders;

import java.util.List;
import java.util.Map;

public interface IOrdersService {
    List<Orders> list(Long userID);
    List<Map<String, Object>> map(Long userID);
    int add(Orders orders);
    int add(List<Orders> orders);
    int update(Orders orders);
    int remove(Long id, Long userid);
}
