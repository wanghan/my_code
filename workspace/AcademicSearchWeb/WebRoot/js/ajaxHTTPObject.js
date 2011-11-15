function getHTTPObject(){
  var xmlhttp = false;
  if(window.XMLHttpRequest){
   xmlhttp = new XMLHttpRequest();
   if(xmlhttp.overrideMimeType){
    xmlhttp.overrideMimeType('text/xml');
   }
  }
  else{
   try{
    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
   }catch(e){
    try{
     xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }catch(E){
     xmlhttp = false;
    }
   }
  }
  return xmlhttp;
 }