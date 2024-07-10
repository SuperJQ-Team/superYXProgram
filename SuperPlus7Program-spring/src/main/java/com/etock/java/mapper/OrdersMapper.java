package com.etock.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etock.java.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    List<Map<String, Object>> selectNoDelWithOrder(@Param(value = "id") Long userID);
    int insertList(List<Orders> list);
}