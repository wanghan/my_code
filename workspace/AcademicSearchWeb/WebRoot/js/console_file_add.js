

var audfix = new Array("mp3","wma");
var vidfix = new Array("avi","wav","mpg");
var txtfix = new Array("doc","txt","ppt","docx","html");
var imgfix = new Array("jpeg","jpg","png","bmp");

function onMouseBlur(o,tipid,vid,eid)
{
	var aa = document.getElementById(eid).value;
	var fix = "";
	try{
		fix = aa.split(".")[1];
	}catch(e)
	{fix = "";}
	
	var text = "";
	var v = document.getElementById(vid).value;
	if(o.id=="cf_f"){
		if(v=="text")
		{
			for(i=0;i<txtfix.length;i++)
			{
				if(fix.toLowerCase()==txtfix[i])
				{
					
					text="<font color='green'>选择格式正确！可以提交</font>";
					break;
				}
				else
				{
					text="<font color='red'>选择格式错误！请重新选择</font>";
				}
			}
		}
		else if(v=="image")
		{
			for(i=0;i<imgfix.length;i++)
			{
				if(fix.toLowerCase()==imgfix[i])
				{
					
					text="<font color='green'>选择格式正确！可以提交</font>";
					break;
				}
				else
				{
					text="<font color='red'>选择格式错误！请重新选择</font>";
				}
			}
		}
		else if(v=="video")
		{
			for(i=0;i<vidfix.length;i++)
			{
				if(fix.toLowerCase()==vidfix[i])
				{
					
					text="<font color='green'>选择格式正确！可以提交</font>";
					break;
				}
				else
				{
					text="<font color='red'>选择格式错误！请重新选择</font>";
				}
			}
		}
		else if(v=="audio")
		{
			for(i=0;i<audfix.length;i++)
			{
				if(fix.toLowerCase()==audfix[i])
				{
					
					text="<font color='green'>选择格式正确！可以提交</font>";
					break;
				}
				else
				{
					text="<font color='red'>选择格式错误！请重新选择</font>";
				}
			}
		}
	}else if(o.id=="cf_t" || o.id=="cf_n")
	{
		if(fix!="txt")
		{text="<font color='red'>选择格式错误！请重新选择</font>";}
		else
		{text="<font color='green'>选择格式正确！可以提交</font>";}
	}
	else if(o.id=="cf_m")
	{
		for(i=0;i<imgfix.length;i++)
		{
			if(fix.toLowerCase()==imgfix[i])
			{
				
				text="<font color='green'>选择格式正确！可以提交</font>";
				break;
			}
			else
			{
				text="<font color='red'>选择格式错误！请重新选择</font>";
			}
		}
	}
	document.getElementById(tipid).innerHTML = text;
}
function onMouseFocs(o,tipid,vid)
{
	
	var text = "你可以选择的格式：";
	var v = document.getElementById(vid).value;
	if(o.id=="cf_f"){
		if(v=="text")
		{
			for(i=0;i<txtfix.length;i++)
			{
				if(i!=txtfix.length-1)
				text+=txtfix[i]+",";
				else
				text+=txtfix[i]+".";
			}
		}
		else if(v=="image")
		{
			for(i=0;i<imgfix.length;i++)
			{
				if(i!=imgfix.length-1)
				text+=imgfix[i]+",";
				else
				text+=imgfix[i]+".";
			}
		}
		else if(v=="video")
		{
			for(i=0;i<vidfix.length;i++)
			{
				if(i!=vidfix.length-1)
				text+=vidfix[i]+",";
				else
				text+=vidfix[i]+".";
			}
		}
		else if(v=="audio")
		{
			for(i=0;i<audfix.length;i++)
			{
				if(i!=audfix.length-1)
				text+=audfix[i]+",";
				else
				text+=audfix[i]+".";
			}
		}
	}else if(o.id=="cf_t" || o.id == "cf_n")
	{
		text += "txt .";
	}
	else if(o.id=="cf_m")
	{
		for(i=0;i<imgfix.length;i++)
			{
				if(i!=imgfix.length-1)
				text+=imgfix[i]+",";
				else
				text+=imgfix[i]+".";
			}
	}
	document.getElementById(tipid).innerHTML = text;
}