<%@ page language="java" import="java.util.*,hibernate.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css"/>
<title>Search Result</title>
</head>
<body>
	<jsp:include page="/common/header_search.jsp"></jsp:include>
	<div id="content">
	<div id="main2">
	<jsp:include page="/PaperSearch"></jsp:include>
	<jsp:include page="/AuthorSearch"></jsp:include>
	</div>
	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>