<%@ page language="java" import=" java.util.*"  contentType="text/html; charset=utf-8"
pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AUDR Academic Search</title>
<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
		
<!--    <link rel="shortcut icon" href="image/images.icon" /> -->
<script type="text/javascript" src="<%=path %>/js/textSearch.js"></script>
<script type="text/javascript" src="<%=path %>/js/calendar.js"></script>

<!--
<script src="js/other.js"></script>
-->
</head>

<jsp:useBean id="tt" class="AUDRwebJavaBeans.SearchType"></jsp:useBean>
<% 
	String[] tfe = tt.search_type;
	request.setAttribute("tfe",tfe);
%>
<body onload="init('<%=path %>','${tfe[0] }')">
   <jsp:include page="/common/header.jsp"></jsp:include>

	<center>
	
	<form action="<%=path %>/TextSearch" method="post" name="" onsubmit="return sub('searchText')">
	<table>
		<tr>
			<td>
				<p>
					<input type="text" name="searchText" size="100" style="height: 37px;width: 450px;font-size: 24">
					<input type="submit" value=" Search " style="height: 37px;width: 170px;font-size: 24">
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>
					<c:forEach items="${tfe}" var="tfe0" varStatus="index">
						<input type="radio" name="fileType" id="${tfe0 }" value="${tfe0 }" />${ tfe[index.index]}
					</c:forEach>
				</p>
			</td>
		</tr>
	</table>
	
	
	
	<div class="bodyDiv">
	<hr color="#6699CC"/>	

	</div>
	
	</form>	
	
	</center>

	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>
