<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@include file="../includes/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<title>登录</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<style type="text/css">
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,dl,dt,dd,ol,nav ul,nav li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline;}
article, aside, details, figcaption, figure,footer, header, hgroup, menu, nav, section {display: block;}
ol,ul{list-style:none;margin:0;padding:0;}
blockquote,q{quotes:none;}
blockquote:before,blockquote:after,q:before,q:after{content:'';content:none;}
table{border-collapse:collapse;border-spacing:0;}
/* start editing from here */
a{text-decoration:none;}
.txt-rt{text-align:right;}/* text align right */
.txt-lt{text-align:left;}/* text align left */
.txt-center{text-align:center;}/* text align center */
.float-rt{float:right;}/* float right */
.float-lt{float:left;}/* float left */
.clear{clear:both;}/* clear float */
.pos-relative{position:relative;}/* Position Relative */
.pos-absolute{position:absolute;}/* Position Absolute */
.vertical-base{	vertical-align:baseline;}/* vertical align baseline */
.vertical-top{	vertical-align:top;}/* vertical align top */
.underline{	padding-bottom:5px;	border-bottom: 1px solid #eee; margin:0 0 20px 0;}/* Add 5px bottom padding and a underline */
nav.vertical ul li{	display:block;}/* vertical menu */
nav.horizontal ul li{	display: inline-block;}/* horizontal menu */
img{max-width:100%;}
/*end reset*/
/*--login start here--*/
body{
 /*  background: url(../images/banner.jpg)repeat;*/
   padding:100px 0px 30px 0px;
   font-family: 'Roboto', sans-serif;
   font-size: 100%;
}
.login {
  width: 32%;
  margin: 0 auto;
}
.login h2 {
  font-size: 30px;
  font-weight: 700;
  color: #fff;
  text-align: center;
  margin: 0px 0px 50px 0px;
  font-family: 'Droid Serif', serif;
}
.login-top {
  background: #E1E1E1;
  border-radius: 25px 25px 0px 0px;
  -webkit-border-radius:  25px 25px 0px 0px;
  -moz-border-radius: 25px 25px 0px 0px;
  -o-border-radius: 25px 25px 0px 0px;
  padding: 40px 60px;
}
.login-top h1 {
  text-align: center;
  font-size: 27px;
  font-weight: 500;
  color: #F45B4B;
  margin: 0px 0px 20px 0px;
}
.login-top input[type="text"] {
	outline: none;
  font-size: 15px;
  font-weight: 500;
  color: #818181;
  padding: 15px 20px;
  background: #CACACA;
  border: 1px solid #ccc;
  border-radius:25px;
  -webkit-border-radius: 25px;
  -moz-border-radius: 25px;
  -o-border-radius: 25px;
  margin: 0px 0px 12px 0px;
  width: 88%;
  -webkit-appearance: none;
}
.login-top input[type="password"]{
	outline: none;
  font-size: 15px;
  font-weight: 500;
  color: #818181;
  padding: 15px 20px;
  background: #CACACA;
  border: 1px solid #ccc;
  border-radius:25px;
  -webkit-border-radius: 25px;
  -moz-border-radius: 25px;
  -o-border-radius: 25px;
  margin: 0px 0px 12px 0px;
  width: 88%;
  -webkit-appearance: none;
}
.forgot  a{
  font-size: 13px;
  font-weight: 500;
  color: #F45B4B;
  display: inline-block;
  border-right: 2px solid #F45B4B;
  padding: 0px 7px 0px 0px;
}
.forgot  a:hover{
  color: #818181;
}
.forgot input[type="submit"] {
  background: #F45B4B;
  color: #FFF;
  font-size: 17px;
  font-weight: 400;
  padding: 8px 7px;
  width: 20%;
  display: inline-block;
  cursor: pointer;
  border-radius: 6px;
  -webkit-border-radius: 19px;
  -moz-border-radius: 6px;
  -o-border-radius: 6px;
  margin: 0px 7px 0px 3px;
  outline: none;
  border: none;
}
.forgot input[type="submit"]:hover {
	background:#818181;
	transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
}
.forgot {
  text-align: right;
}
.login-bottom {
  background: #E15748;
  padding: 30px 65px;
  border-radius: 0px 0px 25px 25px;
  -webkit-border-radius:  0px 0px 25px 25px;
  -moz-border-radius: 0px 0px 25px 25px;
  -o-border-radius: 0px 0px 25px 25px;
  text-align: right;
  border-top: 2px solid #AD4337;
}
.login-bottom h3 {
  font-size: 14px;
  font-weight: 500;
  color: #fff;
}
.login-bottom h3 a {
  font-size: 25px;
  font-weight: 500;
  color: #fff;
}
.login-bottom h3 a:hover {
	color:#696969;
	transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
}
.copyright {
  padding: 150px 0px 0px 0px;
  text-align: center;
}
.copyright p {
  font-size: 15px;
  font-weight: 400;
  color: #fff;
}
.copyright p a{
  font-size: 15px;
  font-weight: 400;
  color: #E15748;
}
.copyright p a:hover{
	color: #fff;
	 transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
}
/*--login end here--*/
/*--meadia quiries start here--*/
@media(max-width:1440px){
.login {
  width: 35%;
}
}
@media(max-width:1366px){
.login {
  width: 37%;
}
}
@media(max-width:1280px){
.login {
  width: 40%;
}
}
@media(max-width:1024px){
.login {
  width: 48%;
}
.copyright {
  padding: 100px 0px 0px 0px;
}
}
@media(max-width:768px){
.login {
  width: 65%;
}
.login-top h1 {
  font-size: 25px;
}
.login-bottom h3 a {
  font-size: 22px;
}
.copyright {
  padding: 250px 0px 0px 0px;
}
body {
  padding: 100px 0px 0px 0px;
}
.login h2 {
  font-size: 28px;
}
}
@media(max-width:640px){
.login-top h1 {
  font-size: 23px;
}
.forgot input[type="submit"] {
  font-size: 15px;
  width: 22%;
}
.login-top input[type="text"] {
  padding: 12px 20px;
}
.login-top input[type="password"] {
  padding: 12px 20px;
}
.login-bottom h3 a {
  font-size: 19px;
}
.login-bottom h3 {
  font-size: 13px;
}
.copyright {
  padding: 110px 0px 0px 0px;
}
body {
  padding: 110px 0px 0px 0px;
}
}
@media(max-width:480px){
.login {
  width: 80%;
}
.login-top h1 {
  font-size: 21px;
}
.login-top input[type="text"] {
  width: 85%;
}
.login-top {
  padding: 30px 40px;
}
.login-top input[type="password"] {
  width: 85%;
}
.login h2 {
  font-size: 25px;
}
}
@media(max-width:320px){
.login {
  width: 90%;
}
.login-top {
  padding: 20px 25px;
}
.login-top input[type="text"] {
  width: 81%;
  padding: 10px 20px;
  font-size: 13px;
  margin: 0px 0px 7px 0px;
}
.login-top input[type="password"] {
  width: 81%;
  padding: 10px 20px;
  font-size: 13px;
  margin: 0px 0px 7px 0px;
}
.forgot input[type="submit"] {
  font-size: 11px;
  width: 25%;
  padding: 6px 7px;
}
.forgot  a {
  font-size: 11px;
}
.login-bottom {
  padding: 20px 25px;
}
.login-bottom h3 {
  font-size: 11px;
}
.login-bottom h3 a {
  font-size: 17px;
}
body {
  padding: 50px 0px 0px 0px;
}
.copyright p {
  font-size: 13px;
}
.copyright p a{
  font-size: 13px;
}
.login h2 {
  font-size: 23px;
  margin:0px 0px 35px 0px;
}
.copyright {
  padding: 75px 0px 0px 0px;
}

</style>
<script type="text/javascript">
    if(!(top==self))
    {
    	top.location.href ="<%=basePath%>login.do"; 	
   	}
    	
</script>
</head>
<body>
<div class="login">
            
	<div class="login-top">
	<h1 >Shiro</h1>
		<form  id ="form1" action="/doLogin" method="post">
			<input type="text" name="userName"  maxlength="25"  value="${userName}"  placeholder="请输入用户名" >
			<input type="password" name="password" maxlength="16"  placeholder="请输入密码" >
	   
	    <div class="forgot">
	    <center>
	    	<input type="submit" value="登 录 ">
	    </center>
	    </div>
	    </form>
	</div>
	<div class="login-bottom" >
	<center>
		<h3 style="color:#fff"><c:out value="${message }"/></h3>
		</center>
	</div>
</div>	



</body>
</html>