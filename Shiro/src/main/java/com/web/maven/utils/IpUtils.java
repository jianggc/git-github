package com.web.maven.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtils {
	
	private final static  Logger log = LoggerFactory.getLogger(IpUtils.class);
	/**
	 * X-Forwarded-For  58.63.227.162, 192.168.237.178, 192.168.238.218
	 * X-Real-IP
	 * Proxy-Client-IP
	 * WL-Proxy-Client-IP
	 * HTTP_CLIENT_IP
	 * HTTP_X_FORWARDED_FOR
	 * getRemoteAddr() 192.168.239.196
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午1:15:18
	 * @param request
	 * @return String
	 */
	public static String getRealRemoteIP(HttpServletRequest request)
	  {
	    String ip = request.getHeader("X-Forwarded-For");
	        log.debug("X-Forwarded-For ==>"+ip);
	    if (realRemoteIPUtil(ip)) {
		      ip = request.getHeader("X-Real-IP");
		      log.debug("X-Real-IP ==>"+ip);
		 }
	    if (realRemoteIPUtil(ip)) {
	      ip = request.getHeader("Proxy-Client-IP");
	      log.debug("Proxy-Client-IP ==>"+ip);
	    }
	    if (realRemoteIPUtil(ip)) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	      log.debug("WL-Proxy-Client-IP ==>"+ip);
	    }
	    if (realRemoteIPUtil(ip)) {
	      ip = request.getHeader("HTTP_CLIENT_IP");
	      log.debug("HTTP_CLIENT_IP ==>"+ip);
	    }
	    if (realRemoteIPUtil(ip)) {
	    	ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    	log.debug("HTTP_X_FORWARDED_FOR ==>"+ip);
	    }
	    if (realRemoteIPUtil(ip)) {
	      ip = request.getRemoteAddr();
	      log.debug("request.getRemoteAddr() ==>"+ip);
	    }
	    log.debug("RealRemoteIP ==>"+ip);
	    return ip;
	  }
	
	/**
	 * 
	 * 校验是否为有效IP
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午1:23:15
	 * @param ip
	 * @return boolean
	 */
	private static boolean realRemoteIPUtil(String ip){
		if(ToolUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * 校验是否为有效IP
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午1:31:03
	 * @param ip
	 * @return boolean
	 */
	public boolean isInvalid(String ip)
	{
		if(ToolUtils.isEmpty(ip)|| "unknown".equalsIgnoreCase(ip)||ip.length() < 7 || ip.length() > 15)
		{
			return false;
		}
		//判断IP格式和范围
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		
		Pattern pat = Pattern.compile(rexp);  
		
		Matcher mat = pat.matcher(ip);  
		
		boolean ipAddress = mat.find();

		return ipAddress;
	}
	
	
}
