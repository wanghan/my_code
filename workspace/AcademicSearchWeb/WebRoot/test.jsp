<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  var array = new Array("#ff0000","#00ffff","#0000ff");
  var iTimerA;
  function mov()
  {
  	document.onmousedown=function()
  	{  
	    iTimerA = window.setInterval(a(0), 1); //鼠示按下时执行a函数  
	}  
	document.onmouseup=function()
	{  
	    window.clearInterval(iTimerA); //鼠标弹起时停止定时器的动作  
	} 
  }
  
  function a(i)
  {
  	document.getElementById("td1").innerHTML=array[i];//背景色
  }
  </script>
  <body>
    <table border="1">
    <tr>
    	<td id="td1" width="100" onclick="mov()">1</td>
    	<td width="100">2</td>
    	<td width="100">3</td>
    	<td width="100">4</td>
    	<td id="td5" width="100">5</td>
    </tr>
    </table>
  </body>
</html>
