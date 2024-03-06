package cn.com.plus7.mapper;

import cn.com.plus7.pojo.Orders;
import cn.com.plus7.pojo.OrdersExample;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int insertList(List<Orders> orders);

    List<Map<String, Object>> selectNoDelWithOrder(Long id);
}