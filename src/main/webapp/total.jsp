<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path=request.getContextPath();
%>
<!DOCTYPE html>
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
	margin-left: 130px;
	margin-top: 50px;
}
</style>
<meta charset="UTF-8">
<title>总计</title>
</head>
<body>
<div id="a">
	<div id="b">
	<h2>恭喜，<font color="orange">${user.uname}</font></h2>
	<h3>成绩：<font color="red" size="6px">${total}</font>分</h3>
	<br>
	<div><a href="<%=path%>/testQueryAction.do">重新答题</a></div>
	<br>
	<div><a href="<%=path%>/index.jsp">退出登录</a></div>
	</div>
</div>

</body>
</html>