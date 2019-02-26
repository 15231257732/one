<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-2.2.4.js"></script>
 <script type="text/javascript">
 //显示时间
 function currentTime(){
		var curruntDate=new Date();
		var year=curruntDate.getFullYear();
		var moth=curruntDate.getMonth()+1;
		moth=moth<10?"0"+moth:moth;
		var date=curruntDate.getDate();
		date=date<10?"0"+date:date;
		var hour = curruntDate.getHours(); 
		hour=hour<10?"0"+hour:hour;
		var minute = curruntDate.getMinutes(); 
		minute=minute<10?"0"+minute:minute;
		var second = curruntDate.getSeconds(); 
		second=second<10?"0"+second:second;
		var week = curruntDate.getDay(); 
		var str="";
		if (week == 0) {  
		        str = "星期日";  
		} else if (week == 1) {  
		        str = "星期一";  
		} else if (week == 2) {  
		        str = "星期二";  
		} else if (week == 3) {  
		        str = "星期三";  
		} else if (week == 4) {  
		        str = "星期四";  
		} else if (week == 5) {  
		        str = "星期五";  
		} else if (week == 6) {  
		        str = "星期六";  
		} 
		var temp=year+"-"+moth+"-"+date+"&nbsp;&nbsp;"+str+"&nbsp;&nbsp;"+hour+":"+minute+":"+second;
	    document.getElementById("curruntTime").innerHTML=temp;
	    setTimeout(currentTime,1000);
	}
    function removeInterval(k){
		clearInterval(k);
	}
	setTimeout(currentTime,1000);
</script>
    
<style type="text/css">
.time{
  color:gray;
  margin-left: 75%;
}
.user{
  margin-left: 75%;
}
</style>
</head>
<body>
    <div style="margin-top:5px;text-align: center">
        <h2>欢迎登陆考试后台管理程序</h2>
    </div>
    <div class="time">
    当前时间:<span id="curruntTime"></span>
    </div>
    <div class="user">
      欢迎:&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size: 15px;color: red;">${user.uname}</font>&nbsp;&nbsp;&nbsp;&nbsp;登陆
    </div>
</body>
</html>