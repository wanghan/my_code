<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>非结构化数据库管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <jsp:include page="common/header.jsp"></jsp:include>
    <div style="text-align: center;width: 100%;background-color: white;">
    	<font color="red" size="100">AUDR检索系统 部分效果展示</font><br>
    	<img src="pic/indextx.png" title="点击进入图像检索系统" width="400" height="200" border="5">
    	<img src="pic/indexwb.png" title="点击进入文本检索系统" width="400" height="200" border="5"><br>
    	<img src="pic/indexyp.png" title="点击进入音频检索系统" width="400" height="200" border="5">
    	<img src="pic/indexsp.png" title="点击进入视频检索系统" width="400" height="200" border="5"><br>
    	<img src="pic/indextxx.png" title="点击进入管理系统-图像" width="400" height="200" border="5">
    	<img src="pic/indexwbx.png" title="点击进入管理系统-文本" width="400" height="200" border="5"><br>
    	<img src="pic/indexypx.png" title="点击进入管理系统-音频" width="400" height="200" border="5">
    	<img src="pic/indexspx.png" title="点击进入管理系统-视频" width="400" height="200" border="5">
    </div>
    <jsp:include page="common/footer.jsp"></jsp:include>
  </body>
</html>
