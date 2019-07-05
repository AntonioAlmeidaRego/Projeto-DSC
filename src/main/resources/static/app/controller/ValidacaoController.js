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

    validacaoDia(dia, mes){
        if((dia > 0) && (dia <= 31)){
            if((new Date().getFullYear() % 4 == 0 && new Date().getFullYear() % 100 != 0) || (new Date().getFullYear() % 4 != 0 && new Date().getFullYear() % 400 == 0)){
            	if(mes == 1){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 2){
                    if(dia <= 29){
                        return true;
                    } 
                }else if(mes == 3){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 4){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 5){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 6){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 7){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 8){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 9){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 10){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 11){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 12){
                	if(dia <= 31){
                        return true;
                    } 
                }else {
                	return false;
                }
            }else{
            	if(mes == 1){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 2){
                    if(dia <= 28){
                        return true;
                    } 
                }else if(mes == 3){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 4){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 5){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 6){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 7){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 8){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 9){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 10){
                	if(dia <= 31){
                        return true;
                    } 
                }else if(mes == 11){
                	if(dia <= 30){
                        return true;
                    } 
                }else if(mes == 12){
                	if(dia <= 31){
                        return true;
                    } 
                }else{
                	return false;
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
    
    _searchChar(input, char){
        let str = input.value.split("");
        for(let i = 0; i < str.length;i++){
            if(str[i] == char){
                return true;
            }
        }
        return false;
    }

    notAllowedCharacters(element, input){
       if((element.key == "Dead") || (element.key == "AltGraph") || (element.key == "]")
       || (this._searchChar(input, "}")) || (element.key == "]") || (this._searchChar(input, "{"))
       || (element.key == "[") || (this._searchChar(input, "&"))
       || (this._searchChar(input, "-")) || (this._searchChar(input, "#"))
       || (this._searchChar(input, "¨")) || (this._searchChar(input, "(")) || (this._searchChar(input, ")"))
       || (this._searchChar(input, "%"))
       || (this._searchChar(input, "*")) || (element.key == "/") || (this._searchChar(input, "|"))
       || (this._searchChar(input, "+")) || (this._searchChar(input, "="))
       || (element.key == "\\") || (element.key == "'") || (this._searchChar(input, '"'))
       || (this._searchChar(input, "!")) || (element.key == ";") || (this._searchChar(input, ":"))
       || (element.key == ",") || (this._searchChar(input, "$"))){
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