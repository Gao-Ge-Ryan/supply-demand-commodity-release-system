package com.gaoge.controller;

import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.entity.Address;
import com.gaoge.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高歌
 * @Description 地址类
 * @Date 2021-08-24
 */
@RestController
@RequestMapping("/address")
@Api(tags = "地址模块接口")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "添加地址 ")
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @ApiOperation(value = "根据登录用户名查询所有地址")
    @GetMapping("/selectByOwnName")
    public Result<List<Address>> selectByOwnName() {
        List<Address> addresses = addressService.selectByOwnName();
        return new Result<List<Address>>(true, StatusCode.OK, "查询成功", addresses);
    }

    @ApiOperation(value = "根据登录用户名查询默认地址")
    @GetMapping("/selectDefaultByOwnName")
    public Result<Address> selectDefaultByOwnName() {
        Address address = addressService.selectDefaultByOwnName();
        return new Result<Address>(true, StatusCode.OK, "查询成功", address);
    }

    @ApiOperation(value = "默认地址信息更新操作")
    @PostMapping("/defaultAddressInfoUpdate")
    public Result<String> defaultAddressInfoUpdate(@Validated @RequestBody Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "地址信息更新失败", s);
        }
        addressService.defaultAddressInfoUpdate(address);
        return new Result<String>(true, StatusCode.OK, "地址信息更新成功");
    }

    @ApiOperation(value = "根据id修改地址")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody Address address) {
        address.setId(id);
        addressService.update(address);
        return new Result(true, StatusCode.OK, "查询成功");
    }

    @ApiOperation(value = "根据id删除地址")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean delete = addressService.delete(id);
        if (delete == false) {
            return new Result(false, StatusCode.ERROR, "为默认地址不能删除");
        } else {
            return new Result(true, StatusCode.OK, "删除成功");
        }
    }


}
