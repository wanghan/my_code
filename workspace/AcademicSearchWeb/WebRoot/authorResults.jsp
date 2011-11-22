<%@ page language="java" import="java.util.*,hibernate.*,java.io.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=path %>/css/bodyCss.css" rel="stylesheet" type="text/css"/>	
<link href="<%=path %>/css/hyperlink.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css"/>
<title>Author Search Result</title>
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
	ArrayList<DbAuthor> list = (ArrayList<DbAuthor>)request.getSession().getAttribute("author_list");
	request.setAttribute("author_list",list);
%>
<body>
	<div style="border: solid 1px #6fbee7; margin-bottom: 20px; float: right; width : 30%";>
	<table width="100%">
	<tr>
		<td width="" valign="top">
			Author Search Time:<font color="red">${(author_e-author_s)/1000.0 }</font>s
			<hr color="#6699CC"/>
			<!-- 搜索结果显示 -->
			<c:choose>
				<c:when test="${fn:length(list)==0}">
					<font size="4" color="red">Sorry, no searched authors for <%=keyword %></font>
				</c:when>
				<c:otherwise>
					<table width="100%">
					<div class="author_info">
					<span class="name">Authors: </span>
					<ul class="authorlist">
					<table>
						<%
						DbAuthor [] authors=(DbAuthor [])list.toArray(new DbAuthor[0]);
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
						<table><tbody>
						<tr><td>
						<a href="<%=path %>/authorItemShow?fid=<%=authors[i].getId()%>"><%=authors[i].getName() %></a>
						</td></tr>
						<%if(authors[i].getAffiliation()!=null){ %>
						<tr><td>
						<%=authors[i].getAffiliation() %>
						</td></tr>
						<%} %>
						</tbody></table>
						</td>
						</tr>
						
						
						<%} %>
					</table>
					</ul>
					
				</div>
					<table width="100%">
						<tr>
							<td width="100%" align="center">
								<c:set value="1" var="abc"></c:set>
								<c:choose>
									<c:when test="${sessionScope.author_nowpage==1}">
										<font color="#888888">第一页  上一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/SearchResult?author_id=1">第一页</a>
										<a href="<%=path %>/SearchResult?author_id=${sessionScope.author_nowpage-1 }">上一页</a>
									</c:otherwise>
								</c:choose>
								<c:forEach begin="1" end="${sessionScope.author_totPage}" var="i">
									<c:choose>
										<c:when test="${sessionScope.author_nowpage==i}">
											<font color="red">${i }</font>
										</c:when>
										<c:otherwise>
											<a href="<%=path %>/SearchResult?author_id=${i }">${i }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${sessionScope.author_nowpage==sessionScope.author_totPage}">
										<font color="#888888">下一页  最后一页</font>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/SearchResult?author_id=${sessionScope.author_nowpage+1 }">下一页</a>
										<a href="<%=path %>/SearchResult?author_id=${sessionScope.author_totPage }">最后一页</a>
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