<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.huaxin.ztree.bean.Question" %>
<%
  String path=request.getContextPath();
  Question question = (Question)session.getAttribute("question");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=path %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">

	.subject{
	
		margin-left: 50px;
	}
	.subject .line{	
		width: 1200px;
		height: 1px;
		background-color: #ccc;
	}
	
</style>
<script type="text/javascript">

$(document).ready(function() {
	 $("#tijiao").click(function(){
		// alert("2222");
		$.ajax({
			type:"post",
			url:"<%=path%>",
			data:$("[name='tform']").val(),
			dataType:"text",
			success:function(res){
				//alert("2222");
			}
		})	
		alert("修改成功");
		//respan();
	})
});
 	$(function(){
 		showType();
 		
		$("#wqtype").change(function() {
			showType();
		})
		
		function showType(){
			var obj=$("#wqtype").val();
			var typ=obj==1?"单选题":obj==2?"多选题":obj==3?"判断题":"未定义";
			if(obj==1){
				$(".answerlist")[0].style.cssText="display: inline;";
				$(".answerlist")[1].style.cssText="display: none;";
				$(".answerlist")[2].style.cssText="display: none;";			
			}else if(obj==2){
				$(".answerlist")[2].style.cssText="display: none;";
				$(".answerlist")[0].style.cssText="display: none;";
				$(".answerlist")[1].style.cssText="display: inline;";
			}else if(obj==3){			
				$(".answerlist")[0].style.cssText="display: none;";
				$(".answerlist")[1].style.cssText="display: none;";
				$(".answerlist")[2].style.cssText="display: inline;";
			}
		}
}); 
	
		
		 

	
</script>
</head>
<body>
	<form role="form" id="tform" action="<%=path%>/action.do" method="post">
	<div class="col-md-6">
	<div class="subject">
		<h3 style="margin-left: 40%">试题修改</h3>
		<div class="line"></div>
		<input type="hidden" name="qid" value="${question.qid}">
		<div style="margin-top: 30px; margin-left: 16px;">
			<label>学科专业:</label>
			<select name="qcourse" value="${question.qcourse}">
				<option value="0">-请选择-</option>
				<option value="1" <c:if test="${question.qcourse=='1'}">selected</c:if> >-java-</option>
				<option value="2" <c:if test="${question.qcourse=='2'}">selected</c:if> >-ui-</option>
				<option value="3" <c:if test="${question.qcourse=='3'}">selected</c:if>  >-大数据-</option>
				<option value="4" <c:if test="${question.qcourse=='4'}">selected</c:if>  >-云计算-</option>
			</select>
		</div>
		<div style="margin-top: 5px; margin-left: 16px;">
				<label>学业阶段:</label>
				<select name="qstage" value="${question.qstage}">
					<option value="0">-请选择-</option>
					<option value="1" <c:if test="${question.qstage=='1'}">selected</c:if> >-第一阶段-</option>
					<option value="2" <c:if test="${question.qstage=='2'}">selected</c:if>>-第二阶段-</option>
					<option value="3" <c:if test="${question.qstage=='3'}">selected</c:if>>-第三阶段-</option>
					<option value="4" <c:if test="${question.qstage=='4'}">selected</c:if>>-第四阶段-</option>
				</select>
			</div>
		<div style="margin-top: 5px; margin-left: 16px;">
			<label>题目类型:</label>
			<select name="qtype" id="wqtype" value="${question.qtype}">
				<option value="0">-请选择-</option>
				<option value="1" <c:if test="${question.qtype=='1'}">selected</c:if> >-单选题-</option>
				<option value="2" <c:if test="${question.qtype=='2'}">selected</c:if>>-多选题-</option>
				<option value="3" <c:if test="${question.qtype=='3'}">selected</c:if>>-判断题-</option>
			</select>
		</div>
		<div class="form-group" style="margin-top: 5px; margin-left: 16px; width: 500px;">
			<label for="tmmc">题目名称:</label> 
			<input type="input" class="form-control" id="tmmc" name="qname" value="${question.qname}">
		</div>
		<div class="form-group" style="margin-top: 10px;">
				<label for="name">题目分值:</label>  
				<div class="radio" name=qscore value="${question.qscore}">
						<label><input name="qscore" type="radio" value="1" <c:if test="${question.qscore=='1'}">checked</c:if>>一分</label> 
						<label><input name="qscore" type="radio" value="2" <c:if test="${question.qscore=='2'}">checked</c:if>>二分</label>
						<label><input name="qscore" type="radio" value="3" <c:if test="${question.qscore=='3'}">checked</c:if>>三分</label>
						<label><input name="qscore" type="radio" value="4" <c:if test="${question.qscore=='4'}">checked</c:if>>四分</label>
						<label><input name="qscore" type="radio" value="5" <c:if test="${question.qscore=='5'}">checked</c:if>>五分</label>
				</div>
			</div>
			<div style="margin-top: 10px; display: inherit;" name="qaname5" >
				<!--单选-->
				
				<div class="answerlist" value="1" style="display: none;" <%-- value="${question.qanswer}" --%>>
				 <%-- <c:if test="${question.qtype=='1'}"> --%>
					<label >单选答案选项:</label><br>
					<input type="radio" name="qanswer" value="A" <c:if test="${question.qanswer eq 'A'}">checked</c:if>  style="margin-top: 4px;" />
					<input type="hidden"name="qanswer2" value="A:" style="margin-top: 4px;" /> 
					<label style="margin-top: 3px;"id="singleindex1">A、</label> 
					<input type="text"  name="qaname1" id="singlename1"style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[0].qansername,':')}"/><br> 
				
					<input type="radio" name="qanswer"value="B" <c:if test="${question.qanswer eq 'B'}">checked</c:if> style="margin-top: 4px;" /> 
					<input type="hidden"name="qanswer2" value="B:" style="margin-top: 4px;" /> 
					<label style="margin-top: 3px;"id="singleindex2">B、</label> 
					<input type="text"  name="qaname1" id="singlename2"style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[1].qansername,':')}"/><br> 
					
					<input type="radio" name="qanswer" value="C" <c:if test="${question.qanswer eq 'C'}">checked</c:if> style="margin-top: 4px;" /> 
					<input type="hidden"name="qanswer2" value="C:" style="margin-top: 4px;" /> 
					<label style="margin-top: 3px;"id="singleindex3">C、</label> 
					<input type="text"  name="qaname1" id="singlename3"style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[2].qansername,':')}"/><br> 
					
					<input type="radio" name="qanswer"value="D" <c:if test="${question.qanswer eq 'D'}">checked</c:if> style="margin-top: 4px;" /> 
					<input type="hidden"name="qanswer2" value="D:" style="margin-top: 4px;" /> 
					<label style="margin-top: 3px;"id="singleindex4">D、</label> 
					<input type="text"  name="qaname1" id="singlename4"style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[3].qansername,':')}"/><br>
					<%--  </c:if>  --%>
				
				</div>
			
					<!--多选-->
					
					<div class="answerlist" style="display: none;"  <%-- value="${question.qanswer}" --%>>
					  <c:if test="${question.qtype=='2'}">  
						<label>多选答案选项:</label><br>
						
						<input type="checkbox" name="qanswer" value="A" <c:if test="${fn:contains(question.qanswer,'A')}">checked</c:if> style="margin-top: 4px;" /> 
						<input type="hidden"name="qanswer1" value="A:" style="margin-top: 4px;" /> 
						<label style="margin-top: 3px;" name="mulindex">A、</label> 
						<input type="text"id="mulname1" name="qaname"style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[0].qansername,':')}" /><br>
						
						<input type="checkbox" value="B" <c:if test="${fn:contains(question.qanswer,'B')}">checked</c:if> name="qanswer" style="margin-top: 4px;" />
						<input type="hidden"name="qanswer1" value="B:" style="margin-top: 4px;" /> 
						<label style="margin-top: 3px;" name="mulindex">B、</label> 
						<input type="text"id="mulname2" name="qaname" style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[1].qansername,':')}"/><br>
						
						<input type="checkbox" value="C" <c:if test="${fn:contains(question.qanswer,'C')}">checked</c:if> name="qanswer" style="margin-top: 4px;" />
						<input type="hidden"name="qanswer1" value="C:" style="margin-top: 4px;" /> 
						<label style="margin-top: 3px;" name="mulindex">C、</label>
					    <input type="text"id="mulname3" name="qaname" style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[2].qansername,':')}"/><br>
						
						<input type="checkbox" value="D" <c:if test="${fn:contains(question.qanswer,'D')}">checked</c:if> name="qanswer" style="margin-top: 4px;" />
						<input type="hidden"name="qanswer1" value="D:" style="margin-top: 4px;" /> 
						<label style="margin-top: 3px;" name="mulindex">D、</label> 
						<input type="text"id="mulname4"name="qaname"  style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[3].qansername,':')}"/><br>
					 </c:if>  
					</div> 
					
					<!--判断-->
				
					<div class="answerlist" style="display: none;" name="qanswer5" <%-- value="${question.qanswer}" --%>>
						<%-- 	 <c:if test="${question.qtype=='3'}">  --%>
						<label>判断答案选项:</label><br>
						<input type="radio" name="qanswer" value="A" <c:if test="${question.qanswer=='YES'}">checked</c:if>
							style="margin-top: 4px;" /> 
						<input type="hidden" name="qanswer3"Nvalue="A:" style="margin-top: 4px;" />
							<label style="margin-top: 3px;">对</label> 
						<input type="hidden" name="qaname2" value="对" style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[0].qansername,':')}"/><br>
						
						<input type="radio" name="qanswer" value="B" <c:if test="${question.qanswer=='NO'}">checked</c:if>
							style="margin-top: 4px;" /> 
						<input type="hidden" name="qanswer3" value="B:" style="margin-top: 4px;" /> 
							<label style="margin-top: 3px;"></label>错 
							<input type="hidden" name="qaname2" value="错" style="width: 80%; margin-top: 6px;" value="${fn:substringAfter(question.answer[1].qansername,':')}"/><br>
					<%-- </c:if>  --%>
					</div>
					
				</div>
			<div class="form-group" style="margin-top: 20px;">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" id="tijiao" class="btn btn-default">提交</button>
				<button type="submit" class="btn btn-default">重置</button>
			</div>
		</div>
	</div>
	</div>
	
					
</form>
</body>
</html>