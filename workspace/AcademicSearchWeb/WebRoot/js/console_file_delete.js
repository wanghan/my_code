var allbf = new Array();
function search_add_bf(o,tabid)
{
	//var tab = document.getElementById(tabid);
	var tab = tabid;
	var id = o.value;
	var text = o.options[o.selectedIndex].text;
	
	//alert(tab.id+":"+id+":"+text);
	if(JS_cruel_search(allbf,id)<0&&id!="-1")
	{addRow(tab,id,text);allbf.push(id);}
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

function addRow(tab,id,text)
{
   var tbl = findObj(tab,document);        //注意testTable         
   var newTR = tbl.insertRow(tbl.rows.length);
  
   var newNameTD=newTR.insertCell(0);
   newNameTD.align="right";
   newNameTD.innerHTML = text+"：";        
   var newNameTD=newTR.insertCell(1);
   newNameTD.innerHTML = "<input name='"+id+"' id='"+id+"' type='text' value = '"+id+"'/>";
}
