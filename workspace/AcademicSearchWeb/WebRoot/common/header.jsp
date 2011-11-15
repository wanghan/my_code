<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title></title>
<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
</head>
<% 
	boolean logined = false;
	String username = "";
	if(session.getAttribute("username")!=null)
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
					<h1>AUDR 科技文献数据检索系统<br></h1>
				</td>
			</tr>
		</table>
	</div>
	<hr style="color: #EEEEEE"/>
</body>
</html>