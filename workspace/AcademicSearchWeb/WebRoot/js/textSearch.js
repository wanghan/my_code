

function init(p,s)
{
	document.getElementById(s).checked = true;
	
	var img1 = createImg(p);
	var img2 = createImg(p);
	var img3 = createImg(p);
	
	document.getElementById("strdate_tip").appendChild(img1);
	document.getElementById("CreatTime_tip").appendChild(img2);
	document.getElementById("PrintTime_tip").appendChild(img3);
}

function createImg(p)
{
	var img = document.createElement("img");
		img.heigth = 20;
		img.width = 30;
		img.src = p+"/image/search_calendar.gif";
	return img;
}

var jjun_flag = "c";
function fun(s)
{
	var ss = new Array("strdate","CreatTime","PrintTime");
	for(i=0;i<ss.length;i++)
	{
		if(s==ss[i])
			setday(document.getElementById(s));
	}	
	
}

//function setSType(e,c)
//{
//	document.getElementById("searchTypeE").value = e;
//	document.getElementById("searchTypeC").value = c;
//	document.getElementById("searchTypeC0").innerHTML = "-"+c;
//}

String.prototype.trim=function(){        
    return this.replace(/(^\s*)|(\s*$)/g, '');     
}  

function more(o,tab)
{
	var obj = document.getElementById(tab);
	var show = new Array("更多>> ","<<简洁");
	if(obj.style.display == "none")
	{
		obj.style.display = "";
		o.innerHTML = show[1];
	}
	else
	{
		obj.style.display = "none";
		o.innerHTML = show[0];
	}
}

function sub(str){
	var v = document.getElementById(str).value;  
	if(v.trim()=="")
	{      
		alert("请输入查询字符串");
    	return false; 
    }
    else
    	return true;   
}  