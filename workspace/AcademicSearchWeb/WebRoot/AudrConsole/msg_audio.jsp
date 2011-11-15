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
    <!-- 视频文件、关键帧 -->
    <table>
    	<tr>
    		<td colspan="3">
    			<input type="hidden" name="cfile_type" id="cfile_type" value="${sessionScope.file_type }">
				<input type="hidden" name="cfile_subtype" id="cfile_subtype" value="${sessionScope.file_subtype }">
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3" bgcolor="#eeeeee">基本属性信息</td>
    	</tr>
    	<tr height="30">
			<td valign="top">
				歌词：
			</td>
			<td>
				<textarea rows="5" cols="37" disabled="disabled">${audio.lyrics}</textarea>
			</td>
			
		</tr>
		<tr height="30">
			<td>
				歌曲名：
			</td>
			<td>
				${audio.audioName}
			</td>
		</tr>
		<tr height="30">
			<td>
				歌手：
			</td>
			<td align="left">
				${audio.singer}
			</td>
		</tr>
		<tr height="30">
			<td>
				专辑：
			</td>
			<td>
				${audio.album }
			</td>
		</tr>
		<tr height="30">
			<td>
				年代：
			</td>
			<td>
				${audio.years }
			</td>
		</tr>
		<tr height="30">
			<td>
				地区：
			</td>
			<td>${audio.district }
			</td>
		</tr>
		<tr height="30">
			<td>
				主题：
			</td>
			<td>${audio.theme }
			</td>
		</tr>
		<tr height="30">
			<td>
				情感：
			</td>
			<td>${audio.emotion }
			</td>
		</tr>
		<tr height="30">
			<td>
				表现意境：
			</td>
			<td>${audio.expression }
			</td>
		</tr>
		<tr height="30">
			<td>
				创作者：
			</td>
			<td>${audio.composer }
			</td>
		</tr>
		<tr height="30">
			<td>
				语言：
			</td>
			<td>${audio.language }
			</td>
		</tr>
		<tr height="30">
			<td>
				风格：
			</td>
			<td>${audio.style }
			</td>
		</tr>
		<tr height="30">
			<td>
				关键字：
			</td>
			<td>${audio.keyword }
			</td>
		</tr>
		
		<tr height="30">
			<td>
				文件后缀：
			</td>
			<td>${audio.fileSuffix }
			</td>
		</tr>
		<tr height="30">
			<td>
				热门程度：
			</td>
			<td>${audio.popularity }
			</td>
		</tr>
		<tr height="30">
			<td>
				比特率：
			</td>
			<td>${audio.bitrate }
			</td>
		</tr>
		<tr height="30">
			<td>
				整体音高：
			</td>
			<td>${audio.averPitch }
			</td>
		</tr>
		<tr height="30">
			<td>
				节奏：
			</td>
			<td>${audio.tempo }
			</td>
		</tr>
		<tr height="30">
			<td>
				乐器：
			</td>
			<td>${audio.instrument }
			</td>
		</tr>
		<tr height="30">
			<td>
				音色：
			</td>
			<td>${audio.timbre }
			</td>
		</tr>
		<tr height="30">
			<td>
				人声：
			</td>
			<td>${audio.voice }
			</td>
		</tr>
    	<tr>
    		<td colspan="3">
    			<hr>
				
			</td>
    	</tr>
    </table>
  </body>
</html>
