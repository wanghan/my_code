<%@page import="client.rmi.SearchRMIClient"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>AUDR Academic Search</title>
		<link href="<%=path%>/css/bodyCss.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/css/hyperlink.css" rel="stylesheet"
			type="text/css" />

		<!--    <link rel="shortcut icon" href="image/images.icon" /> -->
		<script type="text/javascript" src="<%=path%>/js/textSearch.js"></script>
		<script type="text/javascript" src="<%=path%>/js/calendar.js"></script>
		<style type="text/css">
		body,html {
			margin: 0;
			padding: 0;
			font: 12px;
			height: 100%;
		}
		
		#container {
			min-height: 100%;
			position: relative;
			text-align: center;
		}
		
		#footer {
			position: absolute;;
			bottom: 0;
			padding: 10px 0;
			width: 100%;
			text-align: center;
		}
		</style>
		<!--
<script src="js/other.js"></script>
-->
	</head>
	<%
		SearchRMIClient.getInstance();
	%>
	<body onload="init('<%=path%>','${tfe[0] }')">
		<jsp:include page="/common/header.jsp"></jsp:include>

		<center>

			<form action="<%=path%>/SearchResult" method="post" name=""
				onsubmit="return sub('searchText')">
				<table>
					<tr>
						<td>
							<p>
								<input type="text" name="searchText" size="100"
									style="height: 37px; width: 450px; font-size: 24">
								<input type="submit" value=" Search "
									style="height: 37px; width: 170px; font-size: 24">
							</p>
						</td>
					</tr>
				</table>

			</form>

		</center>
		<div  id="footer">
			<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
