package com.web.maven.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.maven.dao.ISysUserServiceDao;
import com.web.maven.model.SysPower;
import com.web.maven.model.SysRole;
import com.web.maven.model.SysUser;
import com.web.maven.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService {
	
	@Autowired
	ISysUserServiceDao userServiceDao;

	@Override
	public List<SysRole> getRoleListByUserId(Long id) {
		// TODO Auto-generated method stub
		Session session = SecurityUtils.getSubject().getSession();
		Object attribute = session.getAttribute("key");
		List<SysRole> roleListByUserId = userServiceDao.getRoleListByUserId(id);
		return roleListByUserId;
	}

	@Override
	public List<SysPower> getPowerListByUserId(Long id) {
		// TODO Auto-generated method stub
		List<SysPower> powerListByUserId = userServiceDao.getPowerListByUserId(id);
		return powerListByUserId;
	}

	@Override
	public SysUser getUserByLoginCode(String userName) {
		// TODO Auto-generated method stub
		SysUser userByLoginCode = userServiceDao.getUserByLoginCode(userName);
		return userByLoginCode;
	}

}
