<%@ page language="java" import="java.util.*,AUDRwebJavaBeans.*,sys.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>

  <body>
	<jsp:useBean id="t" class="Common.FileType"></jsp:useBean>
	<% 
		String[] bfe = t.txt_bfe;
		String[] sfe = t.txt_sfe;
		request.setAttribute("bfe",bfe);
		request.setAttribute("sfe",sfe);
		
		String[] bfc = t.txt_bfc;
		String[] sfc = t.txt_sfc;
		request.setAttribute("bfc",bfc);
		request.setAttribute("sfc",sfc);
		
		TextItem item = (TextItem)request.getAttribute("Item");
		String[] bfv = new String[6];
		if(item!=null)
		{
			bfv[0] = item.getTbf().getBfid();
			bfv[1] = item.getTbf().getStrDate();
			bfv[2] = item.getTbf().getAuthor();
			bfv[3] = item.getTbf().getFilePath();
			bfv[4] = item.getTbf().getFileName();
			bfv[5] = item.getTbf().getFileSize();
		}
		request.setAttribute("bfv",bfv);
	%>

    <table width="100%" border="0">
    	<tr>
    		<td colspan="3">
    			<input type="hidden" name="cfile_type" id="cfile_type" value="${sessionScope.file_type }">
				<input type="hidden" name="cfile_subtype" id="cfile_subtype" value="${sessionScope.file_subtype }">
    		</td>
    	</tr>
    	<tr>
    		<td width="200">ID：</td>
    		<td width="200">
    			<font color="red">${bfv[0] }</font>
    			<input type="hidden" name="id" value="${bfv[0] }"></td>
    		<td></td>
    	</tr>
    	<tr>
    		<td colspan="3" bgcolor="#eeeeee">基本属性信息</td>
    	</tr>
    	<c:forEach items="${bfe}" var="e" varStatus="index">
    	<tr height="30">
			<td>
				${bfc[index.index] }
			</td>
			<td>
				${bfv[index.index+1]}
			</td>
		</tr>
		</c:forEach>
		<tr>
    		<td colspan="3" bgcolor="#eeeeee">扩展属性信息</td>
    	</tr>
		<% 
    	for(int i=2;i<sfe.length;i++){
    	%>
    	<tr height="30">
    		<td valign="top">
				<%=sfc[i-2] %>
			</td>
			<td>
				<%=item.getTsf().getAllItemsValue().get(item.getTsf().getSingleItem(i)) %>
			</td>
		</tr>
		<%} %>
    	
    </table>

  </body>
</html>