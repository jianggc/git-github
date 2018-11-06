package com.web.maven.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.maven.model.SysPower;
import com.web.maven.model.SysRole;
import com.web.maven.model.SysUser;


@Repository
public interface ISysUserServiceDao {
	
	
	public SysUser  getUserByLoginCode(String loginCode);
	
	
	public List<SysRole>  getRoleListByUserId(Long userId);
	
	public List<SysPower>  getPowerListByUserId(Long userId);
	
}
