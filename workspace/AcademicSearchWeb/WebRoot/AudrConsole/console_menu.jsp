<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<% 
	String[][] menu = new String[2][2];
	String menue[] = {"file","user"};
	String menuc[] = {"文件管理","用户管理"};
	String menurl[] = {"file","user"};
%>
  <body>
    <div style="width: 100%;background-color: #07A1EE;">
    	<table cellspacing="0">
	    	<tr>
	    		<td width="150" align="center" style="border-right:1px black solid;">
	    			<font color="#ffffff" size="4">AUDR 控制台</font>
	    		</td>
	    		<% 
	    		for(int i=0;i<menue.length;i++)
	    		{
	    		%>
	    		<td width="70" align="center" style="
	    			border-right:1px black solid; ">
	    			<a href="<%=path %>/AudrConsole/ConsoleMain.jsp?cm_to=<%=menurl[i] %>" title="<%=menurl[i] %>"><%=menuc[i] %></a>
	    		</td>
	    		<%} %>
	    	</tr>
	    </table>
    </div>
    
  </body>
</html>
