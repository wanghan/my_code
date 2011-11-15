var n = 0;
var allid = new Array();
function vid_add_kfm(tabid)
{
	//var tab = document.getElementById(tabid);
	alert("ffffffffffffffffffffffffff");
	var tab = tabid;
	
	//alert(tab.id+":"+id+":"+text);
	var id = "file_";
	id += ""+n;
	if(JS_cruel_search(allid,id)<0)
	{
		addRow(tab,id);n++;
	}
}
function JS_cruel_search(data,key)       /*JS暴虐查找*/
{
  re = new RegExp(key,[""])
  return (data.toString().replace(re,"┢").replace(/[^,┢]/g,"")).indexOf("┢")
}

//表tab，输入框id，输入框提示
function findObj(theObj, theDoc)
{
      var p, i, foundObj;
    
      if(!theDoc) theDoc = document;
      if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
      {
        theDoc = parent.frames[theObj.substring(p+1)].document;
        theObj = theObj.substring(0,p);
      }
      if(!(foundObj = theDoc[theObj]) && theDoc.all) foundObj = theDoc.all[theObj];
      for (i=0; !foundObj && i < theDoc.forms.length; i++)
        foundObj = theDoc.forms[theObj];
      for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
        foundObj = findObj(theObj,theDoc.layers.document);
      if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj);
    
      return foundObj;
}

function addRow(tab,id)
{
   var tbl = findObj(tab,document);        //注意testTable         
   var newTR = tbl.insertRow(tbl.rows.length);
  	
   var newNameTD=newTR.insertCell(0);
   newNameTD.align="right";
   newNameTD.innerHTML = "增加关键帧 "+n+"：";        
   var newNameTD=newTR.insertCell(1);
   newNameTD.innerHTML = "<input name='"+id+"' id='"+id+"' type='file'/>";
   return true;
}
