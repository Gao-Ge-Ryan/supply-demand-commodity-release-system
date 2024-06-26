package com.gaoge.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.dao.OrderDao;
import com.gaoge.entity.Address;
import com.gaoge.entity.Order;
import com.gaoge.security.service.JwtUserDetailsServiceImpl;
import com.gaoge.security.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Set;
import java.util.UUID;

/**
 * @author 高歌
 * @Description 支付宝支付
 * @Date 2021-08-24
 */
@RestController
@Api(tags = "支付模块接口")
public class AlipayController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    //支付宝账号信息
    @Value("${alipay.info.appId}")
    private String appId;
    @Value("${alipay.info.privateKey}")
    private String privateKey;
    @Value("${alipay.info.alipayPublicKey}")
    private String alipayPublicKey;

    /**
     * 这么写会有线程安全问题，待完善。
     */
    //收货人：地址
    private Address addressSuccess;
    //支付人姓名获取
    private String tokenStorage;


    @ApiOperation(value = "支付路径")
    @PostMapping("/alipay/{total_amount}")
    public String ailpay(@PathVariable("total_amount") String total_amount,
                         @RequestBody Address address, HttpServletRequest httpServletRequest)
            throws IOException {
        addressSuccess = address;
        String token = httpServletRequest.getHeader(JwtTokenUtil.getHeader());
        tokenStorage = token;
//        byte[] decode = Base64.getDecoder().decode(appId);
//        appId = new String(decode);
//        byte[] decode1 = Base64.getDecoder().decode(privateKey);
//        privateKey = new String(decode1);
//        byte[] decode2 = Base64.getDecoder().decode(alipayPublicKey);
//        alipayPublicKey = new String(decode2);
        //去沙箱里面找自己的
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do",
                appId,
                privateKey,  //私钥 不知道是什么 往上面看 配置沙箱密钥的时候 自己保存的
                "json", "utf-8",
                alipayPublicKey, "RSA2");
        //订单号  自定义
        String out_trade_no = UUID.randomUUID().toString().replace("-", "");
        out_trade_no = URLDecoder.decode(out_trade_no, "UTF-8");
//        String total_amount = "200";
        total_amount = URLDecoder.decode(total_amount, "UTF-8");
        String subject = "标题";
        subject = URLDecoder.decode(subject, "UTF-8");
        String body = "描述";
        body = URLDecoder.decode(body, "UTF-8");
        String passback_params = token;
        passback_params = URLDecoder.decode(passback_params, "UTF-8");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //同步通知
        alipayRequest.setReturnUrl("http://q91h457687.goho.co/paySuccessful");
        //异步通知
        alipayRequest.setNotifyUrl("支付成功去接口处理东西【必须外网能访问】");
        //配置参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                "    \"passback_params\":\"" + passback_params + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + total_amount + "," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"body\":\"" + body + "\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //form就是一个表单 html 直接给前端 替换 body标签里面的东西
        return form;
    }

    @ApiOperation("/支付成功跳转")
    @GetMapping("/paySuccessful")
    public Result paySuccessful() {
        Address address = addressSuccess;
        //将地址对象变成字符串
        String consignee = address.getConsignee();
        String phone = address.getPhone();
        String address_detail = address.getAddress_detail();
        String addressStr = consignee + " " + phone + " " + address_detail;
        String name = jwtTokenUtil.getUsernameFromToken(tokenStorage);
        //一个个获取redis里的数据
        Set<Integer> range = redisTemplate.boundZSetOps(name).range(0, -1);
        for (Integer id : range) {
            System.out.println(id);
            Order order = orderDao.selectByPrimaryKey(id);
            order.setCooperationName(name);
            order.setOrderStatu(1);
            order.setAddress(addressStr);
            orderDao.updateByPrimaryKeySelective(order);
//                integers.add(id);
            redisTemplate.boundZSetOps(name).remove(id);
        }
        return new Result(true, StatusCode.OK, "支付成功");
    }

}
