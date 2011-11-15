<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <script type="text/javascript" src="<%=path %>/js/ajaxHTTPObject.js"></script>
  <script type="text/javascript" src="<%=path %>/js/ajax_file_update.js"></script>
  <script type="text/javascript">
  function checkbox(mark,cnm)
  {
  	var field = document.getElementsByName(cnm);
	for(i = 0;i < field.length;i++)
	{
	   if(mark=="1")
	   {
	   	field[i].checked = true;
	   }else if(mark=="0")
	   {
	   	field[i].checked = false;
	   }
	   else if(mark=="-1")
	   {
	   	field[i].checked = !field[i].checked;
	   }
	}
  }
  
  var xmlHttp = null;
  //显示文件详细信息////////////////////////////////////
  function showmsg(sid,hid,imgid)
  {
  	var showobj = document.getElementById(sid);
  	var hidenobj = document.getElementById(hid);
  	var cfile_type = document.getElementById("cfile_type").value;
	var cfile_subtype = document.getElementById("cfile_subtype").value;
	
	if(cfile_type==""||cfile_subtype=="")
	{	
		alert("错误");
		return;
	}
	else
	{
		showobj.style.display = '';
	  	hidenobj.style.display = 'none';
	  	
	  	xmlHttp = getHTTPObject();
	  	if(xmlHttp==false)
	  	{alert("你的浏览器不支持详细信息查看，请更新浏览器，或者采用最高版本的浏览器");}
	  	else if(sid=="showmsg")
	  	{
	  		selectItemMsg(cfile_type,cfile_subtype,imgid);
	  	}
	}
  }
   function selectItemMsg(cfile_type,cfile_subtype,id){
	
	var url = "ItemFileSelect?cfile_type="+cfile_type+"&cfile_subtype="+cfile_subtype+"&id="+id;
  	xmlHttp.open("GET",url,true);
  	xmlHttp.onreadystatechange = handleHttpResponse;
  	xmlHttp.send(null);
  	
  }
  function handleHttpResponse()
  {
 	if(xmlHttp.readyState==4 && xmlHttp.status == 200)
 	{
 		var ret = xmlHttp.responseText; 
 		document.getElementById('showmsg_show').innerHTML = ret;
 		xmlHttp = null;
 	}
 	else
 	{
 		document.getElementById('showmsg_show').innerHTML = "<h1>正在加载....</h1>";
 	}
  }
  
  //显示文件详细信息////////////////////////////////////
  
  //==============================================
  function fy(url,v)
  {
  	window.location.href = "<%=path%>/ItemsFileSelect?off.set="+v;
  }
  </script>
  <jsp:useBean id="ft" class="Common.FileType"></jsp:useBean>
  <% 
  	String url=request.getScheme()+"://";  
  	url+=request.getHeader("host");  
  	url+=request.getRequestURI();  
  	
  	//获得路径参数
	String cmid[] = ft.cmid;
	String cmv[]  = new String[cmid.length];
	for (int i = 0; i < cmv.length; i++) {
		String tmp = "";
		if(request.getParameter(cmid[i])!=null)
			tmp = request.getParameter(cmid[i]).toString();
		cmv[i] = tmp;
	}
  	url+="?";
  	for (int i = 0; i < cmv.length; i++) {
		if(i<cmv.length-1)
			url+=cmid[i]+"="+cmv[i]+"&";
		else
			url+=cmid[i]+"="+ft.action[1];
	}
	
  	request.setAttribute("url",url);
  %>
  <body>
    
    <!-- 文件列表 -->
    <div id="showlist" style="height:;display: ; ">
    	<div style="background-color: #ffffff;width: 100%;height: 20px;line-height: 20px;text-align: right;">
    		 <a href="${url }"><font color="#000000" size="2"> 重新检索</font></a>
    	</div>
    <form action="<%=path %>/ItemFileDelete" id="iforms" method="post">
    <input type="hidden" name="cfile_type" id="cfile_type" value="${sessionScope.file_type }">
	<input type="hidden" name="cfile_subtype" id="cfile_subtype" value="${sessionScope.file_subtype }">
    	<table width="100%" height="">
    		<tr height="70">
    			<td colspan="4" align="center" valign="middle">
    				<h1>音频文件检索结果</h1>
    			</td>
    		</tr>
    		<tr>
    			<th width="100">
    				歌曲名称
    			</th>
    			<th width="100">
    				歌手
    			</th>
    			<th width="100">
    				专辑
    			</th>
    			<th width="40">
    				文件类型
    			</th>
    		</tr>
    		<c:choose>
    			<c:when test="${!empty fail}">
    				<tr><td colspan="5"><font size="3" color="red">${fail }</font></td></tr>
    			</c:when>
    			<c:when test="${sessionScope.audioes[0].audioName eq '无结果' }">
    				<tr><td colspan="5"><font size="3" color="red">查询结果为空</font></td></tr>
    			</c:when>
    			<c:otherwise>
    			<c:forEach items="${sessionScope.audioes}" begin="${sessionScope.start }" end="${sessionScope.end }" var="ars" varStatus="index">
    			<tr>
	    			<td>
		    				<a href="<%=path %>/ItemFileSelect?cfile_type=${sessionScope.file_type }&cfile_subtype=${sessionScope.file_subtype}&id=${ars.ID }" title="点击编辑详细信息">
		    					${ars.audioName }
		    				</a>
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.singer }</font>
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.album }</font>
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.fileSuffix }</font>
		    		</td>
    			</tr>
	    		</c:forEach>
    			</c:otherwise>
    		</c:choose>
    		
    		
    		<tr>
    			<td colspan="5" align="center">
    			<hr>
    				<div style="border: 1px #000000 solid;width: 270px;height: 20px;line-height: 20px;background-color: ">
    					【<font color="red">${sessionScope.nowpage }</font>/<font color="red">${sessionScope.totpage }</font>】
    					<c:if test="${sessionScope.nowpage <= 1}">
    						<font color="#888888">pre</font>
    					</c:if>
    					<c:if test="${sessionScope.nowpage > 1}">
    						<a href="javascript:void 0;" onclick="fy('${sessionScope.url }','${sessionScope.nowpage-1 }')">pre</a>
    					</c:if>
    					跳转到
    					<select onchange="fy('${sessionScope.url }',this.value)">
    						<c:forEach begin="1" end="${sessionScope.totpage }" var="n">
    							<c:choose>
    								<c:when test="${n eq sessionScope.nowpage}">
    								<option value="${n }" selected="selected">第 ${n } 页</option>
    								</c:when>
    								<c:otherwise>
    								<option value="${n }">第 ${n } 页</option>
    								</c:otherwise>
    							</c:choose>
    						</c:forEach>
    					</select>
    					<c:if test="${sessionScope.nowpage >= sessionScope.totpage}">
    						<font color="#888888">next</font>
    					</c:if>
    					<c:if test="${sessionScope.nowpage < sessionScope.totpage}">
    						<a href="javascript:void 0;" onclick="fy('${sessionScope.url }','${sessionScope.nowpage+1 }')">next</a>
    					</c:if>
    				</div>
    			</td>
    		</tr>
    	</table>
    </form>

    </div>
    
   
  </body>
</html>
