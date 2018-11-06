package com.web.maven.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.maven.model.SysPower;
import com.web.maven.model.SysRole;
import com.web.maven.model.SysUser;
import com.web.maven.service.ISysUserService;

public class CustomRealm2 extends AuthorizingRealm {

	@Autowired
	ISysUserService sysUserService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		//处理角色
		Set<String> roles = new HashSet<String>();
		List<SysRole> sysRoles= sysUserService.getRoleListByUserId(user.getId());
		for(SysRole r:sysRoles){
			roles.add(r.getName());
		}
		//处理权限
		 List<String> powers = new ArrayList<String>();
		 List<SysPower> sysPowers= sysUserService.getPowerListByUserId(user.getId());
		 for(SysPower p:sysPowers){
			 powers.add(p.getName());
		 }
		 
		 SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
	        simpleAuthorizationInfo.addStringPermissions(powers);
	        simpleAuthorizationInfo.addRoles(roles);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		 	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	        //获取页面传来的用户账号
		 	String loginName = token.getUsername();
	        //根据登录账号从数据库查询用户信息
	        SysUser user = sysUserService.getUserByLoginCode(loginName);
	        System.out.println("从数据库查询到的用户信息 ： "+user);
	        //一些异常新娘西
	        if (null == user) {
	        	throw new UnknownAccountException();//没找到帐号
	        }
	        if (user.getStatus()==null||user.getStatus()==0) {
	        	throw new LockedAccountException();//帐号被锁定
	        }
	        //其他异常...
	        
	        
	        //返回AuthenticationInfo的实现类SimpleAuthenticationInfo
	        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
	        //盐值加密
//	        ByteSource credentialsSalt = ByteSource.Util.bytes(loginName);
//	        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, this.getName());
	}
	
}
