package com.etock.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etock.java.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}