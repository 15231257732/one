<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	width: 70%;
	height: 70px;
	margin-left: 30%;
	margin-top: 1%;
}
#b{
	width: 68%;
	height: 100%px;
	margin-left: 32%;
	margin-top: 15px;
	margin-bottom: 5px;
}
</style>
<meta charset="UTF-8">
<title>考试了</title>
</head>
<body>
	<div id="a">
		<h2>考试就像一盒巧克力，考前你永远不知道你会得到什么。</h2>
		<div align="center"><font color="gray">账号：</font><font color="orange">${user.uid}</font>&nbsp;&nbsp;&nbsp;<font color="gray">姓名：</font><font color="orange">${user.uname}</font></div>
	</div>
	<hr>
	<div id="b">
			<form action="<%=path%>/totalAction.do" method="post">
				<c:forEach items="${list}" var="t" varStatus="Status">
						<div>
							<table>
								<tr>
									<td style="display: none;"><input type="text" value="${t.qid}"name="qid${Status.count}"></td>
								</tr>
								<tr>
									<td><font color="#36648B" size="4px">第${Status.count}题： ${t.qname}</font></td>
								</tr>
								<tr style="display: none;">
									<td>${t.qanswer}</td>
								</tr>
								<c:forEach items="${t.answer}" var="a" varStatus="Status">
									<tr>
									 <td><input type="checkbox" value="${a.qansername}" name="answer${Status.count}"><font color="gray" size="2px">${a.qansername}</font></td> 
									</tr>
								</c:forEach>
							</table>
							<br>
						</div>
					</c:forEach>
				<input type="submit" value="确认提交"/>
			</form>
		</div>
	</body>
</html>