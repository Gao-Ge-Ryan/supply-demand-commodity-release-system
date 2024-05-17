package com.gaoge.service;

import com.gaoge.entity.Knowledge;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface KnowledgeService {
    PageInfo<Knowledge> findPage(Integer pageNum);

    void add(Knowledge knowledge);

    void update(Knowledge knowledge,Integer id);

    void delete(Integer id);

    List<Knowledge> selectByUsername(String name);

    Knowledge selectById(Integer id);
}
