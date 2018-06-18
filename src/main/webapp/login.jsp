<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form action="login.do" method="post">
		<fieldset>
			<legend>登陆</legend>
			用户名:<input name="username"/><br/>
			<%
				String msg=(String)request.getAttribute("login_failed");
			%>
			<span style="color:red;">
			<%=(msg==null?"":msg)%>
			</span><br/>
			密码:<input type="password" name="pwd"/><br/>
			验证码:<input name="number"/>
			<img id="img" alt="验证码" src="checkcode">
			<% 
				String msg2=(String)request.getAttribute("number_error");
			%>
			<span style="color:red">
			<%=(msg2==null?"":msg2)%>
			</span><br/>
			<a href="javascript:;" 
			   onclick="document.getElementById('img').src='checkcode?'+Math.random();">看不清,换一个</a><br/>
			
			<input type="submit" value="确定"/>
		</fieldset>
	</form>
</body>
</html>