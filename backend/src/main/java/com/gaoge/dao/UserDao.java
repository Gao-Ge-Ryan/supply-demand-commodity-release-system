package com.gaoge.dao;

import com.gaoge.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserDao extends Mapper<User> {
}
