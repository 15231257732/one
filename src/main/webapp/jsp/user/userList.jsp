<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    String path=request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
table,tr,th,td{
 border:  1px solid black;
}

</style>
<script type="text/javascript">
$(function(){
	  getTable();
})

$(function(){
	$("[class='btn btn-warning']").click(function(){
		//alert(12);
		$.ajax({
			type:"post",
			url:"<%=path %>/saveOrUpdateAction.do",
			data:{"uid":$("#uid").val(),"uname":$("#uuname").val(),"password":$("#password").val(),"address":$("#address").val(),"telphone":$("#utelphone").val()},
			dataType:"text",
			success:function(res){

			}
		})
	})
	
	$("[class='btn']").click(function(){
		$.ajax({
			type:"post",
			url:"<%=path%>/bynamequeryAction.do",
			data:{"uname":$("#uname").val(),"telphone":$("#telphone").val()},
			dataType:"json",
			success:function(result){
				alert(result);
				getPage(result);
			}
		})
	})
})

function getTable() {
	$.ajax({
		type:"post",
		url:"<%=path%>/queryAction.do",
		data:{},
		dataType:"json",
		success:function(res){
			getPage(res);
		}
	})
}

function getPage(res){
	res=JSON.parse(res);
	//[{"address":"高碑店","sex":"1","telphone":"78","uid":"9","uname":"9","utype":"0"}]
	//alert(typeof(res));  /* 打印res类型  */
	var list=res.list; 
	var html="<thead><tr><th>序号 </th><th>用户名  </th><th>用户类型  </th><th>性别   </th><th>地址 </th><th>电话  </th><th>操作   </th></tr></thead><tbody>";
	for(var i in list){
		var obj=list[i];
		//alert(i);
		html+="<tr>"+
		
		"<td>"+(parseInt(i)+1)+"</td>"+
		"<td>"+obj.uname+"</td>"+
		"<td>"+(obj.utype==0?'管理员':'用户 ')+"</td>"+
		"<td>"+(obj.sex==0?'男':'女')+"</td>"+
		"<td>"+obj.address+"</td>"+
		"<td>"+obj.telphone+"</td>"+
		"<td><a href=\"javaScript:\" onclick=\"to_delete('"+obj.uid+"')\"> 删除 </a> <a><button class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"getByid('"+obj.uid+"')\">修改</button></a></td></tr>"
	}
	html+="</tbody>";
	$("table").html(html);
	//拼分页
	var pagebean=res.pageBean;
	var page=" 总记录数:"+pagebean.count+" ,每页"+pagebean.pagesize+"条,总页数:"+pagebean.pageCount+", 当前第"+pagebean.pageNumber+"页"+     
        "<a href=\"javaScript:trunPage(1)\" style=\"margin-left: 300px;\">首页</a>&nbsp;&nbsp;"+ 
        "<a href=\"javaScript:trunPage("+(pagebean.pageNumber-1)+")\">上一页</a>&nbsp;&nbsp;"+   
        "<a href=\"javaScript:trunPage("+(pagebean.pageNumber+1)+")\">下一页</a>&nbsp;&nbsp;"+ 
        "<a href=\"javaScript:trunPage("+pagebean.pageCount+")\">尾页</a>";   
	$(".page").html(page);
	}
 
 //分页方法
function trunPage(pageNum){
	 //alert(123);
	$.ajax({
		    type:"post",
		    data:{"pageNumber":pageNum},
		    url:"<%=path%>/queryAction.do",
			dataType:"json",
			success:function(result) {
				//alert(result);
				getPage(result);
			}
		});
	}

<%-- 
//拼分页
function getPage(pagebean){
	var page=" 总计："+pagebean.count+" 条, &nbsp;总页数："+pagebean.pageCount+" 页, &nbsp;每页显示："+pagebean.pagesize+" 条, &nbsp;当前：第 -"+pagebean.pageNumber+"- 页"; 
    var sepage="<a href=\"javaScript:trunPage(1)\" >首页</a>&nbsp;&nbsp;"+
    	"<a href=\"javaScript:trunPage("+(pagebean.pageNumber-1)+")\">上一页</a>&nbsp;&nbsp;"+   
        "<a href=\"javaScript:trunPage("+(pagebean.pageNumber+1)+")\">下一页</a>&nbsp;&nbsp;"+ 
        "<a href=\"javaScript:trunPage("+pagebean.pageCount+")\">尾页</a>";
    $("#page").html(page);
	$("#sepage").html(sepage);
}
 --%>
function to_delete(uid) {
		//alert(uid);
		$.ajax({
			type:"post",
			url:"<%=path %>/usredeleteAction.do",
			data:{"uid":uid},
			dataType:"json",
			success:function(res){
				getTable();
			}
		})
	} 
 
 
function getByid(uid) {
	//alert(uid);
	$.ajax({
		type:"post",
		url:"<%=path %>/getByidAction.do",
		data:{"uid":uid},
		dataType:"json",
		success:function(res){
		    //数组的遍历
		    alert(res);
			var d='';
            d= eval("("+res+")"); 
            alert(d.uname);
            $("#uid").val(d.uid);
            $("#uuname").val(d.uname);
            $("#password").val(d.password);
            $("#address").val(d.address);
			$("#utelphone").val(d.telphone);
		}
	})
}

</script>
</head>
<body>
<div id="div_form">
		   <form class="form-inline" role="form">
			  <div class="form-group">
			    <label class="sr-only" for="name">用户名</label>
			    <input type="text" class="form-control" id="uname" placeholder="请输入账号">
			     <input type="text" class="form-control" id="telphone" placeholder="请输入手机号">
			  </div>
			  <button type="button" class="btn">查询</button>
		  </form>
		</div>
 <table class="table table-bordered">
     <%--    <tr><th>用户名</th>
           <th>用户类型</th>
           <th>性别</th>
           <th>地址</th>
           <th>电话</th>
           <th>操作 <a href="<%=path %>/jsp/addOrUpdate.jsp">添加</a></th>
       </tr>
       <c:forEach items="${list}" var="u">
           <tr>
              <td>${u.uname }</td>
              <td>${u.utype==0?'管理员':'用户'}</td>
              <td>${u.sex==0?'男':'女'}</td>
              <td>${u.address }</td>
              <td>${u.telphone }</td>
              <td><a href="<%=path %>/usredeleteAction.do?uid=${u.uid}">删除</a>  <a><button class=".btn-warning" data-toggle="modal" data-target="#myModal" onclick="getByid('${u.uid}')">修改</button></a></td>
           </tr>
       </c:forEach> --%>
   </table>
   
              
	<div class="page">
	 </div>
       <!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" align="center">修改页面</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" action="<%=path%>/saveOrUpdateAction.do" method="post">

						<div class="form-group" style="display: none;">
							<label for="firstname" class="col-sm-2 control-label">uid:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="uid"
									placeholder="请输入id" style="width: 80%">
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">账号:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="uuname"
									placeholder="请输入账号" style="width: 80%">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="password"
									placeholder="请输入密码" style="width: 80%">
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">地址:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="address"
									placeholder="请输入地址" style="width: 80%">
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="utelphone"
									placeholder="请输入电话" style="width: 80%">
							</div>
						</div>
						<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-warning" data-dismiss="modal">提交修改</button>
				</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>