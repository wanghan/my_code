<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
 
<script type="text/javascript" src="<%=path %>/js/file_addform2.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxHTTPObject.js"></script>

<link href="<%=path %>/test/default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=path %>/js/json.js"></script>

<script type="text/javascript" src="<%=path %>/test/test/swfupload.js"></script>
<script type="text/javascript" src="<%=path %>/test/test/swfupload.swfobject.js"></script>
<script type="text/javascript" src="<%=path %>/test/test/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=path %>/test/test/fileprogress.js"></script>
<script type="text/javascript" src="<%=path %>/test/test/handlers.js"></script>
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
	
  <%
    double perMaxSize = 150;//单个文件允许的max大小
    String sizeUnit = "MB";//perMaxSize数据对应的单位
    
    String ext = CF_TYPE.processFix(CF_TYPE.fileFix[n]);
    
    //String ext = "*.jpg;*.jpeg;*.txt";//允许上传的文件类型	//图像
    //String audioExt = "*.mp3;*.wav;*.txt";	//音频
    //String videoExt = "*.avi;*.jpg;*.txt";	//视频
    //String textExt = "*.txt;*.doc;*.docx;*.html;*.ppt;*.xls";	//文本
    
    //文件上传提交的目标页面
	StringBuffer uploadUrl = new StringBuffer("http://");
	uploadUrl.append(request.getHeader("Host"));
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/UploadServletCHBatch");
%>

<script type="text/javascript">
var swfu;

SWFUpload.onload = function () {
	var settings = {
		flash_url : "<%=path %>/test/test/swfupload.swf",
		upload_url: "<%=uploadUrl.toString()%>",
		post_params: {
			"user_id" : "stephen830",
			"pass_id" : "123456"
		},
		file_size_limit : "<%=perMaxSize%> <%=sizeUnit%>",
		file_types : "<%=ext%>",
		file_types_description : "<%=ext%>",
		file_upload_limit : 100,
		file_queue_limit : 0,
		custom_settings : {
			progressTarget : "fsUploadProgress",
			cancelButtonId : "btnCancel",
			uploadButtonId : "btnUpload",
			myFileListTarget : "idFileList"
		},
		debug: false,
		auto_upload:false,

		// Button Settings
		button_image_url : "<%=path %>/test/test/xzwj.png",	// Relative to the SWF file
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 110,
		button_height: 30,

		// The event handler functions are defined in handlers.js
		swfupload_loaded_handler : swfUploadLoaded,
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete,	// Queue plugin event
		
		// SWFObject settings
		minimum_flash_version : "9.0.28",
		swfupload_pre_load_handler : swfUploadPreLoad,
		swfupload_load_failed_handler : swfUploadLoadFailed
	};

	swfu = new SWFUpload(settings);
}

</script>

<style rel="stylesheet" type="text/css" >
#idFileList tr th{
	font-family: "宋体";
	font-size: 12px;
	color: #ffffff;
	
	background-color: #222222;
}
#idFileList tr td{
	font-family: "宋体";
	font-size: 12px;
}
</style>


  <body onload="file_addform_init('<%=path %>')">
<!-- 添加文件 -->
	<div style="height: ;width: 700px;">
		<form action="<%=uploadUrl.toString()%>" id="" method="post">
		<table class="tab_add_file" width="100%" height="" border="0">
			<tr height="100">
				<td colspan="4" align="center">
					<font size="5" color="red">${filetyc[n] }</font>
					<font size="5">文件添加到</font>
					<font size="5" color="red">${ftc[n][m]}</font>
					<font size="5">子类</font>
					<hr color="#dddddd">
				</td>
			</tr>
			<tr>
				<td>
					提交者：
				</td>
				<td>
					<input type="text" name="cf_u_txtv" id="cf_u_txtv" readonly="readonly" 
						style="color: #888888" 
						value="${sessionScope.username }"><font color="red" size="2">不可编辑项</font>
				</td>
			</tr>
			<tr height="">
				<td width="100" valign="top">
					${filetyc[n] }文件：<br>
					<c:if test="${filetye[n] ne 'text'}">
					语义文件：<br>
					</c:if>
					<c:if test="${filetye[n] eq 'video'}">
					关键帧：<br>
					镜头信息文件：<br>
					</c:if>
				</td>
				<td width="" style="font-size: 12px;" valign="top">
					<span id="spanButtonPlaceholder"></span>
					<table id="idFileList" class="uploadFileList" width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr class="uploadTitle">
							<th>文件名</th>
							<th>文件大小</th>
							<th width=100px>状态</th>
							<th width=35px>&nbsp;</th>
						</tr>
					</table>
					
					<font color="#888888">提交文件格式只能为：[<%=ext %>]</font>
					<font size="2" color="#888888">
					等待上传 <span id="idFileListCount">0</span> 个 ，上传完成 
					<span id="idFileListSuccessUploadCount">0</span> 个
					</font>
					
					<hr>
					<div id="divSWFUploadUI" style="visibility: hidden;"></div>
					<noscript style="display: block; margin: 10px 25px; padding: 10px 15px;">
						很抱歉，相片上传界面无法载入，请将浏览器设置成支持JavaScript。
					</noscript>
					<div id="divLoadingContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
						相片上传界面正在载入，请稍后...
					</div>
					<div id="divLongLoading" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
						相片上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。
					</div>
					<div id="divAlternateContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
						很抱歉，相片上传界面无法载入，请安装或者升级您的Flash插件。
						请访问： <a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" target="_blank">Adobe网站</a> 获取最新的Flash插件。
					</div>
					
			<input id="btnUpload" type="button" value="上传文件" class="btn" />
			<input id="btnCancel" type="button" value="取消全部上传" disabled="disabled" class="btn" />
			<input id="file_addform_add_submit" type="button" value="提交并保存当前更新" disabled="disabled" class="btn" onclick="addSubmit()"/>
		</table>
		</form>
		<form action="" id="file_addform_form" name="file_addform_form" method="post">
		<input type="hidden" name="cf_u_txtv" id="cf_u_txtv" value="${sessionScope.username }">
		<input type="hidden" name="cfile_type" value="${filetye[n]  }">
		<input type="hidden" name="cfile_subtype" value="${fte[n][m] }">
		</form>
	</div>
  </body>
</html>
