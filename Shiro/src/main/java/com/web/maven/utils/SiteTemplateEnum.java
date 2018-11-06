package com.web.maven.utils;

public enum SiteTemplateEnum {
	sys_index("sys_index","index"),
	sys_admin("sys_admin","admin"),
	sys_refuse("sys_refuse","refuse"),
	sys_login("sys_login","login");
	
	
	private String code;
	private String urlPath;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
	private SiteTemplateEnum(String code,String urlPath){
		this.code = code;
		this.urlPath = urlPath;
	}

}
