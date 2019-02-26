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
    <%-- <link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css"> --%>
    <link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.ztree.core.js"></script>
    <style type="text/css">
  /*   body{
    background-color: red;
    } */
    </style>
    <script type="text/javascript">
        var zTreeObj;
        var setting = {
            data:{
                key:{
                    children:"child",
                    name:"name",
                    url:"url"
                },
                simpleData:{
                    enable:false,
                    idKey:"id",
                    pIdKey:"pid",
                    rootPid:"0"
                }
            }
        };
        $(document).ready(function(){
            $.ajax({
                type:"post",
                data:{},
                url:"<%=path%>/ztree/queryAction.do",
                dataType:"json",
                success:function(data){
                    var datas=$.parseJSON(data);
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, datas);
                    $.fn.zTree.getZTreeObj("treeDemo").expandAll(true);
                }
            });
        });
    </script>
</head>
<body>
<div style="float: left">
    <div>
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</div>
</body>
</html>