package com.web.maven.dao;

import org.springframework.stereotype.Repository;

import com.web.maven.model.SysUserRole;

@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRole key);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}