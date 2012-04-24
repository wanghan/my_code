var Class = function (){
    var _class = function (){
        this.init.apply (this, arguments);
    }
    
    _class.prototype.init= function (){};
    
    _class.extend = function(obj){
        var extended=obj.extended;
        for(var i in obj){
            _class[i]= obj[i];
        }
        if(extended){ extended(_class)}
    }
    
    _class.include = function(obj){
        var included=obj.included;
        for(var i in obj){
            _class.prototype[i]= obj[i];
        }
        if(included){ included(_class)}
    }
    
    return _class;
};