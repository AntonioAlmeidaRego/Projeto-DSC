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
            if(document.getElementById(form[i].id).value == "") {
                return true;
            }
        }
		return false;
    }

    required(i, form){
        if((document.getElementById(form[i].id).value == "") && (document.getElementById(form[i].id).value.length <= 0)) {
            return true;
        }
        return false;
    }

    requiredInput(id){
        if(document.getElementById(id).value == "") {
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

    requiredCheckBox(form, id){
        let checked = false;
        for(let i = 0; i < form.length;i++){
            if(document.getElementById(form[i].id).id == id){
                if(document.getElementById(form[i].id).checked == true){
                    checked = true;
                }
            }
        }
        if(checked){
            return true;
        }
        return false;
    }

    requiredRadio(id){
        if(document.getElementById(id).checked == true){
            return true;
        }
        return false;
    }

    requiredSenha(idSenha, idconfirmarSenha){
        if(document.getElementById(idSenha).value == document.getElementById(idconfirmarSenha).value){
            return true;
        }
        return false;
    }

    requiredExist(id){
        if($("#"+id).length){
            return true;
        }
        return false;
    }


}