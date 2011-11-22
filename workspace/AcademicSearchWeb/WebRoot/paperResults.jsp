<%@ page language="java" import="java.util.*,hibernate.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css"/>
<title>Paper Search Result</title>
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
<% 
	String keyword=(String)request.getSession().getAttribute("searchText");
	ArrayList<DbPaper> list = (ArrayList<DbPaper>)request.getSession().getAttribute("list");
	request.setAttribute("list",list);
%>
<body>
	<div style="border: solid 1px #6fbee7; margin-bottom: 20px; float: left; width : 65%;">
    <table width="100%">
	<tr>
		<td width="" valign="top">
			Paper Search Time:<font color="red">${(e-s)/1000.0 }</font>s
			<hr color="#6699CC"/>
			<!-- 搜索结果显示 -->
			<c:choose>
				<c:when test="${fn:length(list)==0}">
					<font size="4" color="red">Sorry, no searched papers for <%=keyword %></font>
				</c:when>
				<c:otherwise>
					<table width="100%">
					<% 
					if(list!=null)
					for(int i=0;i<list.size();i++)
					{
						DbPaper ttm = new DbPaper();
						ttm = list.get(i);
					%>
					<tr>
						<td>
						<div id="publist">
							<strong>
								<a class="url" href="<%=path %>/PaperItemShow?fid=<%=ttm.getId() %>">
								<%=ttm.getTitle() %>
								</a>

							</strong>
							<br/>
							<li>Authors: 
							<%
							DbAuthor []authors=(DbAuthor [])ttm.getDbAuthors().toArray(new DbAuthor[0]);
							for(int j=0;j<authors.length-1;j++)
							{
							%>
								<a href="<%=path %>/authorItemShow?fid=<%=authors[j].getId()%>"><%=authors[j].getName()%></a>, 
							<%}%>
							<a href="<%=path %>/authorItemShow?fid=<%=authors[authors.length-1].getId()%>"><%=authors[authors.length-1].getName()%></a> 
							</li>
							<li>Proceedings:   <%="\t\t"+ttm.getDbConference().getName() %><br/></li>
							<li>Published year:    <%="\t\t"+String.valueOf(1900+ttm.getDbConference().getDate().getYear())%></li>

						</div>
						</td>
					</tr>
					<%} %>
					<table width="100%">
						<tr>
							<td width="100%" align="center">
								<c:set value="1" var="abc"></c:set>
								<c:choose>
									<c:when test="${sessionScope.nowpage==1}">
										<font color="#888888">第一页  上一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/SearchResult?id=1">第一页</a>
										<a href="<%=path %>/SearchResult?id=${sessionScope.nowpage-1 }">上一页</a>
									</c:otherwise>
								</c:choose>
								<c:forEach begin="1" end="${sessionScope.totPage}" var="i">
									<c:choose>
										<c:when test="${sessionScope.nowpage==i}">
											<font color="red">${i }</font>
										</c:when>
										<c:otherwise>
											<a href="<%=path %>/SearchResult?id=${i }">${i }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${sessionScope.nowpage==sessionScope.totPage}">
										<font color="#888888">下一页  最后一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/SearchResult?id=${sessionScope.nowpage+1 }">下一页</a>
										<a href="<%=path %>/SearchResult?id=${sessionScope.totPage }">最后一页</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</table>
		</table>	
	</div>
</body>
</html>