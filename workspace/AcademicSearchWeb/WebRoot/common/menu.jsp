<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	String[][] menu = new String[3][4];
	String menue[] = {"image","text","audio","video"};
	String menuc[] = {"图像","文本","音频","视频"};
	String menurl[] = {"/SearchImage/mainimage.jsp","/SearchText/textSearch.jsp","/SearchAudio/mainaudio.jsp","/SearchVideo/mainvideo.jsp"};
%>
  <body>
    <div style="width: 100%;background-color: #07A1EE;">
    	<table cellspacing="0">
	    	<tr>
	    		<td width="150" align="center" style="border-right:1px black solid;">
	    			<font color="#ffffff" size="4">AUDR </font>
	    			<c:choose>
	    			<c:when test="${sessionScope.userpower ne null}">
	    			<font color="#ffffff" size="4">控制台</font>
	    			</c:when>
	    			<c:otherwise>
	    			<font color="#ffffff" size="4">检索系统</font>
	    			</c:otherwise>
	    			</c:choose>
	    		</td>
	    		<% 
	    		for(int i=0;i<menue.length;i++)
	    		{
	    		%>
	    		<td width="70" align="center" style="
	    			border-right:1px black solid; ">
	    			<a href="<%=path+menurl[i] %>"><%=menuc[i] %></a>
	    		</td>
	    		<%} %>
	    	</tr>
	    </table>
    </div>
    
  </body>
</html>
