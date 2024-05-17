package com.gaoge.controller;

import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.entity.Order;
import com.gaoge.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 高歌
 * @Description 购物车
 * @Date 2021-08-24
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车接口")
public class CartController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    //添加商品到购物车
    @ApiOperation("添加商品到购物车")
    @GetMapping("/add/{id}")
    public Result add(@PathVariable("id") Integer id) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        redisTemplate.boundListOps(name).leftPush(id);
//        redisTemplate.boundHashOps(name).put(id,id);
        redisTemplate.boundZSetOps(name).add(id, 100);
        return new Result(true, StatusCode.OK, "添加商品到购物车成功");
    }

    //从购物车删除商品
    @ApiOperation("从购物车删除商品")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        redisTemplate.boundHashOps(name).delete(id);
//        redisTemplate.boundListOps(name).remove(1, id);
        redisTemplate.boundZSetOps(name).remove(id);
        return new Result(true, StatusCode.OK, "删除商品成功");
    }

    //展示购物车列表
    @ApiOperation("展示购物车列表")
    @GetMapping("/show")
    public Result<List<Order>> show() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        redisTemplate.boundHashOps(name).delete(id);
//        Long size = redisTemplate.boundListOps(name).size();
//        List<Integer> list = redisTemplate.boundListOps(name).range(0, size);
        Long size = redisTemplate.boundZSetOps(name).size();
        Set<Integer> range = redisTemplate.boundZSetOps(name).range(0, size);
        ArrayList<Order> orders = new ArrayList<>();
//        LinkedHashSet<Integer> integers = new LinkedHashSet<>();
        BigDecimal totalMoney = new BigDecimal("0.00");
        for (Integer id : range) {
            Order order = orderService.selectById(id);
            if (order == null) {
                System.out.println("这个" + id + "商品已被删除,跳过展示");
                continue;
            }
            orders.add(order);
            totalMoney = totalMoney.add(new BigDecimal(order.getPrice()));
        }
        String totalMoneyStr = totalMoney.toString();
        return new Result<List<Order>>(true, StatusCode.OK, totalMoneyStr, orders);
    }
/**
 * 暂时没在这实现
 */
//    //提交订单
//    @ApiOperation("提交订单")
//    @GetMapping("/commitOrder")
//    public Result<Integer> commitOrder() {
//        //获取登陆的用户名
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = principal.getUsername();
//        ArrayList<Integer> integers = new ArrayList<>();
//        for (int i = 0; i < redisTemplate.boundZSetOps(name).size(); i++) {
//            //一个个获取redis里的数据
//            Set<Integer> range = redisTemplate.boundZSetOps(name).range(i, i + 1);
//            for (Integer id : range) {
//                Order order = orderService.selectById(id);
//                order.setCooperationName(name);
//                order.setOrderStatu(1);
//                orderService.update(order,order.getOrderId());
//                integers.add(id);
//            }
//        }
//        redisTemplate.boundZSetOps(name).remove(0,redisTemplate.boundZSetOps(name).size());
//        return new Result(true, StatusCode.OK, "提交成功", integers);
//    }
}
