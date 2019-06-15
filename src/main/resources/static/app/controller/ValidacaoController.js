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

    validacaoDate(data, dataAtual){
        if((data > 2000) && (data <= dataAtual)){
            return true;
        }
        return false;
    }

    _calcularAniversario(dataAtual, dataAnv){
        return dataAtual - dataAnv;
    }

    validacaoDateNascimento(dataAniver, dataAtual){
        if((dataAniver > 1950) && (this._calcularAniversario(dataAtual, dataAniver) > 15)){
            return true;
        }
        return false;
    }

    validacaoMinutos(minuto){
        if(minuto > 0 && minuto <= 60){
            return true;
        }
        return false;
    }

    validacaoHora(hora){
        if(hora > 0 && hora <= 24){
            return true;
        }
        return false;
    }

    validacaoDia(dia){
        if((dia > 0) && (dia <= 31)){
            if(new Date().getFullYear() % 4 == 0){
                if(new Date().getMonth() == 1){
                    if(dia <= 28){
                        return true;
                    }
                }else{
                    return true;
                }
            }else{
                if(new Date().getMonth() == 1){
                    if(dia <= 29){
                       return true;
                    }
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    validacaoMes(mes){
        if((mes > 0) && (mes <= 12)){
            return true;
        }
        return false;
    }

    validacaoSegundo(segundo){
        if((segundo > 0) && (segundo <= 60)){
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

    validacaoFieldIntervalValors(element, valor1, valor2){
        if((element.value > valor1) && (element.value < valor2)){
            return true;
        }
        return false;
    }

    validacaoFieldDesconto(element, valor){
        if(element.value > valor){
            return true;
        }
        return false;
    }

    keyCodeNumber(event){
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
    
    keyCodeTecla(event){
    	if((event.which == 37 || event.keyCode == 37) || (event.which == 38 || event.keyCode == 38) || (event.which == 39 || event.keyCode == 39) || (event.which == 40 || event.keyCode == 40)){
    		return true;
    	}
    	return false;
    }
    
    keyCodeCaractere(event){
    	console.log(event.which);
        if((event.which == 33 || event.keyCode == 33) || (event.which == 34 || event.keyCode == 34)
            || (event.which == 35 || event.keyCode == 35) ||
            (event.which == 36 || event.keyCode == 36) || (event.which == 37 || event.keyCode == 37)
            || (event.which == 38 || event.keyCode == 38)
            || (event.which == 39 || event.keyCode == 39)
            || (event.which == 219 || event.keyCode == 219) 
            || (event.which == 40 || event.keyCode == 40)
            || (event.which == 41 || event.keyCode == 41)
            || (event.which == 42 || event.keyCode == 42)
            || (event.which == 43 || event.keyCode == 43)
            || (event.which == 44 || event.keyCode == 44)
            || (event.which == 45 || event.keyCode == 45)
            || (event.which == 47 || event.keyCode == 47)
            || (event.which == 58 || event.keyCode == 58)
            || (event.which == 59 || event.keyCode == 59)
            || (event.which == 60 || event.keyCode == 60)
            || (event.which == 61 || event.keyCode == 61)
            || (event.which == 62 || event.keyCode == 62)
            || (event.which == 63 || event.keyCode == 63)
            || (event.which == 64 || event.keyCode == 64)
            || (event.which == 91 || event.keyCode == 91)
            || (event.which == 92 || event.keyCode == 92)
            || (event.which == 93 || event.keyCode == 93)
            || (event.which == 94 || event.keyCode == 94)
            || (event.which == 95 || event.keyCode == 95)
            || (event.which == 96 || event.keyCode == 96)
            || (event.which == 123 || event.keyCode == 123)
            || (event.which == 124 || event.keyCode == 124)
            || (event.which == 125 || event.keyCode == 125)){
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