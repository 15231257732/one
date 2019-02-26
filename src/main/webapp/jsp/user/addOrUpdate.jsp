<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String path=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <form action="<%=path%>/addAction.do" method="post">
     <input type="hidden" name="id"/>
            用户:<input type="text" name="uname"/></br>
            密码:<input type="password" name="password"/></br>
            住址:<input type="text" name="address"/></br>
            电话:<input type="text" name="telphone"/></br>
     <input type="submit" />
  </form>
</body>
</html>