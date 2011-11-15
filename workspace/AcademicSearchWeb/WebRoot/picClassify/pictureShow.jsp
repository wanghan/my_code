<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>图像编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <jsp:include page="/common/header.jsp"></jsp:include>
  
  
<center>
<div class="bodyDiv">

	<div style="height: 20px;width: 100%;background-color: #6699cc;text-align: left;">
		查看
		评论
	</div>
	<div style="border-bottom: 1px black solid;
		border-left: 1px black solid;
		border-top: 1px black solid;
		border-right: 1px black solid;
		line-height:510px;
		height: 510px;width: 100%;overflow: auto;">
		
		
		<img src="<%=path %>/${bigPic }" title="">
	</div>
	
	<div style="height: 20px;width: 100%;background-color: #6699cc;text-align: left;">
		
	</div>
	<div style="border-bottom: 1px black solid;
		border-left: 1px black solid;
		border-top: 1px black solid;
		border-right: 1px black solid;
		height: 450px;width: 100%;">
		
		<div style="height: 70px;width: 100%;text-align: left;">
		
		</div>
		<textarea rows="20" cols="107" style="overflow: auto;"></textarea>
		<br><br>
		<input type="submit" value=" submit ">
	</div>
	
</div>
</center>
<jsp:include page="/common/footer.jsp"></jsp:include>
  </body>
</html>
