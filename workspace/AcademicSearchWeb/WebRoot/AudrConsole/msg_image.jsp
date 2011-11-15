<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detail_audio.jsp' starting page</title>
    
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
  <table width="650">
  	<tr>
  		<td width="370">
  			<div style="width: 100%">
	  		<table width="100%">
	  			<tr>
	  				<td colspan="2" align="center" valign="middle">
	  					<img id="" border="10" src="<%=path %>${bigPic }" 
							onload="javascript:if(this.width>370)  this.width=370;">
	  				</td>
	  			</tr>
	  			<tr>
  					<td colspan="2">
  						<div class="bgc" style="border-bottom: 2px solid black;">基本属性信息</div>
  					</td>
  				</tr>
  				<tr>
  					<td width="100">
  						ID:
  					</td>
  					<td width="270">
  						<font color="red">${id}</font>
  					</td>
  				</tr>
  				<tr>
  					<td>
  						提交者：
  					</td>
  					<td>
  						${bf.fileauthor }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						上传时间：
  					</td>
  					<td>
  						${bf.filedate }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						文件大小：
  					</td>
  					<td>
  						${bf.filesize }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						文件名称：
  					</td>
  					<td>
  						${bf.filename }
  					</td>
  				</tr>
  				<tr>
  					<td valign="top">
  						文件路径：
  					</td>
  					<td>
  						<div style="width: 200px;height: 100;overflow: hidden">${bf.filepath }</div>
  					</td>
  				</tr>
	  		</table>
		</div>
  		</td>
  		<td width="270px" valign="top">
  		<form action="">
  			<table width="100%">
  				
  				<tr>
  					<td colspan="2">
  						<div class="bgc" style="border-bottom: 2px solid black;">基本语义信息</div>
  					</td>
  				</tr>
  				<tr>
  					<td width="100">
  						标题:
  					</td>
  					<td width="">
  						${sf.title }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						主题:
  					</td>
  					<td>
  						${sf.subject }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						关键词:
  					</td>
  					<td>
  						${sf.keywords }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						拍摄者:
  					</td>
  					<td>
  						${sf.author }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						文件名:
  					</td>
  					<td>
  						${sf.filename }
  					</td>
  				</tr>
  				
  				<tr>
  					<td>
  						拍摄地点:
  					</td>
  					<td>
  						${sf.place }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						拍摄日期时间:
  					</td>
  					<td>
  						${sf.date_time }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像质量:
  					</td>
  					<td>
  						${sf.quality }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像高度:
  					</td>
  					<td>
  						${sf.height }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像宽度:
  					</td>
  					<td>
  						${sf.width }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像颜色:
  					</td>
  					<td>
  						${sf.color }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像种类:
  					</td>
  					<td>
  						${sf.category }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						图像格式:
  					</td>
  					<td>
  						${sf.format }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						用户描述:
  					</td>
  					<td>
  						${sf.user_description }
  					</td>
  				</tr>
  				<tr>
  					<td>
  						用户评论:
  					</td>
  					<td>
  						${sf.user_comment }
  					</td>
  				</tr>
  			</table>
  			</form>
  		</td>
  	</tr>
  	
  </table>
  </body>
</html>
