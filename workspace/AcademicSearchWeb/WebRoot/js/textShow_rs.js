var rs_ty_s = "";

var type = new Array("image","text","audio","video");

var img = new Array("全部", "动物","植物","仪器", "风景", "活动", "建筑", "食物", "医学");
var imge = new Array("image", "animal","plant","instrument", "scene", "activity", "structure", "food", "medicine");
var text= new Array("全部", "财经","健康","教育", "军事", "科技", "旅游", "体育", "文化", "消费", "政府");
var texte= new Array("text", "finance","health","education", "military", "science", "tour", "sport", "culture", "consumption", "government");
var aud = new Array("全部", "流行", "轻音乐", "古典", "摇滚", "爵士", "乡村&民歌", "影视&动漫", "少儿&宗教", "其他");
var aude = new Array("audio", "Audio_pop", "Audio_light_music", "Audio_classic", "Audio_rock_roll", "Audio_jazz", "Audio_country_folk", "Audio_movie_animation", "Audio_child_religion", "Audio_other");
var vid = new Array("全部", "商业", "娱乐", "健康", "新闻", "教育", "工业和技术", "社会文化", "体育");
var vide = new Array("video", "Video_Business", "Video_Entertainment", "Video_Health", "Video_News", "Video_Education-and-Culture", "Video_Science-and-Technology", "Video_Society-and-Life", "Video_Sports");
var avit = new Array(img,text,aud,vid);
var avite = new Array(imge,texte,aude,vide);

var a0 = new Array("主题", "关键字", "描述");
var b0 = new Array("Subject","Keywords","Description");
var a1 = new Array("关键字");
var b1 = new Array("Keyword");
var a2 = new Array("音频名称", "关键字", "歌手", "专辑", "主题");
var b2 = new Array("AudioName", "Keyword", "Singer", "Album","Theme");
var a3 = new Array("主题", "关键字", "描述", "字幕");
var b3 = new Array("theme", "keyword", "describe", "subtitle");
var a100 = new Array(a0,a1,a2,a3);
var b100 = new Array(b0,b1,b2,b3);

var tmp = new Array();
var tmpe = new Array();
var tm0 = new Array();
var tm1 = new Array();

function showRelatedStampDialog(o,inner)
{
	var v = o.value;
	rs_ty_s = v;
	for(i=0;i<type.length;i++)
	{
		if(v==type[i])
		{
			tmp = avit[i];
			tmpe = avite[i];
			
			tm0 = a100[i];
			tm1 = b100[i];
			break;
		}
	}
	//alert(v+":"+tmp);
	var obj1 = document.getElementById("rs_sjfl");
	obj1.length = 0;
	for(i = 0;i<tmp.length;i++)
	{
		obj1.appendChild(createOption(tmpe[i],tmp[i]));
	}
	theChange('rs_sjfl');
	
	var ob0 = document.getElementById("rs_glx0");
		ob0.length = 0;
	for(i = 0;i<tm0.length;i++)
	{
		ob0.appendChild(createOption(tm1[i],tm0[i]));
	}
	
	//document.getElementById("rs_data_type").value = v;
	
	document.getElementById("avitShowName").innerHTML = "文本"+inner;
	
	s();
}

function createOption(v,t)
{
    var option=document.createElement("option");
   	option.value=v;
   	option.innerHTML=t;
  	
	return option;
}

function theChange(v)
{
	var o = document.getElementById(v);
	for(index=0;index<o.options.length;index++)
	{
		if (o.options[index].selected == true)
		{
			var   Value   =   o.options[index].value;  
  			var   Text    =   o.options[index].text;
  			break;
  		}
	}
	document.getElementById("rs_data_type").value = Value+","+Text;
}

////////////////////////////
function theSearch()
{
	var paths = new Array(
		"/AUDRweb/mainimage.html?rs=text",
		"/AUDRweb/TextSearch?rs=text",
		"/AUDRweb/AudioSearch?rs=text",
		"/AUDRweb/VideoSearch?rs=text"
		);
		
	//"/AUDRweb/RS_AudioSearch?rs=1",	
	var toPath = "";
	for(i=0;i<type.length;i++)
	{
		if(rs_ty_s==type[i])
		{
			toPath = paths[i];
			break;
		}
	}
	
	document.getElementById("avit_rs_form").action = toPath;
	//alert(toPath);
	
	var cbs = document.getElementsByName("cb_rs");
	for(i=0;i<cbs.length;i++)
	{
		if(cbs[i].checked)
		{
			theCancal();
			return true;
		}
	}
	alert("请选择关联项");
	return false;
	
}

function theCancal()
{
	 var r = document.getElementsByName("related_radio");
	 for(i=0;i<r.length;i++){
		 if(r.checked)
		 {
		 	r.checked = false;
		 }
	 }
	 h();
}
////////////////////////////////////////////
function l()
{
	var obox=document.getElementById('show_rs');
        if (obox !=null && obox.style.display !="none")
        {
            var w=420;
            var h=270;
            var x,y;
            
            if (window.innerWidth)
            {
                x=window.pageXOffset+(window.innerWidth-w)/2 +"px";
                y=window.pageYOffset+(window.innerHeight-h)/2 +"px";
            }
            else
            {
                var dde=document.documentElement;
                x=dde.scrollLeft+(dde.offsetWidth-w)/2 +"px";
                y=dde.scrollTop+(dde.offsetHeight-h)/2 +"px";
            }
            
            obox.style.left=x;
            obox.style.top=y;
         }
}

//显示遮盖层
function showDlg()
{
	var objDeck = document.getElementById("deck");
        if(!objDeck)
        {
            objDeck = document.createElement("div");
            objDeck.id="deck";
            document.body.appendChild(objDeck);
        }
        objDeck.style.height = document.body.scrollLeft+document.body.clientWidth;
        objDeck.style.height = document.body.scrollTop+document.body.clientHeight;
        
        
        objDeck.className="showDeck";
        objDeck.style.filter="alpha(opacity=80)";
        objDeck.style.opacity=40/100;
        objDeck.style.MozOpacity=40/100;
        
        document.getElementById("showdiv").style.display = "none";

        document.body.style.overflow = "hidden";
}
//移除遮盖层
function hiddenDlg()
{
	var o = document.getElementById("deck");
	o.parentNode.removeChild(o);
	
	document.getElementById("showdiv").style.display = "";
	document.body.style.overflow = "auto";
}

function s()
{
	var x,y,w,h;
		w = 420;
		h = 270;
		x = (document.body.offsetWidth-w)/2;
		y = (document.body.offsetHeight-h)/2;
		
	var d = document.getElementById("show_rs");
		d.style.width = w;
		d.style.height = h;
		d.style.left = x;
		d.style.top = y;
		d.style.display = "";
		
		showDlg();
}
function h()
{
	hiddenDlg();
	document.getElementById("show_rs").style.display = "none";
}