var bool = true;
var achou = false;
class RequiredController {

    constructor(id){
        let input = document.getElementById(id.id);
        this._seletor = new TagsView();
        this._input = input;
    }

    requiredAll(event, form, length){
		for(let i = 0; i < length;i++){
		    console.log(form[i].id);
            if(document.getElementById(form[i].id).value == "") {
                bool = true;
            }
        }
		if(bool == true){
		    return true;
        }
		return false;
    }

    required(i, form){
        if(document.getElementById(form[i].id).value == "") {
            return true;
        }
        return false;
    }

    requiredEmail(){
        let array = this._input.value.split("");
    	for(let i = 0;i< array.length;i++){
			if(array[i] == "@"){
				var res = this._input.value.substring(i, this._input.value.length.length);
				console.log(res);
				if((res == "@hotmail.com") || (res == "@gmail.com") || (res == "@outlook.com")){
					return true;
				}
			}
    	}
    	return false;
    }
}