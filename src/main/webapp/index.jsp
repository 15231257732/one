<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path=request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
#form_div{
   margin: 130px auto;
   border: 2px solid green;
   border-left-color:blue;
   border-right-color:black;
   border-top-color:green;
   border-bottom-color:red;   
   width: 400px;
   height:175px;
   border-radius: 15px;
}
#login{
   border: 1px solid blue;
   border-left-color:black;
   border-right-color:blue;
   border-top-color:red;
   border-bottom-color:green;
/*   border-radius: 15px; */
  margin: 150px auto;
  height:450px;
  width: 750px;
  background-image: url("image/login.png");
}
#button{
   margin-top:30px;
   margin-left: 25%;
}
#buttonn{
   margin-left: 27%;
}
#name_span,#pwd_span{
   color: red;
}
</style>
<script type="text/javascript">
function login() {
var o=document.getElementById("uname").value;
var p=document.getElementById("password").value;
	if(o=="" || o==undefined){
		alert("姓名不能为空");
	}
	if(p=="" || p==undefined){
		alert("密码不能为空");

	}
}
</script>
</head>
<body>
    <div id="login">
   <div id="form_div">
      <form action="<%=path%>/loginAction.do" method="post">
         <div id="button">
            <label>用户名:</label><input type="text" placeholder="用户名" name="uname" id="uname" />
         </div>
         <div style="margin-left: 25%">
            <label>密&nbsp;&nbsp;&nbsp;码:</label><input type="password" placeholder="密码" name="password" id="password" />
         </div>
         <!-- <div style="margin-left: 25%">
            <label>验证码:</label>
         </div> -->
			<div style="margin-left: 27%">
				<input type="checkbox" name="isRemeber" /><font size="2">记住账号</font>
				<label> <font size="1">用户类型：</font> 
				<select name="utype"  style="font-size: 10px">
						<option value="1">考生</option>
						<option value="0">管理员</option>
				</select>
				</label>
			</div>
         <div style="width: 180px;margin-left: 100px;margin-bottom: 30px">
             <button type="submit" class="btn btn-info btn-block" onclick="login()">登&nbsp;&nbsp;&nbsp;陆</button>
         </div>
      </form>
   </div>
   </div>
</body>
</body>
</html>