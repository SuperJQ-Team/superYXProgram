package cn.com.plus7.service.impl;

import cn.com.plus7.mapper.OrdersMapper;
import cn.com.plus7.pojo.Orders;
import cn.com.plus7.pojo.OrdersExample;
import cn.com.plus7.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orders> list(Long userID) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        criteria.andDelFlagEqualTo(false);

        return ordersMapper.selectByExample(example);
    }

    public List<Map<String, Object>> map(Long userID){
        return ordersMapper.selectNoDelWithOrder(userID);
    }

    @Override
    public int add(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    @Override
    public int add(List<Orders> list) {
        return ordersMapper.insertList(list);
    }

    @Override
    public int update(Orders orders) {
        Long id = orders.getId();
        orders.setId(null);
        orders.setUserId(null);

        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        return ordersMapper.updateByExampleSelective(orders, example);
    }

    @Override
    public int remove(Long id, Long userid) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andUserIdEqualTo(userid);
        criteria.andDelFlagEqualTo(false);

        return ordersMapper.updateByExampleSelective(
                Orders
                        .builder()
                        .delFlag(true)
                        .updateBy("system")
                        .updateTime(new Date())
                        .build()
                , example);
    }
}
