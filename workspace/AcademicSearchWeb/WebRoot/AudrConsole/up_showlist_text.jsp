<%@ page language="java" import="java.util.*,AUDRwebJavaBeans.*,sys.*" pageEncoding="utf-8"%>
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
<style type="text/css">

</style>
  </head>
 <script type="text/javascript" src="<%=path %>/js/ajaxHTTPObject.js"></script>
 <script type="text/javascript" src="<%=path %>/js/ajax_file_update.js"></script>
  <script type="text/javascript">
   
  
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
  	
  	ArrayList<TextItem> list = (ArrayList<TextItem>)request.getSession().getAttribute("texts");
  	int start = 0;
   	if(session.getAttribute("start")!=null);
   	try{
   		start = Integer.parseInt(session.getAttribute("start").toString());
   	}
   	catch(Exception e)
   	{}
   	start = (start>=0)?start:0;
   	
   	int end = 0;
   	if(session.getAttribute("end")!=null);
   	try{
   		end = Integer.parseInt(session.getAttribute("end").toString());
   	}
   	catch(Exception e)
   	{}
   	end = (end>=0)?end:0;
  %>
  <body>
    <!-- 文件列表 -->
    <div id="showlist" style="height: ;display: ; ">
    	<div style="background-color: #ffffff;width: 100%;height: 20px;line-height: 20px;text-align: right;">
    		 <a href="${url }"><font color="#000000" size="2"> 重新检索</font></a>
    	</div>
    
    	
    	<input type="hidden" name="cfile_type" id="cfile_type" value="${sessionScope.file_type }">
		<input type="hidden" name="cfile_subtype" id="cfile_subtype" value="${sessionScope.file_subtype }">
		<table width="100%" height="">
    		<tr height="70">
    			<td colspan="4" align="center" valign="middle">
    				<h1>文本文件检索结果</h1>
    			</td>
    		</tr>
    		<tr>
    			<th width="100">
    				文件名
    			</th>
    			<th width="100">
    				上传日期
    			</th>
    			<th width="90">
    				文档上传者
    			</th>
    			<th>
    				其他
    			</th>
    		</tr>
    		<c:choose>
    			<c:when test="${!empty fail || fn:length(sessionScope.texts) eq 0}">
    				<tr><td colspan="4"><font size="3" color="red">${fail }</font></td></tr>
    			</c:when>
    			<c:otherwise>
    			<% 
					if(list != null)
					for(int i=start;i<=end;i++)
					{
						TextItem ttm = ttm = list.get(i);
				%>
				<tr>
	    			<td>
	    				<a href="<%=path %>/ItemFileSelect?cfile_type=${sessionScope.file_type }&cfile_subtype=${sessionScope.file_subtype}&id=<%=ttm.getTqr().getId() %>" title="点击编辑详细信息">
		    				<%=ttm.getTqr().getOriginalFileName() %>
		    			</a>
		    		</td>
		    		<td>
		    			<%=ttm.getTbf().getStrDate() %>
		    		</td>
		    		<td align="center">
		    			<%=ttm.getTbf().getAuthor()%>
		    		</td>
		    		<td>
		    			<%
						int[] nn = {2,3,5,6};
						for(int k=0;k<nn.length;k++){
						if(
							(!ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])).equals(""))
							&&
							(!ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])).equals("nothing"))){%>
						<%=ttm.getTsf().getSingleItem(nn[k]) %>:<%=ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])) %>
						<%}} %>
		    		</td>
    			</tr>	
				<%} %>
				
    			</c:otherwise>
    		</c:choose>
    		
    		<tr>
    			<td colspan="4" align="center">
    			<hr>
    				<div style="border: 1px #000000 solid;width: 270px;height: 20px;line-height: 20px;">
    					【<font color="red">${sessionScope.nowpage }</font>/<font color="red">${sessionScope.totpage}</font>】
    					<c:if test="${sessionScope.nowpage <= 1}">
    						<font color="#888888">pre</font>
    					</c:if>
    					<c:if test="${sessionScope.nowpage > 1}">
    						<a href="javascript:void 0;" onclick="fy('${sessionScope.url }','${sessionScope.nowpage-1 }')">pre</a>
    					</c:if>
    					跳转到
    					<select onchange="fy('${sessionScope.url }',this.value)">
    						<c:forEach begin="1" end="${sessionScope.totpage}" var="n">
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
    </div>
  </body>
</html>
