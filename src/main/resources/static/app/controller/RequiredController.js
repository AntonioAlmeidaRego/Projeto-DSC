var bool = true;
var achou = false;
class RequiredController {

    constructor(id){
        let input = document.getElementById(id.id);
        this._seletor = new TagsView();
        this._input = input;
    }

    requiredAll(event, form, length){
		for(let i = 1; i <= length;i++){
            if(document.getElementById(form[i].id).value == "") {
                this._seletor.criarElement("div", "alert alert-danger", document.getElementById(form[i].id+"-div"), form[i].id+"-div");
                this._seletor.updateElementWithoutFather("div", "campo obrigatório!");
                bool = true;
            }
        }
		if(bool == true){
		    return true;
        }
		return false;
    }
    
    requiredEmail(form, length){
    	let array = [
    		this._input.value,
    	];
    	
    	for(let i = 0;i< array.length;i++){
    		if(array[i] == "@"){
    			 return true;
    		}
    	}
    	if(achou == false){
    		for(let i = 1; i <= length;i++){
    			if(document.getElementById(form[i].id).id == "email"){
    				this._seletor.criarElement("div", "alert alert-danger", document.getElementById(form[i].id+"-div"), form[i].id+"-div");
                    this._seletor.updateElementWithoutFather("div", "Email Inválido!");
                    return false;
    			}    			
    		}
    	}
    	return false;
    }
}