<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="./WEB-INF/includes/taglibs.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black"> 
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link href="<%=uiPath%>ui/skins/default/css/commons.css" rel="stylesheet" type="text/css" />
<script src="<%=uiPath%>ui/jquery/jquery.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>ui/js/commons.js" type="text/javascript"></script>
<script type="text/javascript">
	 // 父函数执行
	 $(function(){
		 var arr=[1,2,3,4];
		 arr.forEach(function(val,index,arr){
		     arr[index]=2*val;
		 });
		 console.log(arr);//结果是修改了原数组，为每个数乘以2
		 
	     //回调函数作为父函数的参数
	   var result = a(b);
	   console.log("result = "+ result);
	 });
	 
	 function a(callback){
		   console.log("这是parent函数a");
		   var m =1;
		   var n=3;
		   //注意这里，为回调函数传参。
// 		  return callback(m,n);
		   console.log(callback.call(b,m,n));
		   console.log(this);
		   return callback.apply(this,[m,n]);
		 }

	 //b作为a的回调函数
	 function b(m,n){
	   console.log("这是回调函数B");
	   console.log(this);
	   return m+n;
	 }

</script>
</head>
<body>
<%
	String addr =  request.getRemoteAddr();
	out.print(addr);
	String conString =  request.getContentType();
	out.print(conString);
%>
<%-- 
<form action="saveUserImport.do" method="POST" enctype="multipart/form-data">
        File: <input type="file" name="file"/>
        Desc: <input type="text" name="desc"/>
        <input type="submit" value="Submit"/>
</form>
--%>
<form action="testName.do" method="POST" >
        input1: <input type="checkbox" name="userName" value="123"/>
        input2: <input type="checkbox" name="userName" value="456"/>
        <input type="submit" value="Submit"/>
</form>
<shiro:guest>
<a href="testDown.do">test down file</a>
</shiro:guest>
<shiro:principal/>
<c:if test="${message==null}">登录成功！</c:if>
<c:if test="${message!=null}">${message}</c:if>


</body>
</html>
