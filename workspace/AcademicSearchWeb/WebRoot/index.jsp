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
			text-align: center;  
		}
		.txtcss1 {
			text-align: left;
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

			<div style="width: 70%; float: center">
			<div>
			<br>
			<h3 style="font-size: 17px;">AUDR简介</h3>
			<br>
			</div>
			<div class="txtcss1" style="font-family: Microsoft YaHei, Arial, Comic Sans MS; font-size: 14px; padding-left: 20px; padding-right: 50px; line-height: 24px;">
			&nbsp; &nbsp; &nbsp;
			非结构化数据管理系统Advanced Unstructured Data Repository（AUDR），其基于四面体模型来表征非结构化数据，具有可扩展的系统体系架构，能管理图像，文本，视频，音频等四种主流的非结构化数据，提供对其基本的增加、删除、修改和查询功能，还提供诸如关联检索、数据挖掘等可扩展性的功能，并提供不同类型的操作接口。除此之外，AUDR还拥有海量非结构化数据处理能力，具备分布式存储与并行计算能力。为了方便对非结构化数据的管理，AUDR还附带了相关的标注工具，分布式集群管理工具，数据导入导出工具，以及模式管理工具等。
			</div>
			<div>
			<br>
			<h3 style="font-size: 17px;">AUDR科技数据管理系统平台简介</h3>
			<br>
			</div>
			<div class="txtcss1" style="font-family: Microsoft YaHei, Arial, Comic Sans MS; font-size: 14px; padding-left: 20px; padding-right: 50px; line-height: 24px;">
			&nbsp; &nbsp; &nbsp;
			AUDR科技数据管理系统是由北京航空航天大学软件开发环境国家重点实验室非结构化数据组开发的一个管理科技数据等非结构化数据的一个应用系统，该系统是非结构化数据管理的一个典型应用实例.
			<br>
			&nbsp; &nbsp; &nbsp; 目前系统对普通用户支持图像数据的底层检索（以图检图），语义检索，基于四面体模型面与面的关
			联检索以及分类检索。对文本数据支持全文检索，语义检索以及分类检索。对于视频数据支持基于关键
			帧检索，语义检索。对于注册用户提供数据的下载功能。
			<br>
			&nbsp; &nbsp; &nbsp;
			本系统是AUDR科技数据管理系统下得子系统，目前系统共有科技文献数据25,000余条，作者数据40,000余条。
			</div>
			</div>
				</center>
		<div  id="footer">
			<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
