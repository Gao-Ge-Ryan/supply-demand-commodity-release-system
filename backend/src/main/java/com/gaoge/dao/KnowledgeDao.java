package com.gaoge.dao;

import com.gaoge.entity.Knowledge;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface KnowledgeDao extends Mapper<Knowledge> {
}
