package com.gaoge.service;

import com.gaoge.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {
    List<Order> selectAll();

    PageInfo<Order> selectAllGoods(Integer pageNum);

    PageInfo<Order> selectAllNeeds(Integer pageNum);

    void add(Order order);

    void update(Order order, Integer id);

    void delete(Integer id);

    List<Order> selectByType(String type);

    Order selectById(Integer id);

    List<Order> selectBuyByUserName();

    List<Order> selectSellByUserName();
}
