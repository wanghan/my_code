function duplicate(obj){
	//temp construction method
  var f = function(){};
  f.prototype = obj;
  return new f();
}

//inherit prototype
function inheritPrototype(subType, superType){
	var prototype=duplicate(superType.prototype);
	prototype.constructor =subType;
	subType.prototype=prototype;
}

function SuperType(name){
	this.name=name;
	this.color=["red", "green", "blue"];
}

SuperType.prototype.sayName=function (){
	
	alert(this.name);
};

function SubType(name, age){
	//inherit property
	SuperType.call(this, name)
	//new property
	this.age=age;
}

//new prototype
inheritPrototype(SubType, SuperType);

//new method
SubType.prototype.sayAge=function (){
	alert(this.age);
}

