<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>	
	<%
		//进行session验证
		Object obj=session.getAttribute("user");
		if(obj==null){
			//没有登陆
			response.sendRedirect("login.jsp");
			return;
		}
	%>
	<p align="center" style="text-color:red;font-size:35px;">登陆成功</p>
</body>
</html>