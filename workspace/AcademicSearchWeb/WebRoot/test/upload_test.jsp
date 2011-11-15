<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
  </head>
  <script type="text/javascript" src="<%=path%>/js/jquery.uploadify.js"></script>
  <script type="text/javascript" src="<%=path %>/js/calendar.js"></script>
  <link href="<%=path%>/css/uploadify.css" rel="stylesheet"
		type="text/css" />
  
  <style type="text/css">
  .ddiv a{
  	font-size: 12px;
  }
  .tab_add_file tr td{
  	font-size:12px;
  }
  </style>
  
  <script type="text/javascript">
  function challs_flash_update(){ //Flash 初始化函数
  	var a={};
	//定义变量为Object 类型

	a.title = "上传文件"; //设置组件头部名称
	
	a.FormName = "Filedata";
	//设置Form表单的文本域的Name属性
	
	a.url="<%=path%>/UploadServletCH"; 
	//设置服务器接收代码文件
	
	a.parameter="bs=tyi&id=50"; 
	//设置提交参数，以GET形式提交
	
	a.typefile=["图像文件 (*.png,*.jpg,*jpeg)","*.png;*.jpg;*.jpeg;",
		"文本文件 (*.txt)","*.txt;",
		"视频文件 (*.avi,*.wmv)","*.avi;*.wmv;"];
	//设置可以上传文件 数组类型
	//"Images (*.gif,*.png,*.jpg)"为用户选择要上载的文件时可以看到的描述字符串,
	//"*.gif;*.png;*.jpg"为文件扩展名列表，其中列出用户选择要上载的文件时可以看到的 Windows 文件格式，以分号相隔
	//2个为一组，可以设置多组文件类型
	
	a.UpSize=0;
	//可限制传输文件总容量，0或负数为不限制，单位MB
	
	a.fileNum=0;
	//可限制待传文件的数量，0或负数为不限制
	
	a.size=1000;
	//上传单个文件限制大小，单位MB，可以填写小数类型
	
	a.FormID=['folder'];
	//设置每次上传时将注册了ID的表单数据以POST形式发送到服务器
	//需要设置的FORM表单中checkbox,text,textarea,radio,select项目的ID值,radio组只需要一个设置ID即可
	//参数为数组类型，注意使用此参数必须有 challs_flash_FormData() 函数支持
	
	a.autoClose=1;
	//上传完成条目，将自动删除已完成的条目，值为延迟时间，以秒为单位，当值为 -1 时不会自动关闭，注意：当参数CompleteClose为false时无效
	
	a.CompleteClose=true;
	//设置为true时，上传完成的条目，将也可以取消删除条目，这样参数 UpSize 将失效, 默认为false
	
	a.repeatFile=true;
	//设置为true时，可以过滤用户已经选择的重复文件，否则可以让用户多次选择上传同一个文件，默认为false
	
	a.returnServer=true;
	//设置为true时，组件必须等到服务器有反馈值了才会进行下一个步骤，否则不会等待服务器返回值，直接进行下一步骤，默认为false
	
	a.MD5File = 1;
	//设置MD5文件签名模式，参数如下
	//0为关闭MD5计算签名
	//1为直接计算MD5签名后上传
	//2为计算签名，将签名提交服务器验证，在根据服务器反馈来执行上传或不上传
	//3为先提交文件基本信息，根据服务器反馈，执行MD5签名计算或直接上传，如果是要进行MD5计算，计算后，提交计算结果，在根据服务器反馈，来执行是否上传或不上传
	
	a.loadFileOrder=true;
	//选择的文件加载文件列表顺序，TRUE = 正序加载，FALSE = 倒序加载
	
	a.mixFileNum=0;
	//至少选择的文件数量，设置这个将限制文件列表最少正常数量（包括等待上传和已经上传）为设置的数量，才能点击上传，0为不限制
	
	return a ;
	//返回Object
  }
  
  /////////////////////////////
  
  function challs_flash_onComplete(a){ //每次上传完成调用的函数，并传入一个Object类型变量，包括刚上传文件的大小，名称，上传所用时间,文件类型
	var name=a.fileName; //获取上传文件名
	var size=a.fileSize; //获取上传文件大小，单位字节
	var time=a.updateTime; //获取上传所用时间 单位毫秒
	var type=a.fileType; //获取文件类型，在 Windows 上，此属性是文件扩展名。 在 Macintosh 上，此属性是由四个字符组成的文件类型
	document.getElementById('show').innerHTML+=name+' --- '+size+'字节 ----文件类型：'+type+'--- 用时 '+(time/1000)+'秒<br><br>'
}

function challs_flash_onCompleteData(a){ //获取服务器反馈信息事件
	document.getElementById('show').innerHTML+='<font color="#ff0000">服务器端反馈信息：</font><br />'+a+'<br />';	
}
function challs_flash_onStart(a){ //开始一个新的文件上传时事件,并传入一个Object类型变量，包括刚上传文件的大小，名称，类型
	var name=a.fileName; //获取上传文件名
	var size=a.fileSize; //获取上传文件大小，单位字节
	var type=a.fileType; //获取文件类型，在 Windows 上，此属性是文件扩展名。 在 Macintosh 上，此属性是由四个字符组成的文件类型
	document.getElementById('show').innerHTML+=name+'开始上传！<br />';
}

function challs_flash_onCompleteAll(a){ //上传文件列表全部上传完毕事件,参数 a 数值类型，返回上传失败的数量
	document.getElementById('show').innerHTML+='<font color="#ff0000">所有文件上传完毕，</font>上传失败'+a+'个！<br />';
	//window.location.href='http://www.access2008.cn/update'; //传输完成后，跳转页面
}

function challs_flash_onError(a){ //上传文件发生错误事件，并传入一个Object类型变量，包括错误文件的大小，名称，类型
	var err=a.textErr; //错误信息
	var name=a.fileName; //获取上传文件名
	var size=a.fileSize; //获取上传文件大小，单位字节
	var type=a.fileType; //获取文件类型，在 Windows 上，此属性是文件扩展名。 在 Macintosh 上，此属性是由四个字符组成的文件类型
	document.getElementById('show').innerHTML+='<font color="#ff0000">'+name+' - '+err+'</font><br />';
}

function challs_flash_FormData(a){ // 使用FormID参数时必要函数
	try{
		var value = '';
		var id=document.getElementById(a);
		if(id.type == 'radio'){
			var name = document.getElementsByName(id.name);
			for(var i = 0;i<name.length;i++){
				if(name[i].checked){
					value = name[i].value;
				}
			}
		}else if(id.type == 'checkbox'){
			if(id.checked){
				value = id.value;
			}
		}else{
			value = id.value;
		}
		return value;
	 }catch(e){
		return '';
	 }
}

var isMSIE = (navigator.appName == "Microsoft Internet Explorer");   
function thisMovie(movieName){   
  if(isMSIE){   
  	return window[movieName];   
  }else{
  	return document[movieName];   
  }   
}
  </script>
	
  <body>
  <jsp:include page="/common/header.jsp"></jsp:include>
选择存储文件夹：
<select id="folder">
	    <option value="/temp">temp</option>
</select>
<br>
<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" width="408" height="320" id="update" align="middle">
	<param name="allowFullScreen" value="false" />
    <param name="allowScriptAccess" value="always" />
	<param name="movie" value="<%=path %>/test/test/update.swf" />
    <param name="quality" value="high" />
    <param name="bgcolor" value="#ffffff" />
    <embed src="<%=path %>/test/test/update.swf" quality="high" bgcolor="#ffffff" width="408" height="320" name="update" align="middle" allowScriptAccess="always" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
</object>
<div id="show" style="margin-top:20px; width:500px; text-align:left;"></div>
  </body>
</html>
