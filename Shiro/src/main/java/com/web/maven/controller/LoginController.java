package com.web.maven.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.maven.model.SysUser;
import com.web.maven.utils.PasswordException;
import com.web.maven.utils.SiteTemplateEnum;
import com.web.maven.utils.UserNameException;


@Controller
public class LoginController {
    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public ModelAndView  login() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName(SiteTemplateEnum.sys_login.getUrlPath());
    	return mav;
    }
    
    @ResponseBody
    @RequestMapping(value="/test",produces="application/json;charset=utf-8")
    public String  test(HttpSession session) {
    	System.out.println("调用方法test");
    	session.setAttribute("key", "123456");
    	return "success";
    }

    @RequestMapping(value="/doLogin" )
    public String doLogin(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, String  userName, String password, String randomCode) throws Exception{
        String msg;
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        log.debug("UserNamePasswordToken----:"+JSON.toJSONString(token));
        token.setRememberMe(true);
        request.setAttribute("userName", userName);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("HttpSession : "+session);
        Session shiroSession = subject.getSession();
        System.out.println("shiro session : "+shiroSession);
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
            	System.out.println("认证成功！");
                return "redirect:/index";
            }
        } catch (UserNameException e) {
        	msg = e.getMessage();//Password for account " + token.getPrincipal() + " was incorrect.
        	model.addAttribute("message", msg);
        } catch (PasswordException e) {
        	msg = e.getMessage();//Password for account " + token.getPrincipal() + " was incorrect.
        	model.addAttribute("message", msg);
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. ";//Password for account " + token.getPrincipal() + " was incorrect.
            model.addAttribute("message", msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多.";
            model.addAttribute("message", msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定,如有疑问请联系管理员. ";//The account for username " + token.getPrincipal() + " was locked.
            model.addAttribute("message", msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. ";//The account for username " + token.getPrincipal() + " was disabled.
            model.addAttribute("message", msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. ";//the account for username " + token.getPrincipal() + "  was expired.
            model.addAttribute("message", msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. ";//There is no user with username of " + token.getPrincipal()
            model.addAttribute("message", msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
        }
        return "forward:/login";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
    	SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
    	log.debug("loginOut loginUser:"+JSON.toJSONString(user));
        return "redirect:/login";//重定向
    }


}
