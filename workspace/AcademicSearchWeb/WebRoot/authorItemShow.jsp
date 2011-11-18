<%@ page language="java" import="java.util.*,hibernate.*,sys.*"
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
	%>
	<body>
		<jsp:include page="/common/header_search.jsp"></jsp:include>
		<div id="content">
		<div id="main" style="width: 960px;">
		<div style="float: right;">

			<img class="portray" border="1"
				src="http://www.cs.uiuc.edu/homes/hanj/images/hanj_tour.jpg"
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
				<%if(item.getPosition()!=null) {%>
				<tr>
					<th>
						Title:
					</th>
					<td>
						<%=item.getPosition() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getPosition()!=null) {%>
				<tr>
					<th>
						Affiliation:
					</th>
					<td>
						<%=item.getAffiliation() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getPosition()!=null) {%>
				<tr>
					<th>
						Address:
					</th>
					<td>
						<%=item.getAddress() %>
					</td>
				</tr>
				<%} %>
				<%if(item.getHomepage()!=null) {%>
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
				<%if(item.getLink()!=null) {%>
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
				<%if(item.getInterest()!=null) {%>
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

