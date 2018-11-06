package com.web.maven.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.maven.model.SysUser;
import com.web.maven.utils.SiteTemplateEnum;

@Controller
public class MainController {
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		SysUser user_=(SysUser)SecurityUtils.getSubject().getPrincipal();
		mav.addObject("loginUser",user_);
		System.out.println("进入index方法");
		mav.setViewName(SiteTemplateEnum.sys_index.getUrlPath());
		return mav;
	}
	
	@RequestMapping(value = "/admin")
	public ModelAndView admin(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		SysUser user_=(SysUser)SecurityUtils.getSubject().getPrincipal();
		mav.addObject("loginUser",user_);
		System.out.println("进入admin方法");
		mav.setViewName(SiteTemplateEnum.sys_admin.getUrlPath());
		return mav;
	}
	
	
	@RequestMapping(value = "refuse")
	public ModelAndView refuse(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(SiteTemplateEnum.sys_refuse.getUrlPath());
		return mav;
	}
}
