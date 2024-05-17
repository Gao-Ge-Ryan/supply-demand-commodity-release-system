package com.gaoge.dao;

import com.gaoge.entity.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderDao extends Mapper<Order> {
}
