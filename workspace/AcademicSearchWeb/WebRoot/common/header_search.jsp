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
<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<form action="<%=path %>/SearchResult" method="post" name="" onsubmit="return sub('searchText')">
	<div style="height: 87px;;">
		<table width="100%">
			<tr>
				<td width="170" valign="top">
					<a href="<%=basePath %>">
						<img width="170" height="70" border="0" src="<%=path %>/img/logo.png">
					</a>
				</td>
				<td valign="bottom">
					<h5>AUDR 科技文献数据检索系统<br></h5>
				</td>
				<td valign="bottom">
				<p>
					<input type="text" name="searchText" size="100" style="height: 30px;width: 300px;font-size: 24">
					<input type="submit" value=" Search " style="height: 30px;width: 130px;font-size: 24">
				</p>
				</td>
			</tr>
		</table>
	</div>
	<hr style="color: #EEEEEE"/>
	</form>	
</body>
</html>