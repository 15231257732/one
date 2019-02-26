<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考试系统用户管理</title>
<link href="/telexam/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/telexam/js/jquery-2.2.4.js"></script>
<script type="text/javascript"
	src="/telexam/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
	<div style="margin-top: 16px;">
		<fieldset style="width: 80%; margin: 0px auto;">
			<legend>用户信息</legend>
			<div class="row">
				<div style="width: 90%; margin-left: 2%;">
					<form action="<%=path %>/userListAction.do" method="post" >
						<label>用户名</label> <input type="text" id="txtName" placeholder="请输入名称(模糊搜索)" style="width: 20%;" name="txtName" />

						<input type="submit" value="查询"> <input type="button" value="添加"
							onclick="window.location.href='<%=path%>/jsp/userlyx/saveOrUpdate.jsp'" />
					</form>
				</div>
				<div style="width: 98%; margin: 0 auto;">
					<table align="center"
						class="table table-bordered table-hover table-condensed">
						<thead>
							<tr class="success" align="center">
								<td width="70"><b>用户名</b></td>
								<td width="60" align="center"><b>性别</b></td>
								<td width="150"><b>电话</b></td>
								<td width="300"><b>地址</b></td>
								<td width="120"><b>编辑/删除</b></td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="stu">

							<tr>
								<td width="160">${stu.uname}</td>
								<td width="70" align="center">${stu.sex }</td>
								<td width="150" align="center">${stu.telphone }</td>
								<td width="300">${stu.address}</td>
								<td width="120" align="center"><a
									href="<%=path %>/toUpdateAction.do?uid=${stu.uid}">编辑</a>&nbsp;&nbsp;
									<a href="<%=path %>/userdeleteAction.do?uid=${stu.uid}">删除</a></td>
							</tr>

						</c:forEach>
					</table>
				</div>
				<div>
					<div id="pege" style="display: inline-block; float: left;"></div>
					<div id="sepege" style="display: inline-block; float: right;"></div>
				</div>
			</div>

		</fieldset>
	</div>
</body>
</html> --%>