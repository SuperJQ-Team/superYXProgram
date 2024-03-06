package cn.com.plus7.service;

import cn.com.plus7.pojo.Orders;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IOrdersService {
    List<Orders> list(Long userID);
    List<Map<String, Object>> map(Long userID);
    int add(Orders orders);
    int add(List<Orders> orders);
    int update(Orders orders);
    int remove(Long id, Long userid);
}
