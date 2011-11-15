<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title></title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
</head>
<% 
	boolean logined = false;
	String username = "";
	
	if(session.getAttribute("username")==null)
	{
		out.println("<script>alert('你未登录，不具有此操作权限!')</script>");
    	out.println("<script>parent.window.location.href=\""+path+"/AudrConsole/userlogin.jsp\";</script>");
    	return;
	}
	else
	{
		username = session.getAttribute("username").toString();
		
		if(!username.toString().trim().equals(""))
		{logined = true;}
	}
	//request.setAttribute("logined",logined);
%>
<body>
	<div style="height: 87px;;">
		<table width="100%">
			<tr>
				<td width="170" valign="top">
					<a href="<%=basePath %>">
						<img width="170" height="70" border="0" src="<%=path %>/img/logo.png">
					</a>
				</td>
				<td valign="bottom">
					<h1>非结构化数据库管理系统</h1>
				</td>
				<td bgcolor="" width="270" valign="top" align="right">
					<%if(logined){ %>
					<font color="#888888" size="2">当前用户：</font>
					<font color="red"><%=username %></font>
					<a href="<%=path %>/AudrConsole/ConsoleMain.jsp">我的控制台</a>
					<a href="UserLogout">注销</a>
					<%}else{ %>
					<a href="<%=path %>/AudrConsole/userlogin.jsp">管理员登录</a>
					<%} %>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/AudrConsole/console_menu.jsp"></jsp:include>
	<hr style="color: #EEEEEE"/>
</body>
</html>