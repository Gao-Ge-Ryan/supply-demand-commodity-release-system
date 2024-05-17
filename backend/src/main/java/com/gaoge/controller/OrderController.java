package com.gaoge.controller;

import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.dao.OrderDao;
import com.gaoge.entity.Order;
import com.gaoge.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 高歌
 * @Description 订单模块
 * @Date 2021-08-24
 */
@Api(tags = "订单模块接口")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    //查询所有订单
    @ApiOperation(value = "查询所有订单")
    @GetMapping
    public Result<List<Order>> selectAll() {
        List<Order> orders = orderService.selectAll();
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", orders);
    }

    //查询所有商品（货源)订单
    @ApiOperation(value = "分页查询所有货源(商品)订单")
    @GetMapping("/goods/{pageNum}")
    public Result<PageInfo> selectAllGoods(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAllGoods(pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //查询所有需求订单
    @ApiOperation(value = "分页查询所有需求订单")
    @GetMapping("/needs/{pageNum}")
    public Result<PageInfo> selectAllNeeds(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAllNeeds(pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //添加订单
    @ApiOperation(value = "添加订单")
    @PostMapping
    public Result<String> add(@Valid @RequestBody Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "添加订单失败",s);
        }
        orderService.add(order);
        return new Result(true, StatusCode.OK, "添加订单成功","添加成功");
    }

    //修改id订单
    @ApiOperation(value = "根据id修改订单")
    @PutMapping("/{id}")
    public Result<String> update(@Validated @RequestBody Order order,BindingResult bindingResult,
                         @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "修改订单失败",s);
        }
        orderService.update(order, id);
        return new Result(true, StatusCode.OK, "修改成功",null);
    }

    //删除订单
    @ApiOperation(value = "根据id删除订单")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
        return new Result(true, StatusCode.OK, "删除订单");
    }

    //根据用户名+类型查询订单
    @ApiOperation(value = "根据用户名+订单类型查询订单")
    @GetMapping("/search/{type}")
    public Result<List<Order>> selectByType(@PathVariable("type") String type) {
        List<Order> orders = orderService.selectByType(type);
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", orders);
    }

    //根据id查询订单
    @ApiOperation(value = "根据id查询订单")
    @GetMapping("/selectById/{id}")
    public Result<Order> selectById(@PathVariable("id") Integer id) {
        Order order = orderService.selectById(id);
        return new Result<Order>(true, StatusCode.OK, "查询成功", order);

    }

    //根据登录用户查询我买的订单
    @ApiOperation(value = "根据登录用户查询我买的订单")
    @GetMapping("/selectBuyByUserName")
    public Result<List<Order>> selectBuyByUserName() {
        List<Order> orders = orderService.selectBuyByUserName();
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", orders);

    }

    //根据登录用户查询我买的订单
    @ApiOperation(value = "根据登录用户查询我卖的订单")
    @GetMapping("/selectSellByUserName")
    public Result<List<Order>> selectSellByUserName() {
        List<Order> orders = orderService.selectSellByUserName();
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", orders);

    }


    //分页多条件搜索商品（货源）订单  不写了
    @ApiOperation(value = "分页多条件搜索商品（货源）订单  没写")
    @GetMapping("/search/goods/Example")
    public Result searchGooodsByExample() {
        return new Result(true, StatusCode.OK, "没写           ");
    }

    //分页多条件搜索需求订单  不写了
    @ApiOperation(value = "分页多条件搜索需求订单            没写")
    @GetMapping("/search/noods/Example")
    public Result searchNooodsByExample() {
        return new Result(true, StatusCode.OK, "没写");
    }

}
