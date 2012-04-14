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

//inherit method
SubType.prototype =new SuperType();

//new method
SubType.prototype.sayAge=function (){
	alert(this.age);
}

