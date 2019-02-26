<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/bootstrap/js/bootstrap.min.js"></script>
<title>试题管理界面</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<form  method="post" action="<%=path%>/questionListAction.do" class="form-inline" role="form" style="margin-top: 10px;">
			<div class="form-group" >
			     <label  for="qtype">题目类型：</label>
			     <select class="form-control" name=qtype id="qtype">
				        <option value="0">---请选择---</option>
						<option value="1">单选题</option>
						<option value="2">多选题</option>
						<option value="3">判断题</option>
				  </select>
			</div>
			<div class="form-group" >
			     <label  for="qcourse">学科专业：</label>
			     <select class="form-control" name=qcourse id="qcourse"">
					<option value="0">---请选择---</option>
					<option value="1">java</option>
					<option value="2">大数据</option>
					<option value="3">UI设计</option>
					<option value="4">网络营销</option>
				</select>
			</div>
			<div class="form-group" >
			     <label  for="qstage">学科阶段：</label>
			     <select class="form-control" name=qstage id="qstage">
					<option value="0">---请选择---</option>
					<option value="1">第一阶段</option>
					<option value="2">第二阶段</option>
					<option value="3">第三阶段</option>
					<option value="4">第四阶段</option>
				</select>
			</div>
			<input type="submit"  class="btn btn-primary" id="querytype"value="查&nbsp;询 "/>
			<input onclick="javascript:window.location.href='<%=path%>/jsp/questionanswer/addOrupdate.jsp'"
				type="button" class="btn btn-danger" value="增&nbsp;加" /> 
			<!-- <button type="button" class="btn btn-success">批&nbsp;量&nbsp;删&nbsp;除 </button> -->
		</form>
	</nav>
	<div style="margin-top: -15px;" >
		<table class="table table-bordered table-hover">
		    <thead>
				<tr >
					<!-- <th > <input type="checkbox">全选</th> -->
					<th >序号</th>
					<th >题目描述</th>
					<th >题目类型</th>
					<!-- <th>问题内容</th> -->
					<th >问题答案</th>
					<th >问题分值</th>
					<th >学科阶段</th>
					<th >学科专业</th>
					<th >操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${map.list}" var="question" varStatus="i">
				<tr >
					<!-- <td><input type='checkbox' name='id'></td> -->
					<td>${i.index+1}</td>
					<td>${question.qname}</td>
					<td><c:choose>
							<c:when test="${question.qtype=='1'}">   
    					单选题 
  					</c:when>
							<c:when test="${question.qtype=='2'}">
   						多选题   
  					</c:when>
							<c:otherwise>   
  						 判断题   
  					</c:otherwise>
						</c:choose></td>
					<td>${question.qanswer}</td>
					<td>
						<!-- 分值 qscore--> <c:choose>
							<c:when test="${question.qscore=='1'}">   
    				一分
  				</c:when>
							<c:when test="${question.qscore=='2'}">
   					二分 
  				</c:when>
							<c:when test="${question.qscore=='3'}">
   					三分   
  				</c:when>
							<c:when test="${question.qscore=='4'}">
   					四分
  				</c:when>
							<c:otherwise>   
  					五分   
  				</c:otherwise>
						</c:choose>
					</td>
					<td>
						<!-- 阶段  qstage--> <c:choose>
							<c:when test="${question.qstage=='1'}">   
    				第一阶段
  				</c:when>
							<c:when test="${question.qstage=='2'}">
   					第二阶段   
  				</c:when>
							<c:when test="${question.qstage=='3'}">
   					第三阶段   
  				</c:when>
							<c:otherwise>   
  					第四阶段   
  				</c:otherwise>
						</c:choose>
					</td>
					<td>
						<!-- 专业  qcourse--> <c:choose>
							<c:when test="${question.qcourse=='1'}">   
    				java
  				</c:when>
							<c:when test="${question.qcourse=='2'}">
   					大数据 
  				</c:when>
							<c:when test="${question.qcourse=='3'}">
   					UI设计   
  				</c:when>
							<c:otherwise>   
  					网络营销   
  				</c:otherwise>
						</c:choose>
					</td>
					<td>
						 <a href="<%=path%>/UpdateAction.do?qid=${question.qid}">修改</a> 
						<a href="<%=path%>/deleteAction.do?qid=${question.qid}">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div id="page" style="margin-left: ">
	                总记录数:<strong>&nbsp;${map.pageBean.count}&nbsp;</strong>,每页记录数:<strong>&nbsp;${map.pageBean.pagesize}&nbsp;</strong>,总页数:&nbsp;<strong>${map.pageBean.pageCount}</strong>&nbsp;,当前<strong>&nbsp;${map.pageBean.pageNumber}&nbsp;</strong>页    
	       <a href="<%=path%>/questionListAction.do?pageNumber=1&qname=${question.qname}" style="margin-left: 30%;">首页</a>
	       <a href="<%=path%>/questionListAction.do?pageNumber=${map.pageBean.pageNumber-1}&qname=${question.qname}">上一页</a>
	       <a href="<%=path%>/questionListAction.do?pageNumber=${map.pageBean.pageNumber+1}&qname=${question.qname}">下一页</a>
	       <a href="<%=path%>/questionListAction.do?pageNumber=${map.pageBean.pageCount}&qname=${question.qname}">末页</a>
	   </div>

	</div>
</body>
</html>