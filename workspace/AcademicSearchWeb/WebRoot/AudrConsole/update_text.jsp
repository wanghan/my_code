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
<script type="text/javascript" src="<%=path%>/js/ajaxHTTPObject.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajax_file_update.js"></script>
<link href="<%=path%>/css/uploadify.css" rel="stylesheet"
			type="text/css" />
<script type="text/javascript">

//////////////////////////////////////
var idarr = new Array();
function chg(id)
{
	var iv = id+"_v";
	var it = id+"_t";
	
	var d = document.getElementById(id);
	var v = document.getElementById(iv);
	var t = document.getElementById(it);
	
	if(d.value!=v.value)
	{
		t.innerHTML = "<img width='12' height='12' src='<%=path%>/img/bplus.png' onerror='this.style.display=\"none\"'><font color='red'>已修改</font>";
		idarr.push(id);
	}
	else
	{
		t.innerHTML = "";
		idarr.remove(id);
	}
}
	
Array.prototype.remove=function(dx)
{
  if(isNaN(dx)||dx>this.length){return false;}
  for(var i=0,n=0;i<this.length;i++)
  {
      if(this[i]!=this[dx])
      {
          this[n++]=this[i]
      }
  }
  this.length-=1
}
//重置按钮的第二事件
function rst()
{
	if(idarr!=null)
	{
		for(i=0;i<idarr.length;i++)
		{
			document.getElementById(idarr[i]+'_t').innerHTML = "";
		}
	}
}


</script>
  <body onload="file_update_init('<%=path %>')">
  <div style="background-color: #ffffff;width: 100%;height: 20px;line-height: 20px;text-align: right;">
  		<a href="<%=path %>/ItemsFileSelect?requery=yes&cfile_subtype=${sessionScope.file_subtype }&cfile_type=${sessionScope.file_type }"><font color="#000000" size="2">返回列表</font></a></div>
  		
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
    <form action="<%=path %>/ItemFileUpdate" name="updateForm" id="updateForm" method="post">
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
				<input type="text" name="${bfe[index.index] }" id="${bfe[index.index] }" value="${bfv[index.index+1]}" onblur="chg(this.id)">
				<input type="hidden" name="${bfe[index.index] }_v" id="${bfe[index.index] }_v" value="${bfv[index.index+1]}">
			</td>
			<td id="${bfe[index.index] }_t">
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
				<input type="text" name="<%=sfe[i-2] %>" id="<%=sfe[i-2] %>" 
				value="<%=item.getTsf().getAllItemsValue().get(item.getTsf().getSingleItem(i)) %>" onblur="chg(this.id)">
				<input type="hidden" name="<%=sfe[i-2] %>_v" id="<%=sfe[i-2] %>_v" 
				value="<%=item.getTsf().getAllItemsValue().get(item.getTsf().getSingleItem(i)) %>">
			</td>
			<td id="<%=sfe[i-2] %>_t">
			</td>
		</tr>
		<%} %>
    	
    	<tr>
    		<td colspan="3">
    			<hr>
				<table width="100%">
			    	<tr>
			    		<td width="100" align="center">
			    			<a href="<%=path %>/ItemFileSelect?cfile_type=${sessionScope.file_type }&cfile_subtype=${sessionScope.file_subtype}&id=${pre }" >
			    				<img border="0" width="40" height="40" src="<%=path %>/img/file_left.png" title="上一个">
			    			</a>
			    		</td>
			    		<td align="center">
			    			<input type="button" value=" 保存修改 " id="updateSave" onclick="updateSubmit()">
							<input type="reset" value=" 重置 " onclick="rst()">
			    		</td>
			    		<td width="100" align="center">
			    			<a href="<%=path %>/ItemFileSelect?cfile_type=${sessionScope.file_type }&cfile_subtype=${sessionScope.file_subtype}&id=${nxt }" >
			    				<img border="0" width="40" height="40" src="<%=path %>/img/file_rigth.png" title="上一个">
			    			</a>
			    		</td>
			    	</tr>
			    </table>
			</td>
    	</tr>
    </table>
    </form>
  </body>
</html>