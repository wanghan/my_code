//提交按钮事件，ajax提交
var xmlHttp = null;
var webpath = "";

//初始化
function file_update_init(v)
{
	webpath = v;
}
 //获取所有参数
 function analyseForm(form) // 分析form的元素
 {
  var params = new Array();
  for(var i = 0; i < form.elements.length; i++)
  {
     var param = form.elements[i].name;
     param += "=";
     param += form.elements[i].value;
     params.push(param);
  }
  return params.join("&");//返回的是一个数组
 }
 
// function $(str)//获取节点
// {
//  return (document.getElementById(str));
// }

 //v=/ItemFileUpdate;
 function updateSubmit()//ajax提交函数
 {
  var form = document.getElementById("updateForm");
  var formBody =analyseForm(form);//准备好要提交的值
  xmlHttp = getHTTPObject();
  if(xmlHttp==false)
  {alert("你的浏览器不支持详细信息查看，请更新浏览器，或者采用最高版本的浏览器");}
  else
  {
	  url=webpath+'/ItemFileUpdate';//服务器处理数据的地址
	  xmlHttp.open("post",url,true);
	  xmlHttp.onreadystatechange=getMsg;//由getHello函数来处理返回信息
	  xmlHttp.setRequestHeader("cache-control","no-cache");
	  xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
	  xmlHttp.send(formBody);//post方法须在send里发送
	  
	  document.getElementById("updateSave").disabled = true;
  }
 }
 
 function getMsg()//处理ajax返回信息的函数
 {
  if(xmlHttp.readyState==4)
  {
   if(xmlHttp.status==200)
   {
    var Str=xmlHttp.responseText;
    alert(Str);
    document.getElementById("updateSave").disabled = false;
    
   }
  }
 }