<%@ page language="java" import="java.util.*,hibernate.*,sys.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文本检索 文本详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
	<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/textShow.js"></script>
	<script type="text/javascript" src="<%=path %>/js/textShow_rs.js"></script>

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
  <jsp:include page="/common/header.jsp"></jsp:include>
  	<div id="content">
			<div id="main">

				<h2 class="title">Data Mining: An Overview from a Database Perspective.</h2>
				<div class="paper_op_links">
					&nbsp;<a href="http://www.informatik.uni-trier.de/~ley/db/journals/tkde/tkde8.html#ChenHY96" class = "yellow-link">[DBLP_Link]</a>&nbsp;<a href="http://www.informatik.uni-trier.de/~ley/db/journals/tkde/ChenHY96.html" class = "yellow-link">[Online_Version]</a>
<span class='text_link'>CitedBy 1672</span>
<span>&nbsp;<a href="dev.do?m=downloadpdf&url=
http://arnetminer.org/pdf/PDFFiles2/--d---d-1253785579187/Data Mining  An Overview from a Database Perspective1253797231235.pdf
" class = "locallink">[PDF]
</a></span>

				</div>

				<div class="author_info">
					<span class="name">Authors: </span>
					<ul class="authorlist" id="imws_authors" imws:type="PersonInfo.Tiny"
						imws_param:ids="6987,745329,265966">
						<li>Ming-Syan Chen</li><li>Jiawei Han</li><li>Philip S. Yu</li>
					</ul>
					
					<script type="text/javascript"><!--
google_ad_client = "ca-pub-4644025872977348";
/* in paper */
google_ad_slot = "8758650456";
google_ad_width = 250;
google_ad_height = 250;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>	
					
				</div>

				<div class="paper_info">
					<a name='Top'></a>
					<a href="#Reference" class="tab">Reference</a> |
					<a href="#CitedBy" class="tab">Cited By</a> |
					<a href="#LNCS" class="tab">Latex</a>

					<ul class="pubdetail">
						
						<li>
							<b>Abstract: </b>
							<br />
							<p style="text-indent: 2em">Mining information and knowledge from large databases has been recognized by many researchers as a key research topic in database systems and machine learning, and by many industrial companies as an important area with an opportunity of major revenues. Researchers in many different fields have shown great interest in data mining. Several emerging applications in information providing services, such as data warehousing and on-line services over the Internet, also call for various data mining techniques to better understand user behavior, to improve the service provided, and to increase the business opportunities. In response to such a demand, this article is to provide a survey, from a database researcher's point of view, on the data mining techniques developed recently. A classification of the available data mining techniques is provided, and a comparative study of such techniques is presented.</p>
						</li>
						
						<li>
							<b>Year: </b>1996</li>
						
						<li>
							<b>Pages: </b>18</li>
						

						
						<li>
							<b>Article:  </b>IEEE Trans. Knowl. Data Eng.
						</li>

						
					</ul>
				</div>
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

  