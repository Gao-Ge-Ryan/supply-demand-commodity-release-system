package com.gaoge.controller;

import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.dao.OrderDao;
import com.gaoge.dao.UserDao;
import com.gaoge.entity.Address;
import com.gaoge.entity.Order;
import com.gaoge.entity.PasswordParam;
import com.gaoge.entity.User;
import com.gaoge.security.service.JwtUserDetailsServiceImpl;
import com.gaoge.security.util.JwtTokenUtil;
import com.gaoge.service.OrderService;
import com.gaoge.service.UserService;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 高歌
 * @Description 用户模块
 * @Date 2021-08-24
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户模块接口")
public class UserController {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    @Autowired
    private UserDao userDao;

    //获取认证里面的用户信息,在每个方法里
//  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //用户登录之后，修改用户密码
    @ApiOperation(value = "用户登录之后，修改用户密码")
    @PostMapping("/loginUpdatePassword")
    public Result<String> loginUpdatePassword(@Validated @RequestBody PasswordParam passwordParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "密码修改失败", s);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userService.selectByUserName(username);
        //数据库密码
        String oldDataBasePassword = user.getPassword();
        //传过来的老密码
        String oldPassword = passwordParam.getOldPassword();
//        String encode = passwordEncoder.encode(oldPassword);

        if (!passwordEncoder.matches(oldPassword, oldDataBasePassword)) {
            return new Result<String>(false, StatusCode.ERROR, "原密码输入错误", "原密码输入错误");
        }
        user.setUserName(username);
        user.setPassword(passwordParam.getNewPassword());
        userService.update(user);
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    //用户登录之后，根据用户名展示个人信息
    @ApiOperation(value = "用户登录之后，根据用户名展示个人信息")
    @GetMapping("/loginSelectByUsername")
    public Result<User> loginSelectByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userService.selectByUserName(username);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    //用户登录之后，根据用户名修改个人基本信息
    @ApiOperation(value = "用户登录之后，根据用户名修改个人基本信息")
    @PostMapping("/loginUpdateByUsername")
    public Result<String> loginUpdateByUsername(@Validated @RequestBody User user, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (ObjectError error : allErrors) {
                System.out.println(error);
                stringBuffer.append(error.getDefaultMessage()).append("; ");
            }
            System.out.println(stringBuffer);
            String errorInfo = stringBuffer.toString();
            return new Result<String>(false, StatusCode.ERROR, "修改失败", errorInfo);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        user.setUserName(username);
        userService.loginUpdateByUsername(user);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new Result<String>(true, StatusCode.OK, "修改成功", token);
    }

    //查询所有用户
    @ApiOperation(value = "查询所有用户")
    @GetMapping
    public Result<List<User>> selectAll() {
        List<User> users = userService.selectAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", users);
    }

    //增加用户
    @ApiOperation(value = "增加用户")
    @PostMapping("/add")
    public Result<String> add(//@RequestParam(value = "file", required = false) MultipartFile file,
                              @Valid @RequestBody User user, BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (ObjectError error : allErrors) {
                System.out.println(error);
                stringBuffer.append(error.getDefaultMessage()).append("; ");
            }
            System.out.println(stringBuffer);
            String errorInfo = stringBuffer.toString();
            return new Result<String>(false, StatusCode.ERROR, "注册失败", errorInfo);
        }
        String userName = user.getUserName();
        User user1 = userDao.selectByPrimaryKey(userName);
        if (user1!=null){
            return new Result<String>(false, StatusCode.ERROR, "注册失败", "用户名已被注册，请重新输入");
        }
        userService.add(user);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    //修改用户
    @ApiOperation(value = "根据用户名修改用户信息")
    @PutMapping(value = "/{userName}")
    public Result<String> update(@Validated @RequestBody User user, BindingResult bindingResult, @PathVariable("userName") String userName) {
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "信息修改失败", s);
        }
        user.setUserName(userName);
        userService.update(user);
        return new Result<String>(true, StatusCode.OK, "信息修改成功", "修改成功");
    }

    //删除用户
    @ApiOperation(value = "根据用户名删除用户")
    @DeleteMapping("/{userName}")
    public Result<String> deletes(@PathVariable("userName") String userName) {
        //判断该用户是否有订单信息
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ownName", userName);
        List<Order> orders = orderDao.selectByExample(example);
        if (!orders.isEmpty()) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败", "该用户有发布的订单，暂不能删除");
        }
        userService.delete(userName);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //根据用户名查询用户
    @ApiOperation(value = "根据用户名查询用户")
    @GetMapping("/{userName}")
    public Result selectByUserName(@PathVariable("userName") String userName) {
        User user = userService.selectByUserName(userName);
        return new Result(true, StatusCode.OK, "查询成功", user);
    }

    //分页查询所有用户
    @ApiOperation("分页查询所有用户")
    @GetMapping("/search/{pageNum}")
    public Result<PageInfo> findPage(@PathVariable("pageNum") Integer pageNum) {
        Integer pageSize = 9;
        PageInfo<User> pageInfo = userService.findPage(pageNum, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    //分页条件查询
    @ApiOperation("分页条件查询用户")
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo> findPage(@RequestBody User user,
                                     @PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize) {
        PageInfo<User> pageInfo = userService.findPage(user, pageNum, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
