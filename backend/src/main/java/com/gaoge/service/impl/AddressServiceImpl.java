package com.gaoge.service.impl;

import com.gaoge.dao.AddressDao;
import com.gaoge.entity.Address;
import com.gaoge.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public void add(Address address) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        address.setOwn_name(name);
        addressDao.insertSelective(address);
    }

    @Override
    public List<Address> selectByOwnName() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("own_name", name);
        List<Address> addresses = addressDao.selectByExample(example);
        return addresses;
    }

    @Override
    public Address selectDefaultByOwnName() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("own_name", name);
        criteria.andEqualTo("is_default", 1);
        Address address = addressDao.selectOneByExample(example);
        Boolean is_default = address.getIs_default();
//        System.out.println(is_default);
        return address;
    }

    @Override
    public void defaultAddressInfoUpdate(Address address) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        Integer id = address.getId();
        address.setOwn_name(name);
        if (address.getIs_default()) {
            Boolean is_default = address.getIs_default();
            if (is_default) {
//          将别的默认路径清除
                setZero();
            }
            update(address);
        }else {
            Boolean is_default = address.getIs_default();
            if (is_default) {
//          将别的默认路径清除
                setZero();
            }
            address.setId(null);
            add(address);
        }
    }

    @Override
    public void update(Address address) {
//        Integer is_default = address.getIs_default();
//        if (is_default.equals(1)){
//            setZero();
//        }
        addressDao.updateByPrimaryKey(address);
    }

    @Override
    public boolean delete(Integer id) {
        Address address = addressDao.selectByPrimaryKey(id);
        if (address.getIs_default()){
            return false;
        }else {
            addressDao.deleteByPrimaryKey(id);
            return true;
        }
    }

    /**
     * 让所有的地址默认值设为0
     */
    public void setZero() {
        List<Address> addresses = selectByOwnName();
        for (Address address : addresses) {
            address.setIs_default(false);
            update(address);
        }
    }

}
