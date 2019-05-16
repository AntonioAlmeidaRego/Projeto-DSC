class ValidacaoController {

    validacaoFieldLength(length, element){
        if(element.value.length > 0 && element.value.length <= length){
            return true;
        }
        return false;
    }
    
    validacaoFieldLimitDate(length, element, dataAtual){
    	if((element.value.length == length) && (element.value > 1900) && (element.value <= dataAtual)){
    		return true;
    	}
    	return false;
    }

    validacaoFieldValors(element, valor){
        if((element.value) > valor){
            return true;
        }
        return false;
    }


    keyCodeNumber(event){
    	console.log(event.which);
        if((event.which == 96 || event.keyCode == 96) || (event.which == 97 || event.keyCode == 97)
            || (event.which == 98 || event.keyCode == 98) ||
            (event.which == 99 || event.keyCode == 99) || (event.which == 100 || event.keyCode == 100)
            || (event.which == 101 || event.keyCode == 101)
            || (event.which == 102 || event.keyCode == 102)
            || (event.which == 103 || event.keyCode == 103)
            || (event.which == 104 || event.keyCode == 104)
            || (event.which == 105 || event.keyCode == 105) || (event.keyCode == 190 || event.whitch == 190)){
            return true;
        }else if(event.keyCode == 48 || event.keyCode == 49
            || event.keyCode == 50 || event.keyCode == 51 || event.keyCode == 52
            || event.keyCode == 53 || event.keyCode == 54
            || event.keyCode == 55 || event.keyCode == 56
            || event.keyCode == 57){
            return true;
        }
        return false;
    }
    
    keyCodeBackspaceAndDelete(event){
	    if((event.which == 8 || event.keyCode == 8) || (event.which == 46 || event.keyCode == 46)){
	        return true;
        }
	    return false;
    }

}