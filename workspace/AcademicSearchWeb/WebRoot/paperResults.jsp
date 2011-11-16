<%@ page language="java" import="java.util.*,AUDRwebJavaBeans.*,sys.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
<title>文本检索 文本检索结果列表</title>
</head>
<script type="text/javascript">
function init(id)
{
	document.getElementById(id).checked = true;
}
function setSType(e,c)
{
	document.getElementById("searchTypeE").value = e;
	document.getElementById("searchTypeC").value = c;
	document.getElementById("searchTypeC0").innerHTML = "-"+c;
}

String.prototype.trim=function(){        
    return this.replace(/(^\s*)|(\s*$)/g, '');     
};
function sub(str){
	var v = document.getElementById(str).value;  
	if(v.trim()=="")
	{      
		window.location.href="<%=path%>/textSearch.jsp"; 
    	return false;
    }
    else
    	return true;   
}  
</script>
<jsp:useBean id="tt" class="AUDRwebJavaBeans.SearchType"></jsp:useBean>
<% 
	//当前所有文件类型[中、英]
	String[] tfe = tt.search_type;
	request.setAttribute("tfe",tfe);

	ArrayList<TextItem> list = (ArrayList<TextItem>)request.getSession().getAttribute("list");
	request.setAttribute("list",list);
%>
<body onload="init('${tfe[0] }')">
	<jsp:include page="/common/header.jsp"></jsp:include>
	<center>

	<form action="<%=path %>/TextSearch" method="post" name="" onsubmit="return sub('searchText')">
	<table>
		<tr>
			<td>
				<p>
					<input type="text" name="searchText" value="${sessionScope.searchText }" size="100" style="height: 37px;width: 450px;font-size: 24">
					<input type="submit" value=" Search " style="height: 37px;width: 170px;font-size: 24">
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>
					<c:forEach items="${tfe}" var="tfe0" varStatus="index">
						<input type="radio" name="fileType" id="${tfe0 }" value="${tfe0 }"/>${ tfe[index.index]}
					</c:forEach>
					<input type="hidden" value="${sessionScope.searchTypeE }" name="searchTypeE" id="searchTypeE">
					<input type="hidden" value="${sessionScope.searchTypeC }" name="searchTypeE" id="searchTypeC">
				</p>
			</td>
		</tr>
	</table>
	</form>
	
	
	<div class="bodyDiv">

<table width="100%">
	<tr>
		<td width="" valign="top">
			搜索时间:<font color="red">${(e-s)/1000.0 }</font>s
			<hr color="#6699CC"/>
			<!-- 搜索结果显示 -->
			<c:choose>
				<c:when test="${fn:length(list)==0}">
					<font size="7" color="red">查询结果为空！！！</font>
				</c:when>
				<c:otherwise>
					<table width="100%">
					<% 
					if(list!=null)
					for(int i=0;i<list.size();i++)
					{
						TextItem ttm = new TextItem();
						ttm = list.get(i);
						
						if(i%2==0){
					%>
					<tr>
					<%}else{ %>
					<tr bgcolor="#eeeeee">
					<%} %>
						<td>
							<strong>
								<a href="<%=path %>/textItemShow?fid=<%=ttm.getTqr().getId() %>">
								<%=ttm.getTqr().getOriginalFileName() %>
								</a>
							</strong>
							<p>
								<%=ttm.getTqr().getSnippet() %>
							</p>
							<span style="font-size: 12px;color: #55555;">
								Owner:<%=ttm.getTbf().getAuthor()%>
								Uplaod Time:<%=ttm.getTbf().getStrDate() %>
								Size:<%=ttm.getTbf().getFileSize() %>
								<%
								int[] nn = {2,3,5,6};
								for(int k=0;k<nn.length;k++){
								if(
									(!ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])).equals(""))
									&&
									(!ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])).equals("nothing"))){%>
								<%=ttm.getTsf().getSingleItem(nn[k]) %>:<%=ttm.getTsf().get(ttm.getTsf().getSingleItem(nn[k])) %>
								<%}} %>
								
							</span>
						</td>
					</tr>
					<%} %>
					<table width="100%">
						<tr>
							<td width="100%" align="center">
								<c:set value="1" var="abc"></c:set>
								<c:choose>
									<c:when test="${nowpage==1}">
										<font color="#888888">第一页  上一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/TextSearch?id=1">第一页</a>
										<a href="<%=path %>/TextSearch?id=${nowpage-1 }">上一页</a>
									</c:otherwise>
								</c:choose>
								<c:forEach begin="1" end="${sessionScope.totPage}" var="i">
									<c:choose>
										<c:when test="${nowpage==i}">
											<font color="red">${i }</font>
										</c:when>
										<c:otherwise>
											<a href="<%=path %>/TextSearch?id=${i }">${i }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${nowpage==sessionScope.totPage}">
										<font color="#888888">下一页  最后一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/TextSearch?id=${nowpage+1 }">下一页</a>
										<a href="<%=path %>/TextSearch?id=${sessionScope.totPage }">最后一页</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		
</table>	
	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>