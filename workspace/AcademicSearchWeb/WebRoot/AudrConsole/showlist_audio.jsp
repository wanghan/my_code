<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <script type="text/javascript" src="<%=path %>/js/ajaxHTTPObject.js"></script>
  <script type="text/javascript">
  function checkbox(mark,cnm)
  {
  	var field = document.getElementsByName(cnm);
	for(i = 0;i < field.length;i++)
	{
	   if(mark=="1")
	   {
	   	field[i].checked = true;
	   }else if(mark=="0")
	   {
	   	field[i].checked = false;
	   }
	   else if(mark=="-1")
	   {
	   	field[i].checked = !field[i].checked;
	   }
	}
  }
  
  var xmlHttp = null;
  //显示文件详细信息////////////////////////////////////
  function showmsg(sid,hid,imgid)
  {
  	var showobj = document.getElementById(sid);
  	var hidenobj = document.getElementById(hid);
  	var cfile_type = document.getElementById("cfile_type").value;
	var cfile_subtype = document.getElementById("cfile_subtype").value;
	
	if(cfile_type==""||cfile_subtype=="")
	{	
		alert("错误");
		return;
	}
	else
	{
		showobj.style.display = '';
	  	hidenobj.style.display = 'none';
	  	
	  	xmlHttp = getHTTPObject();
	  	if(xmlHttp==false)
	  	{alert("你的浏览器不支持详细信息查看，请更新浏览器，或者采用最高版本的浏览器");}
	  	else if(sid=="showmsg")
	  	{
	  		selectItemMsg(cfile_type,cfile_subtype,imgid);
	  	}
	}
  }
   function selectItemMsg(cfile_type,cfile_subtype,id){
	
	var url = "ItemFileSelect?cfile_type="+cfile_type+"&cfile_subtype="+cfile_subtype+"&id="+id;
  	xmlHttp.open("GET",url,true);
  	xmlHttp.onreadystatechange = handleHttpResponse;
  	xmlHttp.send(null);
  	
  }
  function handleHttpResponse()
  {
 	if(xmlHttp.readyState==4 && xmlHttp.status == 200)
 	{
 		var ret = xmlHttp.responseText; 
 		document.getElementById('showmsg_show').innerHTML = ret;
 		xmlHttp = null;
 	}
 	else
 	{
 		document.getElementById('showmsg_show').innerHTML = "<h1>正在加载....</h1>";
 	}
  }
  
  //显示文件详细信息////////////////////////////////////
  
  //删除文件详细信息////////////////////////////////////
  
  function analyseForm() // 分析form的元素
  {
	  var params = new Array();
	  var cf_type = "cfile_type";
	  	cf_type+="=";
	  	cf_type+=document.getElementById("cfile_type").value;
	  	params.push(cf_type);
	  var cf_subtype = "cfile_subtype";
	  	cf_subtype+="=";
	  	cf_subtype+=document.getElementById("cfile_subtype").value;
	  	params.push(cf_subtype);
	  var cbs = document.getElementsByName("iform");
	  var n = 0;
	  for(i=0;i<cbs.length;i++)
	  {
	  	if(cbs[i].checked)
	  		n++;
	  }
	  
	  if(n==0)
	  	return false;
	  else
	  {
		  for(i=0;i<cbs.length;i++)
		  {
		  	if(cbs[i].checked)
		  	{
		  		var param = "iform";
		  			param+="=";
		  			param+=cbs[i].value;
		  		params.push(param);
		  	}
		  }
		  return params.join("&");//返回的是一个数组
	  }
  }
  function deleteitem()
  {
  	
  	var formBody = analyseForm();//准备好要提交的值

  	if(formBody==false)
  		alert("请先选择");
  	else
  	{
	  	if(confirm("你真的要删除吗？"))
  		{
	  		document.getElementById("bt_sub_delete").disabled = true;
		  	var url='<%=path %>/ItemFileDelete';//服务器处理数据的地址
		  	
		  	xmlhttp = getHTTPObject();
		  	
		  	xmlhttp.open("post",url,true);
		  	xmlhttp.onreadystatechange=deltipmsg;//由getHello函数来处理返回信息
		  	//xmlhttp.setRequestHeader("cache-control","no-cache");
		  	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
		  	xmlhttp.send(formBody);//post方法须在send里发送
	  	}
	  }
  }
  function deltipmsg()
  {
	  if(xmlhttp.readyState==4)
	  {
	   if(xmlhttp.status==200)
	   {
	    var msg=xmlhttp.responseText;
	    alert(msg);
	    document.getElementById("bt_sub_delete").disabled = false;
	    xmlhttp = null;
	     
	     //重新查询
	    var cfile_type = document.getElementById("cfile_type").value;
	    var cfile_subtype = document.getElementById("cfile_subtype").value;
	    window.location.href = '<%=path %>/ItemsFileSelect?requery=yes&cfile_subtype='+cfile_subtype+'&cfile_type='+cfile_type;
	   }
  }
  }
  
  //==============================================
  function fy(url,v)
  {
  	window.location.href = "<%=path%>/ItemsFileSelect?off.set="+v;
  }
  </script>
  <body>
    
    <!-- 详细信息显示 -->
    <div id="showmsg" style="height: ;display: none;">
    	<div style="background-color: #ffffff;width: 100%;height: 20px;line-height: 20px;text-align: right;">
    		<a href="javascript:void 0;" onclick="showmsg('showlist','showmsg','')"><font color="#000000" size="2">返回列表</font></a>
    	</div>
    	<div id="showmsg_show" style="height:;line-height: 420px;display: ;">
    		
    	</div>
    </div>
    
    <!-- 文件列表 -->
    <div id="showlist" style="height: ;display: ; ">
    	<div style="background-color: #ffffff;width: 100%;height: 20px;line-height: 20px;text-align: right;">
    		 <a href="<%=path %>/AudrConsole/ConsoleMain.jsp?cm_to=file&cm_ftype=${sessionScope.file_type }&cm_sub_ftype=${sessionScope.file_subtype }&cm_contro=delete&action=deleteform"><font color="#000000" size="2">重新查询</font></a>
    	</div>
    <form action="#" id="iforms" method="post">
    <input type="hidden" name="cfile_type" id="cfile_type" value="${file_type }">
	<input type="hidden" name="cfile_subtype" id="cfile_subtype" value="${file_subtype }">
    	<table width="100%" height="" border="0">
    		<tr height="70">
    			<td colspan="4" align="center" valign="middle">
    				<h1>音频文件检索结果</h1>
    			</td>
    		</tr>
    		<tr>
    			<th width="150">
    				歌曲名称
    			</th>
    			<th width="100">
    				歌手
    			</th>
    			<th width="100">
    				专辑
    			</th>
    			<th width="70">
    				文件类型
    			</th>
    		</tr>
    		<c:choose>
    			<c:when test="${!empty fail || empty sessionScope.audioes}">
    				<tr><td colspan="5"><font size="3" color="red">${fail }</font></td></tr>
    			</c:when>
    			<c:when test="${sessionScope.audioes[0].audioName eq '无结果' }">
    				<tr><td colspan="5"><font size="3" color="red">查询结果为空</font></td></tr>
    			</c:when>
    			<c:otherwise>
    			<c:forEach items="${sessionScope.audioes}" begin="${sessionScope.start }" end="${sessionScope.end }" var="ars" varStatus="index">
    			<tr>
	    			<td>
		    				<input type="checkbox" name="iform" id="iform" value="${ars.ID }"/>
		    				<a href="javascript:void 0;" onclick="showmsg('showmsg','showlist','${ars.ID }')" title="点击查看详细信息">
		    					${ars.audioName }
		    				</a>
		    				
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.singer }</font>
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.album }</font>
		    		</td>
		    		<td>
		    			<font size="2" color="888888">${ars.fileSuffix }</font>
		    		</td>
    			</tr>
	    		</c:forEach>
    			</c:otherwise>
    		</c:choose>
    		
    		<tr>
    			<td colspan="4" align="left">
    			<hr>
    				<font color="#555555" size="2">选择：</font>
    				<a href="javascript:void 0;" onclick="checkbox('1','iform')"><font color="#555555" size="2">全选</font></a> -
    				<a href="javascript:void 0;" onclick="checkbox('-1','iform')"><font color="#555555" size="2">反选</font></a> -
    				<a href="javascript:void 0;" onclick="checkbox('0','iform')"><font color="#555555" size="2">取消</font></a>
    				&nbsp&nbsp&nbsp&nbsp<input type="button" id="bt_sub_delete" value=" 删除已经选择音频 " onclick="deleteitem()">
    			</td>
    		</tr>
    		<tr>
    			<td colspan="5" align="center">
    				<div style="border: 1px #000000 solid;width: 270px;height: 20px;line-height: 20px;background-color: ">
    					【<font color="red">${nowpage }</font>/<font color="red">${sessionScope.totpage }</font>】
    					<c:if test="${nowpage <= 1}">
    						<font color="#888888">pre</font>
    					</c:if>
    					<c:if test="${nowpage > 1}">
    						<a href="javascript:void 0;" onclick="fy('${sessionScope.url }','${nowpage-1 }')">pre</a>
    					</c:if>
    					跳转到
    					<select onchange="fy('${sessionScope.url }',this.value)">
    						<c:forEach begin="1" end="${sessionScope.totpage }" var="n">
    							<c:choose>
    								<c:when test="${n eq nowpage}">
    								<option value="${n }" selected="selected">第 ${n } 页</option>
    								</c:when>
    								<c:otherwise>
    								<option value="${n }">第 ${n } 页</option>
    								</c:otherwise>
    							</c:choose>
    						</c:forEach>
    					</select>
    					<c:if test="${nowpage >= sessionScope.totpage}">
    						<font color="#888888">next</font>
    					</c:if>
    					<c:if test="${nowpage < sessionScope.totpage}">
    						<a href="javascript:void 0;" onclick="fy('${sessionScope.url }','${nowpage+1 }')">next</a>
    					</c:if>
    				</div>
    			</td>
    		</tr>
    	</table>
    </form>

    </div>
    
   
  </body>
</html>
