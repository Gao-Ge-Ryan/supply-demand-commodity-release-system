package com.gaoge.service;

import com.gaoge.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    void add(User user);

    void update(User user);

    void delete(String userName);

    User selectByUserName(String userName);

    PageInfo<User> findPage(Integer pageNum, Integer pageSize);

    PageInfo<User> findPage(User user, Integer pageNum, Integer pageSize);

    void loginUpdateByUsername(User user);
}
