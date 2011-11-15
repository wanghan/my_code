<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
  <script type="text/javascript" src="<%=path %>/js/console_file_delete.js"></script>
  <script type="text/javascript" src="<%=path %>/js/calendar.js"></script>
  <style type="text/css">
  .ddiv a{
  	font-size: 12px;
  }
  .tab_add_file tr td{
  	font-size:12px;
  }
  
  </style>
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
		//bf、sf中英文对照
		request.setAttribute("bfe",CF_TYPE.bfe);
		request.setAttribute("bfc",CF_TYPE.bfc);
		request.setAttribute("sfe",CF_TYPE.sfe);
		request.setAttribute("sfc",CF_TYPE.sfc);
		
		String[] cmid = CF_TYPE.cmid;
		
		int n = 0;
		int m = 0;
		try{
			n = Integer.parseInt(request.getParameter("n"));
			m = Integer.parseInt(request.getParameter("m"));
		}catch(Exception e){}
		
		request.setAttribute("n",n);
		request.setAttribute("m",m);
	%>
  <body>
  	<div style="height: ;">
	<!-- 删除文件 -->
		<form action="<%=path %>/ItemsFileSelect" method="post">
		<input type="hidden" name="cfile_type" value="${filetye[n] }">
		<input type="hidden" name="cfile_subtype" value="${fte[n][m] }">
		<table class="tab_add_file" width="100%" height="" border="0">
			<tr height="70">
				<td colspan="4" align="center">
					<font size="5">删除</font>
					<font size="5" color="red">${filetyc[n] }>>${ftc[n][m]}</font>
					<font size="5">中的文件</font>
				</td>
			</tr>
			<tr height="">
				<td width="5%">
				</td>
				<td width="90%" height="1" valign="top" colspan="2" style="border: 1px #eeeeee solid;">
				
					<table id="tab_add_bf" width="100%" border="0" class="bgc">
						<tr bgcolor="#eeeeee">
							<td width="50%" align="right">
								选择添加<font color="red">基本</font>检索方式：
 							<select id="select_search_bf" onchange="search_add_bf(this,'tab_add_bf')">
 								<option value="-1">请选择...</option>
 								<c:forEach items="${bfe[n]}" var="e" varStatus="index">
 								<option value="${e }">${index.index}、${bfc[n][index.index] }</option>
 								</c:forEach>
 							</select>
							</td>
							<td align="center" width="60%">
								填写检索条件
							</td>
						</tr>
					</table>
				
				</td>
				<td width="5%"></td>
			</tr>
			<tr>
				<td>
				</td>
				<td width="" height="" valign="top" colspan="2" style="border: 1px #eeeeee solid;">
					<table id="tab_add_sf" width="100%" border="0" class="bgd">
						<tr bgcolor="#eeeeee">
							<td align="right" width="50%">
								选择添加<font color="red">语义</font>检索方式：
 							<select id="select_search_sf" onchange="search_add_bf(this,'tab_add_sf')">
 								<option value="-1">请选择...</option>
 								<c:forEach items="${sfe[n]}" var="e" varStatus="index">
 								<option value="${e }">${index.index}、${sfc[n][index.index] }</option>
 								</c:forEach>
 							</select>
							</td>
							<td align="center" width="60%">
								填写检索条件
							</td>
						</tr>
					</table>
				
				</td>
				<td>
				</td>
			</tr>
			<tr height="40">
				<td colspan="4" align="center">
					<input type="submit" value=" 确定 ">
					<input type="reset" value=" 清空 " onclick="window.location.reload()">
				</td>
			</tr>
		</table>
		
		</form>
		   		
   </div>
  </body>
</html>
