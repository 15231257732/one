<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>考试系统用户管理</title>

<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="../../js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>

<style type="text/css">

.foot{
	text-align: center;
	margin-top:18px;
	margin-bottom: 18px;
}

.defaultPage{
	text-align:center;
	margin-top: 120px;
	font-size: 50px;
	color: red;
	height: 300px;
}

.examMenu{
	margin-top: 15px;
}

.data_list{
	margin-top: 10px;
}

.data_list .data_content{
	margin-top: 15px;
}

.data_list .data_info .examTitle{
	font-size: 30px;
	font-weight: bold;
	text-align: center;
	padding-top: 30px;
}

.data_list .data_info .examScoreInfo{
	padding:10px;
	font-weight: bold;
	text-align: right;
}

.examResult{
	font-size: 20px;
	padding: 120px;
}

.data_list .data_info p{
	text-align: center;
	font-size: 20px;
	font-weight: bold;
	padding-bottom: 10px;
}

.data_list .search_content form{
	text-align:center;
	margin: 0px;
}
</style>
</head>
<body>
	<div class="data_list">
		<div class="data_info">
			<p>考生信息管理</p>
		</div>
		<div class="search_context">
			<form action="<%=path%>/userListAction.do" method="post">
				<table align="center">
					<tr>
						<td><label>用户名：</label></td>
						<td><input type="text" id="txtName" name="txtName"
							placeholder="输入名称(此处可模糊搜索)" /></td>
						<td>&nbsp;</td>
						<td><button class="btn btn-primary"
								style="margin-bottom: 8px;" type="submit" onclick="toUpdateAction">查询</button>
								</td>
					</tr>
				</table>
			</form>
			<button class="btn-mini btn-primary" style="float: right;margin-bottom: 5px;" type="button" onclick="window.location.href='<%=path%>/jsp/userlyx/saveOrUpdate.jsp'">添加考生信息</button>
		</div>
			<div class = "data_content">
				<table class = "table table-bordered table-hover">
					<tr>
						<th>用户名</th>
						<th>性别</th>
						<th>电话</th>
						<th>地址</th>
						<th>编辑与删除</th>
					</tr>
					<c:forEach items="${list}" var="stu">
						<tr>
							<td>${stu.uname}</td>
							<td>${stu.sex}</td>
							<td>${stu.telphone}</td>
							<td>${stu.address}</td>
							
							<td>
							<button class="btn-mini btn-info" type="button"> <a href="<%=path %>/toUpdateAction.do?uid=${stu.uid}">修改</a></button>
							&nbsp;&nbsp;
							<button class="btn-mini btn-danger" type="button"><a href="<%=path %>/userdeleteAction.do?uid=${stu.uid}">删除</button></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div>
					<div id="pege" style="display: inline-block; float: left;"></div>
					<div id="sepege" style="display: inline-block; float: right;"></div>
			</div>
	</div>
</body>
</html>