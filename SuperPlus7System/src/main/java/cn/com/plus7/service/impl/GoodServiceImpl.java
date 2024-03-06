package cn.com.plus7.service.impl;

import cn.com.plus7.mapper.GoodsMapper;
import cn.com.plus7.pojo.Goods;
import cn.com.plus7.pojo.GoodsExample;
import cn.com.plus7.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodServiceImpl implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> list() {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(false);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<Goods> list(Long user_id) {
       return goodsMapper.selectNoDelWithOrder(user_id);
    }

    @Override
    public int add(Goods orderForm) {
        return goodsMapper.insertSelective(orderForm);
    }

    @Override
    public int update(Goods orderForm) {
        Long id = orderForm.getId();
        orderForm.setId(null);

        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        return goodsMapper.updateByExampleSelective(orderForm, example);
    }

    @Override
    public int remove(Long id) {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDelFlagEqualTo(false);

        return goodsMapper.updateByExampleSelective(
                Goods
                        .builder()
                        .delFlag(true)
                        .updateBy("system")
                        .updateTime(new Date())
                        .build()
                , example);
    }

}
