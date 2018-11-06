package com.web.maven.dao;

import org.springframework.stereotype.Repository;

import com.web.maven.model.SysRolePower;

@Repository
public interface SysRolePowerMapper {
    int deleteByPrimaryKey(SysRolePower key);

    int insert(SysRolePower record);

    int insertSelective(SysRolePower record);
}