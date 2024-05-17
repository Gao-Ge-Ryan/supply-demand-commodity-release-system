package com.gaoge.dao;

import com.gaoge.entity.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AddressDao extends Mapper<Address> {
}
