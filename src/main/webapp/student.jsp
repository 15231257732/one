<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path=request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<style type="text/css">
#a{
	width: 400px;
	height: 300px;
	margin: 100px auto;
	border: gray solid 2px;
	border-radius: 8px 8px 8px 8px;
}
#b{
	width: 150px;
	height: 100px;
	margin-left: 140px;
	margin-top: 40px;
	
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>青少年杯世界知识竞赛</title>
</head>
<body>
	<br>
	<h2 align="center">欢迎考试</h2>
	<div id="a">
		
		<div id="b">
			<div>
				<label><font color="gray">账号：</font></label><font color="orange">${user.uid}</font>
			</div>
			<br>
			<div>
				<label><font color="gray">姓名：</font></label><font color="orange">${user.uname}</font>
			</div>
			<br>
			<div>
				<label><font color="gray">电话：</font></label><font color="orange">${user.telphone}</font>
			</div>
			<br>
			<div>
				<label><font color="gray">地址：</font></label><font color="orange">${user.address}</font>
			</div>
			<br>
			<div>
				<a href="<%=path%>/testQueryAction.do">祝你好运！</a>
			</div>
		</div>
	</div>
	<h2 align="right">众里寻他千百度，考试不能查百度。</h2>
</body>
</html>