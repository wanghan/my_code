<%@ page language="java" import="java.util.*,AUDRwebJavaBeans.*,sys.*" pageEncoding="utf-8"%>
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
  	TextItem item = (TextItem)request.getAttribute("Item");
//	String id = item.getTqr().getId();
	String id="123";
//	String filePath = item.getTqr().getOriginalFilePath();
//	filePath = filePath.replaceAll("\\\\","/");
//	String fileName = item.getTqr().getOriginalFileName();
	String fileName="abc.txt";
	request.setAttribute("fileName",fileName);
  %>
  <body>
  <jsp:include page="/common/header.jsp"></jsp:include>
  	<p>
		当前检索范围: 
		<a href="<%=path %>/SearchText/textSearch.jsp">文本</a>
		<label> ${sessionScope.searchTypeC }</label>
		<a href="javascript:history.go(-1);">搜索结果</a>
		-[<span class="bgc"> ${fileName }</span>]
			<c:forEach items="${avit_avite}" var="ad_ade" varStatus="index">
				<c:choose>
					<c:when test="${avit_avit eq ad_ade}"></c:when>
					<c:otherwise>
						<input type="radio" name="related_radio" value="${ad_ade }" onclick="showRelatedStampDialog(this,'${avit_avitc[index.index] }')">
							${avit_avitc[index.index] }
					</c:otherwise>
				</c:choose>
			</c:forEach>
	</p>
	
	<!-- 显示关联查询 -->
	<div id="show_rs"  class="bgc" style="left: ;top: ;width: px;height: px;z-index: 1000;
		display: none;position: absolute;">
		<div id="avitShowName" style="width: 100%;height: 20px;background-color: black;color: white;text-align: center">
		</div>
		<form id="avit_rs_form" action="" method="post" target="_blank" onsubmit="return theSearch();">
			数据分类
			<select id="rs_sjfl" onchange="theChange('rs_sjfl')"></select>
			<input type="hidden" id="rs_data_type" name="avitType" value="">
			<div style="vertical-align: middle;">
				<table width="100%" height="80%" border="0">
				<!-- 非结构化数据类型 -->
					<tr>
						<td><input type="checkbox" name="cb_rs" id="" value="0">主题</td>
						<td>
							<input type="text" name="avit_rs_cb_0_tx" value="${sessionScope.searchText }">
						</td>
						<td>关联项</td>
						<td>
							<select id="rs_glx0" name="avit_rs_cb_0_sl"></select>
						</td>
					</tr>
					<tr>
						<td height="40px" align="center" colspan="4">
							<input type="submit" value=" search ">
							<input type="button" value=" cancel " onclick="theCancal()">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
  	<center>
	<div class="bodyDiv">
<table width="100%">
	<tr>
		<td>
			<DIV id="showdiv"
				style="Z-INDEX: ; LEFT:100px; TOP: 100px; WIDTH: 570px; HEIGHT: ; 
				POSITION: ;overflow:inherit; display: ">
				
			<c:if test="${fn:substringAfter(showPath,'.') ne ''}">	
			<c:set value="${fn:substringAfter(showPath,'.')}" var="typ"></c:set>
			<c:choose>
			<c:when test="${arraytype[3] eq fn:toUpperCase(typ) }">
			<object id="swfShow" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="570" height="450">
				<param name="movie" value="<%=path %>${showPath }" />
        		<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="<%=path %>${showPath }" width="570" height="450">
				<!--<![endif]-->
				<div>
					<h1>Alternative content</h1>
					<p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
				</div>
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
			</c:when>
			<c:otherwise>
			<iframe name="htmlShow" id="htmlShow" width="570" height="450" 
					src="<%=path %>${showPath }" scrolling="auto" 
					style="display:; ">
			</iframe>
			</c:otherwise>
			</c:choose>	
				
			<hr>
			<span id="tools"><a href='<%=path %>${showPath }' target='_blank'>在新窗口打开</a></span>
			</c:if>
			</DIV>

		</td>
		<td width="40px"></td>
		<td width="370px" valign="top">	
		<hr>
		<a href="<%=path %>/TextDownLoad?type=${tp3 }&path=${tp1 }&name=${tp2 }" target="_">下载此文档</a>
		</td>
	</tr>
</table>
	</div>
	</center>
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

  