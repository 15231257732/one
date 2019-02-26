<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%String path = request.getContextPath();%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/bootstrap/js/bootstrap.min.js"></script>
<title>试题管理界面</title>
<script type="text/javascript">
	/* 试题类型 */
	$(function() {
		$("#qtype").change(function() {
			//判断下拉菜单
			var obj = $("#qtype").val();
			if (obj == "1") {
				$(".answerlist")[0].style.cssText = "display: inline;";
				$(".answerlist")[1].style.cssText = "display: none;";
				$(".answerlist")[2].style.cssText = "display: none;";
			} else if (obj == "2") {
				$(".answerlist")[0].style.cssText = "display: none;";
				$(".answerlist")[1].style.cssText = "display: inline;";
				$(".answerlist")[2].style.cssText = "display: none;";
			} else if (obj == "3") {
				$(".answerlist")[0].style.cssText = "display: none;";
				$(".answerlist")[1].style.cssText = "display: none;";
				$(".answerlist")[2].style.cssText = "display: inline;";
			} else if (obj == 0) {
				$(".answerlist")[2].style.cssText = "display: none;";
				$(".answerlist")[0].style.cssText = "display: none;";
				$(".answerlist")[1].style.cssText = "display:  none;";
			}
		})

	});
	/*
	 * 提交成功提示 
	 */
	$(document).ready(function() {
		$("form").submit(function(e) {
			alert("提交成功！！！");
		});
	});
</script>
<style type="text/css">
#xuanxiang{
	position: absolute;
	top:20%;
	left:45%;
	/* border: 1px solid red; */
	height: 400px;
	width: 300px;
}


</style>
</head>
<body>
	<div style="margin-top: 16px;">
		<div class="page-header" style="width: 85%; margin: 0px auto;">
			<h3>试题增加</h3>
		</div>
	</div>
	<form action="<%=path%>/action.do" method="post">    <!-- form表单大框架 -->
				<input type="hidden" value="${question.qid}" name="qid"> <!-- 隐藏qid表单 -->
		<div style="margin-top: 3%; margin-left: 10%;" >
				<label>学科专业：</label>  <!-- 学科专业div qcourse -->
					<select name=qcourse id="qcourse">
						<option value="0" >---请选择---</option>
						<option value="1" <c:if test="${question.qcourse=='1'}"> selected="selected" </c:if>>java</option>
						<option value="2" <c:if test="${question.qcourse=='2'}"> selected="selected" </c:if>>大数据</option>
						<option value="3" <c:if test="${question.qcourse=='3'}"> selected="selected" </c:if>>UI设计</option>
						<option value="4" <c:if test="${question.qcourse=='4'}"> selected="selected" </c:if>>网络营销</option>
					</select>
				<div style="margin-top: 10px;" id="wqstage">	<!--阶段 div qstage  -->
					<label>学科阶段：</label> 
					<select name=qstage id="qstage" value="${question.qstage}">
						<option value="0">---请选择---</option>
						<option value="1" <c:if test="${question.qstage=='1'}"> selected="selected" </c:if>>第一阶段</option>
						<option value="2" <c:if test="${question.qstage=='2'}"> selected="selected" </c:if>>第二阶段</option>
						<option value="3" <c:if test="${question.qstage=='3'}"> selected="selected" </c:if>>第三阶段</option>
						<option value="4" <c:if test="${question.qstage=='4'}"> selected="selected" </c:if>>第四阶段</option>
					</select>
				</div>
				<div style="margin-top: 10px;">
					<label>题目类型：</label>        <!-- 题目类型  div qtype -->
					<select name=qtype id="qtype" value="${question.qtype}">
						<option value="0">---请选择---</option>
						<option value="1" <c:if test="${question.qtype=='1'}"> selected="selected" </c:if>>单选题</option>
						<option value="2" <c:if test="${question.qtype=='2'}"> selected="selected" </c:if>>多选题</option>
						<option value="3" <c:if test="${question.qtype=='3'}"> selected="selected" </c:if>>判断题</option>
					</select>
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<label for="name">题目</label>  <!-- 题目描述 qname -->
					<input type="text" name=qname value="${question.qname}"
						class="form-control" id="name" placeholder="请输入题目"
						style="width: 30%">
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<label for="name">分值</label>  <!-- 分值类型 qscore-->
					<div class="radio" value="${question.qscore}">
						<label><input name="qscore" type="radio" value="1" <c:if test="${question.qscore=='1'}"> checked="checked" </c:if>>一分</label>
						<label><input name="qscore" type="radio" value="2" <c:if test="${question.qscore=='2'}"> checked="checked" </c:if>>二分</label>
						<label><input name="qscore" type="radio" value="3" <c:if test="${question.qscore=='3'}"> checked="checked" </c:if>>三分</label>
						<label><input name="qscore" type="radio" value="4" <c:if test="${question.qscore=='4'}"> checked="checked" </c:if>>四分</label>
						<label><input name="qscore" type="radio" value="5" <c:if test="${question.qscore=='5'}"> checked="checked" </c:if>>五分</label>
					</div>
				</div>
				<div id="xuanxiang" display: inherit;">
					<!--单选-->
					<div class="answerlist" style="display: none;">
						<label>单选答案选项:</label><br> 
						<input type="radio" name="qanswer"value="A" style="margin-top: 6px;" /> 
						<input type="hidden"name="qanswer2" value="A:" style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;" id="singleindex1">A、</label> 
						<input type="text" name="qaname1" id="singlename1"style="width: 80%; margin-top: 6px;" /><br> 
						
						<input type="radio" name="qanswer" value="B" style="margin-top: 6px;" />
						<input type="hidden" name="qanswer2" value="B:"style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;"id="singleindex2">B、</label> 
						<input type="text" name="qaname1"id="singlename2" style="width: 80%; margin-top: 6px;" /><br>
	
						<input type="radio" name="qanswer" value="C"style="margin-top: 6px;" /> 
						<input type="hidden" name="qanswer2"value="C:" style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;" id="singleindex3">C、</label> 
						<input type="text" name="qaname1" id="singlename3"style="width: 80%; margin-top: 6px;" /><br> 
						<input type="radio" name="qanswer" value="D" style="margin-top: 6px;" />
						<input type="hidden" name="qanswer2" value="D:"style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;"id="singleindex4">D、</label> 
						<input type="text" name="qaname1"id="singlename4" style="width: 80%; margin-top: 6px;" /><br>
					</div>
					<!--多选-->
					<div class="answerlist" style="display: none;" >
						<label>多选答案选项:</label><br> <input type="checkbox"name="qanswer" value="A" style="margin-top: 6px;" /> 
						<input type="hidden" name="qanswer1" value="A:" style="margin-top: 6px;" />
						<label style="margin-top: 3px;" name="mulindex">A、</label> 
						<input type="text" id="mulname1" name="qaname"style="width: 80%; margin-top: 6px;" /><br>
						
						<input type="checkbox" value="B" name="qanswer" style="margin-top: 6px;" />
						<input type="hidden" name="qanswer1" value="B:"style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;"name="mulindex">B、</label> 
						<input type="text" id="mulname2"name="qaname" style="width: 80%; margin-top: 6px;" /><br> 
						
						<input type="checkbox" value="C" name="qanswer" style="margin-top: 6px;" />
						<input type="hidden" name="qanswer1" value="C:"style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;"name="mulindex">C、</label> 
						<input type="text" id="mulname3"name="qaname" style="width: 80%; margin-top: 6px;" /><br> 
						
						<input type="checkbox" value="D" name="qanswer" style="margin-top: 6px;" />
						<input type="hidden" name="qanswer1" value="D:"style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;"name="mulindex">D、</label> 
						<input type="text" id="mulname4"name="qaname" style="width: 80%; margin-top: 6px;" /><br>
					</div>
					<!--判断-->
					<div class="answerlist" style="display: none;" >
						<label>判断答案选项:</label><br> 
						<input type="radio" name="qanswer"value="YES" style="margin-top: 6px;" /> 
						<input type="hidden"name="qanswer3" value="A:" style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;">对</label> 
						<input type="hidden"name="qaname2" value="YES" style="width: 80%; margin-top: 6px;" /><br>
	
						<input type="radio" name="qanswer" value="NO"style="margin-top: 6px;" /> 
						<input type="hidden" name="qanswer3"value="B:" style="margin-top: 6px;" /> 
						<label style="margin-top: 3px;">错</label> 
						<input type="hidden" name="qaname2" value="NO" style="width: 80%; margin-top: 6px;" /><br>
					</div>
				</div>
				<div style="margin-top: 10px;">
					<button type="submit" class="btn btn-default">提交</button>
					<button type="reset" class="btn btn-default">重置</button>
				</div>
		</div>
	</form>
</body>
</html>