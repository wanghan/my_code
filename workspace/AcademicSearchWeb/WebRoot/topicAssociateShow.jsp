<%@page import="rmi.AssociateResult"%>
<%@ page language="java" import="java.util.*,hibernate.*, AUDRwebJavaBeans.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Associate Results</title>
    
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
  	List<TopicAssociate> item = (List<TopicAssociate>)request.getAttribute("TopicAssociates");
  %>
  <body>
  <div id="main2">
  	<div id="left_panel">
			
			<table>
			<%for(int i=0;i<item.size();++i){
				if(i%2==0){
					%>
					<tr>
					<%}else{ %>
					<tr bgcolor="#eeeeee">
					<%} %>
					
					
					<td>
						<div id="publist">
						<%=item.get(i).getTitle() %>
						</br>
						<table>
						<%for(int k=0;k<5;++k){
							DbPaper ttm=item.get(i).getTopPapers().get(k);
						%>
							<tr>
							<strong>
								<a class="url" href="<%=path %>/PaperItemShow?fid=<%=ttm.getId() %>">
								<%=ttm.getTitle() %>
								</a>

							</strong>
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
							</tr>
						<%} %>
						</table>
						</div>
						</td>
					</tr>
			<%} %>
			</table>
			</div>
	</div>
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

  