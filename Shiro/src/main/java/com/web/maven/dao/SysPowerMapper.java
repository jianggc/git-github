package com.web.maven.dao;

import org.springframework.stereotype.Repository;

import com.web.maven.model.SysPower;

@Repository
public interface SysPowerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPower record);

    int insertSelective(SysPower record);

    SysPower selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPower record);

    int updateByPrimaryKey(SysPower record);
}