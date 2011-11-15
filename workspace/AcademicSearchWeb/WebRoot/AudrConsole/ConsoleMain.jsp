<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>非结构化数据库控制台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<style type="text/css">
  .ddiv a{
  	font-size: 12px;
  }
  .tab_add_file tr td{
  	font-size:12px;
  }
  
</style>
  </head>
  <% 
  String url=request.getScheme()+"://";   
  url+=request.getHeader("host");   
  url+=request.getRequestURI();   

  request.setAttribute("url",url);
  %>
  <body>
    <jsp:include page="/AudrConsole/console_header.jsp"></jsp:include>
    <jsp:useBean id="CF_TYPE" class="Common.FileType"></jsp:useBean>
	<% 
		//数据库主类型
		request.setAttribute("filetye",CF_TYPE.filetye);
		request.setAttribute("filetyc",CF_TYPE.filetyc);
		//子类型
		request.setAttribute("fte",CF_TYPE.fte);
		request.setAttribute("ftc",CF_TYPE.ftc);
		//更新类型
		request.setAttribute("dbtye",CF_TYPE.dbtye);
		request.setAttribute("dbtyc",CF_TYPE.dbtyc);
	%>
	<%
		String[] ac = CF_TYPE.action;
		String[] us = CF_TYPE.urls;
		request.setAttribute("ac",ac);
		request.setAttribute("us",us);
	%>
    <% 
    	String[] cmid = CF_TYPE.cmid;
		String[] cmv  = new String[cmid.length];
		
		for(int i=0;i<cmid.length;i++)
		{
			String tmp = request.getParameter(cmid[i]);
			if(tmp==null)
				cmv[i] = "";
			else
				cmv[i] = tmp;
				
			session.setAttribute(cmid[i],cmv[i]);
		}
		
		request.setAttribute("cmid",cmid);
		request.setAttribute("cmv",cmv);
	%>
	<center>
	
	<div class="bodyDiv" style="border: 1px #07A1EE solid;height: ;background-color: ">
		<table width="100%" height="">
			<tr>
				<!-- 菜单选择框 -->
				<td width="170" valign="top" height="450">
					<c:if test="${cmv[0] eq 'file'}">
						<div style="width: 100%;height: 30px;line-height: 30px;background-color: #eeeeee;color: #07A1EE;font-size: 12px;">
							选择文件类型：
						</div>
						<div class="ddiv" style="height: 70px;">
							<c:forEach items="${filetye}" var="ft" varStatus="index">
								<c:choose>
									<c:when test="${cmv[1] eq filetye[index.index]}">
										<font color="#07A1EE">${filetyc[index.index] }</font>
									</c:when>
									<c:otherwise>
										<a href="${url }?${cmid[0] }=file&${cmid[1] }=${filetye[index.index] }">
											${filetyc[index.index] }</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						
						<!-- =============================== -->
						<c:set var="n" value="0"></c:set>
						<c:set var="m" value="0"></c:set>
						<c:if test="${cmv[1] ne ''}">
							<c:forEach items="${filetye}" var="ft" varStatus="index">
								<c:if test="${ft eq cmv[1]}">
									<c:set var="n" value="${index.index}"></c:set>
									<div style="width: 100%;height: 30px;line-height: 30px;background-color: #eeeeee;color: #07A1EE;font-size: 12px;">
										选择${filetyc[index.index] }类型：
									</div>
									<div class="ddiv" style="height: 170px;">
										<c:forEach items="${fte[index.index]}" var="sub" varStatus="ind">
											<c:choose>
												<c:when test="${cmv[2] ne '' and cmv[2] eq fte[index.index][ind.index]   }">
													<c:set var="m" value="${ind.index}"></c:set>
													<font color="#07A1EE">${ftc[index.index][ind.index]}</font>
												</c:when>
												<c:otherwise>
													<a href="${url }?${cmid[0] }=file&${cmid[1] }=${filetye[index.index] }&${cmid[2] }=${fte[index.index][ind.index] }">
														${ftc[index.index][ind.index]}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									 </div>
								</c:if>
							</c:forEach>
						</c:if>
						<!--  -->
						<c:set var="k" value="${0}"></c:set>
						<c:if test="${cmv[2] ne ''}">
							<c:forEach items="${fte[n]}" var="ft" varStatus="index">
								<c:if test="${fte[n][index.index] eq cmv[2]}">
									<div style="width: 100%;height: 30px;line-height: 30px;background-color: #eeeeee;color: #07A1EE;font-size: 12px;">
										选择管理类型：
									</div>
									<div class="ddiv" style="height: 150px;">
									<c:forEach items="${dbtye}" var="d" varStatus="ind">
										<c:choose>
											<c:when test="${cmv[3] eq d}">
												<c:set var="k" value="${ind.index}"></c:set>
												<font color="#07A1EE">${dbtyc[ind.index] }</font>
											</c:when>
											<c:otherwise>
												<a href="${url }?${cmid[0] }=file&${cmid[1] }=${filetye[n] }&${cmid[2] }=${fte[n][m] }&${cmid[3] }=${dbtye[ind.index] }&${cmid[4]}=${ac[ind.index] }">
													${dbtyc[ind.index] }</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									</div>
								</c:if>
							</c:forEach>
						</c:if>				
					</c:if>
				</td>
				<!-- 效果展示框 -->
				<c:choose>
				<c:when test="${cmv[0] eq 'file'}">
					<td valign="top" style="border-left: 1px solid #07A1EE;">
						<div id="show_bar" style="background-color: #eeeeee;height: 20;width: 100%;line-height: 20px;
	   					font-size: 12px;color:#07A1EE; ">
							<c:choose>
							<c:when test="${cmv[0] ne '' and cmv[1] ne '' and cmv[2] ne '' and cmv[3] ne ''}">
								当前路径：文件管理 >> ${filetyc[n] } >> ${ftc[n][m] } >> ${dbtyc[k] }
							</c:when>
							<c:when test="${cmv[0] ne '' and cmv[1] ne '' and cmv[2] ne ''}">
								当前路径：文件管理 >> ${filetyc[n] } >> ${ftc[n][m] }
							</c:when>
							<c:when test="${cmv[0] ne '' and cmv[1] ne ''}">
								当前路径：文件管理 >> ${filetyc[n] }
							</c:when>
							<c:when test="${cmv[0] ne ''}">
								当前路径：文件管理
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
						</div>
						
						<!-- 条件完成，显示操作界面 -->
						
						<div style="height: ;overflow: auto;">
						<c:choose>
							<c:when test="${ac ne null}">
							<c:forEach items="${ac}" varStatus="index">
								<c:if test="${cmv[4] eq ac[index.index]}">
									<jsp:include page="${us[index.index]}">
										<jsp:param name="n" value="${n}"/>
										<jsp:param name="m" value="${m}"/>
									</jsp:include>
								</c:if>
							</c:forEach>
							</c:when>
						</c:choose>
						</div>
					<td>
				</c:when>
				<c:when test="${cmv[0] eq 'user'}">
					<td>
						<h1>user</h1>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<jsp:include page="wellcome.html"></jsp:include>
					</td>
				</c:otherwise>
				</c:choose>
			</tr>
		</table>
	</div>
	
    </center>
    <jsp:include page="/common/footer.jsp"></jsp:include>
  </body>
</html>
