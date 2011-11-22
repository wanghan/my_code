<%@ page language="java" import="java.util.*,hibernate.*,java.io.*,common.*" pageEncoding="utf-8"%>
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
	<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
	<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/person_info.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/viewpub.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/image_utils.js"></script>
  </head>
  <style type="text/css">
    .showDeck {
        display:block;
        top:0px;
        left:0px;
        margin:0px;
        padding:0px;
        width:100%;
        height:100%;
        position:absolute;
        z-index:3;
        background:#000000;
    }
  </style>
  
  <% 
  	DbPaper item = (DbPaper)request.getAttribute("Item");
//	String id = item.getTqr().getId();
	String id="123";
//	String filePath = item.getTqr().getOriginalFilePath();
//	filePath = filePath.replaceAll("\\\\","/");
//	String fileName = item.getTqr().getOriginalFileName();
	String fileName="abc.txt";
  %>
  <body>
  <jsp:include page="/common/header_search.jsp"></jsp:include>
  	<div id="content">
			<div id="main">

				<h2 class="title"><%=item.getTitle() %></h2>
				

				<div class="author_info">
					<span class="name">Authors: </span>
					<ul class="authorlist">
					<table>
						<%
						DbAuthor [] authors=(DbAuthor [])item.getDbAuthors().toArray(new DbAuthor[0]);
						for(int i=0;i<authors.length;++i) 
						{	
						%>
						<tr>
						<td>
						<%String imagepath="/profile/no_image.jpg";

						String newPath="/profile/"+String.valueOf(authors[i].getId())+".jpg";
						String realPath="H:/author_profile/"+String.valueOf(authors[i].getId())+".jpg";
						if(new File(realPath).exists()){
							imagepath=newPath;
						} 
						%>
						<img src="<%=imagepath %>" align="center" border="1" width="70" onload="resizeimg(this,70,70)">
						</td>
						<td>
						<li><a href="<%=path %>/authorItemShow?fid=<%=authors[i].getId()%>"><%=authors[i].getName() %></a></li>
						</td>
						</tr>
						
						
						<%} %>
					</table>
					</ul>
					
				</div>
			
			
				<div class="paper_info">
					<a name='Top'></a>

				
						
						<li>
							<b>Abstract: </b>
							<br />
							<p style="text-indent: 2em"><%=item.getAbstract_() %></p></li>
						</div>
						</div>	
						<div id="main">
						<div class="paper_info1">
						<li>
							<b>Year: </b><%=String.valueOf(1900+item.getDbConference().getDate().getYear()) %></li>

						<li>
							<b>Proceedings:  </b><%=item.getDbConference().getName() %>
							&nbsp;&nbsp;&nbsp;
							<%if(item.getPages()!=null){ %>
							<%=item.getPages() %>
							<%} %>
						</li>
						
						<li>
							<%if(item.getSource()!=null){ %>
							<b>Source Link:  </b>
							<a class="url" href="<%=item.getSource()%>">
							<%=item.getSource()%></a>
							<%} %>
						</li>
						
						<li>
							<%if(item.getDoiLink()!=null&&item.getDoi()!=null){ %>
							<b>Doi:  </b>
							<a class="url" href="<%=item.getDoiLink()%>">
							<%=item.getDoi()%></a>
							<%} %>
						</li>
						</div>
						</div>
				</div>
<jsp:include page="/topicAssociateShow.jsp"></jsp:include>
<jsp:include page="/common/footer.jsp"></jsp:include>
  </body>
</html>
<script type="text/javascript">
var posX;
var posY;  
fdiv = document.getElementById("show_rs");  
document.getElementById("avitShowName").onmousedown=function(e)
{
  if(!e) e = window.event; //如果是IE
  posX = e.clientX - parseInt(fdiv.style.left);
  posY = e.clientY - parseInt(fdiv.style.top);
  document.onmousemove = mousemove;  
}
document.onmouseup = function()
{
  document.onmousemove = null;
}
function mousemove(ev)
{
  if(ev==null) ev = window.event;//如果是IE
  fdiv.style.left = (ev.clientX - posX) + "px";
  fdiv.style.top = (ev.clientY - posY) + "px";
}
</script>

  