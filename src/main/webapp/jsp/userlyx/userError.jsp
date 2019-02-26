<%@ page language="java"  import="java.util.*" pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath(); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆失败</title>
<style type="text/css">
	div
	{
		margin-top:100px;
		text-align:center;
		color:red;
		font-size:20px;
	}
</style>
</head>
<body>
	<div>登陆失败 10秒后自动到登陆页面</div>
	<script type="text/javascript">
		setTimeout(reDo,10000);
		function reDo()
		{
			windows.location.href = "index.jsp";
		}
	</script>
</body>
</html>