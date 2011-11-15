
var webpath = "";
function file_addform_init(wp)
{
	webpath = wp;
}
//数组删除
Array.prototype.remove=function(dx)
  {
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
  }
  
//数组查找
Array.prototype.lastIndexOf=function(substr,start){
	var ta,rt,d='\0';
	if(start!=null){ta=this.slice(start);rt=start;}else{ta=this;rt=0;}
	ta=ta.reverse();var str=d+ta.join(d)+d,t=str.indexOf(d+substr+d);
	if(t==-1)return -1;rt+=str.slice(t).replace(/[^\0]/g,'').length-2;
	return rt;
}
	
/////////////////////////////////////////////////
//以下为当前ajax处理文档信息
var xmlHttp = null;
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
 
 function addSubmit()//ajax提交函数
 {
  var form = document.getElementById("file_addform_form");
  var formBody =analyseForm(form);//准备好要提交的值
  
  var files = "&file_addform_add_file="+allfiles.toJSONString();
  
  formBody += files;
  
  xmlHttp = getHTTPObject();
  if(xmlHttp==false)
  {alert("你的浏览器不支持详细信息查看，请更新浏览器，或者采用最高版本的浏览器");}
  else
  {
  	  //alert(allfiles);
	  //alert(formBody);
	  url=webpath+'/ItemFileAdd2';//服务器处理数据的地址
	  xmlHttp.open("post",url,true);
	  xmlHttp.onreadystatechange=getMsg;//由getHello函数来处理返回信息
	  xmlHttp.setRequestHeader("cache-control","no-cache");
	  xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
	  xmlHttp.send(formBody);//post方法须在send里发送
	  
	  document.getElementById("file_addform_add_submit").disabled = true;
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
    //location.reload();
    location.replace(location);
    document.getElementById("file_addform_add_submit").disabled = false;
    
   }
  }
 }
