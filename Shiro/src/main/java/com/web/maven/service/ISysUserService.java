package com.web.maven.service;

import java.util.List;

import com.web.maven.model.SysPower;
import com.web.maven.model.SysRole;
import com.web.maven.model.SysUser;

public interface ISysUserService {
	
	List<SysRole> getRoleListByUserId(Long id);
	
	 List<SysPower> getPowerListByUserId(Long id);
	 
	 SysUser getUserByLoginCode(String userName);

}
