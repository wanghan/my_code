<%@page import="java.io.File"%>
<%@page import="common.Configuration"%>
<%@ page language="java" import="java.util.*,hibernate.*"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Paper Details</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=path%>/css/bodyCss.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/css/hyperlink.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/css/inner.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/person_info.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/css/viewpub.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/image_utils.js"></script>
	</head>
	<style type="text/css">
.showDeck {
	display: block;
	top: 0px;
	left: 0px;
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
	position: absolute;
	z-index: 3;
	background: #000000;
}
</style>

	<%
		DbAuthor item = (DbAuthor) request.getAttribute("author");
		String imagepath=path+"/author_profile/no_image.jpg";

		String newPath=Configuration.getInstance().getProfileImagePath()+String.valueOf(item.getId())+".jpg";
		if(new File(newPath).exists()){
			imagepath=newPath;
		}
	%>
	<body>
		<jsp:include page="/common/header_search.jsp"></jsp:include>
		<div id="content">
		<div id="main" style="width: 960px;">
		<div style="float: right;">

			<img class="portray" border="1"
				src="<%=imagepath %>"
				style="" width="250" onload="resizeimg(this, 250, 500);">

		</div>

		<div id="basicinfo"
			style="float: left ;display: block;max-width:650px;">
			<div
				style="font-weight: bold; vertical-align: bottom; margin-top: 10px;">
			</div>

			<h1 style="font-weight: bold; text-align: center;">
				<%=item.getName() %>
			</h1>

			<table id="viewperson">
				<%if(item.getPosition()!=null&&item.getPosition().length()>0) {%>
				<tr>
					<th>
						Title:
					</th>
					<td>
						<%=item.getPosition() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getAffiliation()!=null&&item.getAffiliation().length()>0) {%>
				<tr>
					<th>
						Affiliation:
					</th>
					<td>
						<%=item.getAffiliation() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getAddress()!=null&&item.getAddress().length()>0) {%>
				<tr>
					<th>
						Address:
					</th>
					<td>
						<%=item.getAddress() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getHomepage()!=null&&item.getHomepage().length()>0) {%>
				<tr>
					<th>
						Homepage:
					</th>
					<td style="word-wrap: break-word; word-break: break-all;">
						<a href="<%=item.getHomepage()%>" target="_blank"
							class="url"><%=item.getHomepage()%></a>
					</td>
				</tr>
				<%} %>
				<%if(item.getLink()!=null&&item.getLink().length()>0) {%>
				<tr>
					<th>
						ACM Library Page:
					</th>
					<td style="word-wrap: break-word; word-break: break-all;">
						<a class="url" href="<%=item.getLink()%>" target="_blank"
							class="url"><%=item.getLink()%></a>
					</td>
				</tr>
				<%} %>
				<tr>
					<th>
						Indexed Papers:
					</th>
					<td>
						<%=item.getDbPapers().size() %>
					</td>
				</tr>
				<%if(item.getInterest()!=null&&item.getInterest().length()>0) {%>
				<tr>
					<th>
						Research Interest:
					</th>
					<td>
						<%=item.getInterest() %>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
		<div id="main">
		<table width="100%">
		<tr><td>Publications:</td></tr>
					<% 
					if(item.getDbPapers()!=null){
					DbPaper[] pubs=(DbPaper[])item.getDbPapers().toArray(new DbPaper[0]);
					for(int i=0;i<pubs.length;i++)
					{
						DbPaper ttm = new DbPaper();
						ttm = pubs[i];
						
						if(i%2==0){
					%>
					<tr>
					<%}else{ %>
					<tr bgcolor="#eeeeee">
					<%} %>
						<td>
						<div id="publist">
							<strong>
								<a class="url" href="<%=path %>/PaperItemShow?fid=<%=ttm.getId() %>">
								<%=ttm.getTitle() %>
								</a>

							</strong>
							<br/>
							<li>
							<%
							DbAuthor []authors=(DbAuthor [])ttm.getDbAuthors().toArray(new DbAuthor[0]);
							for(int j=0;j<authors.length-1;j++)
							{
							%>
								<a href="<%=path %>/authorItemShow?fid=<%=authors[j].getId()%>"><%=authors[j].getName()%></a>, 
							<%}%>
							<a href="<%=path %>/authorItemShow?fid=<%=authors[authors.length-1].getId()%>"><%=authors[authors.length-1].getName()%></a> 
							<br/></li>

						</div>
						</td>
					</tr>
					<%}} %>
					</table>
		</div>
		</div>
		</div>
		<jsp:include page="/common/footer.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
	var posX;
	var posY;
	fdiv = document.getElementById("show_rs");
	document.getElementById("avitShowName").onmousedown = function(e) {
		if (!e)
			e = window.event; //如果是IE
		posX = e.clientX - parseInt(fdiv.style.left);
		posY = e.clientY - parseInt(fdiv.style.top);
		document.onmousemove = mousemove;
	}
	document.onmouseup = function() {
		document.onmousemove = null;
	}
	function mousemove(ev) {
		if (ev == null)
			ev = window.event;//如果是IE
		fdiv.style.left = (ev.clientX - posX) + "px";
		fdiv.style.top = (ev.clientY - posY) + "px";
	}
</script>

