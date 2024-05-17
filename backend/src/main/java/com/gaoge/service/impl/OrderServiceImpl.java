package com.gaoge.service.impl;

import com.gaoge.dao.OrderDao;
import com.gaoge.entity.Order;
import com.gaoge.entity.User;
import com.gaoge.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    //每页显示多条数据
    private  Integer pageSize = 6;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> selectAll() {
        return orderDao.selectAll();
    }

    @Override
    public PageInfo<Order> selectAllGoods(Integer pageNum) {
        Order order = new Order();
        order.setType("goods");
        Example example = createExample(order);
        pageSize=6;
        PageHelper.startPage(pageNum, pageSize);
        List<Order> goods = orderDao.selectByExample(example);
        PageInfo<Order> orderPageInfo = new PageInfo<>(goods);
        return orderPageInfo;
    }

    @Override
    public PageInfo<Order> selectAllNeeds(Integer pageNum) {
        Order order = new Order();
        order.setType("needs");
        Example example = createExample(order);
        pageSize=8;
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderDao.selectByExample(example);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }

    @Override
    public void add(Order order) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        order.setOwnName(name);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderDao.insertSelective(order);
    }

    @Override
    public void update(Order order, Integer id) {
        order.setUpdateTime(new Date());
        order.setOrderId(id);
       orderDao.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(Integer id) {
        orderDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Order> selectByType(String type) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Order order = new Order();
//        order.setOwnName(name);
//        order.setType(type);
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",type);
        criteria.andEqualTo("ownName",name);
        List<Order> orders = orderDao.selectByExample(example);
        return orders;
    }

    @Override
    public Order selectById(Integer id) {
        Order order = orderDao.selectByPrimaryKey(id);
        return order;
    }

    @Override
    public List<Order> selectBuyByUserName() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cooperationName",name);
        criteria.andEqualTo("orderStatu",1);
        List<Order> orders = orderDao.selectByExample(example);
        return orders;
    }

    @Override
    public List<Order> selectSellByUserName() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ownName",name);
        criteria.andEqualTo("orderStatu",1);
        List<Order> orders = orderDao.selectByExample(example);
        return orders;
    }

    //创建example
    public Example createExample(Order order) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if ("goods".equals(order.getType())) {
            criteria.andEqualTo("type", "goods");
        }
        if ("needs".equals(order.getType())) {
            criteria.andEqualTo("type", "needs");
        }
        return example;
    }
}
