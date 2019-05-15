class Calculadora {

    constructor(valor){
        this._valor = parseFloat(valor);
    }

    get valor(){
        return this._valor;
    }

    set valor(valor){
        this._valor = parseFloat(valor);
    }

    calcularDays(dia, mes, ano){
        let dataAtual = new Date();
        let diaAtual = dataAtual.getDate();
        for(let i = 1; i<=dia;i++){
            dataAtual = new Date(ano, mes, diaAtual);
            if((dataAtual.getDay() > 0) && (dataAtual.getDay() < 6)){
                diaAtual = diaAtual + 1;
            }else{
                diaAtual = dataAtual.getDate() + 1;
            }
        }
        return dataAtual;
    }

    calcularSomaPedidos(className){
        let soma = 0.0;
        let p = document.getElementsByClassName(className);
        for(let j = 0;j < p.length;j++){
            soma += parseFloat(p[j].innerText);
        }
        return soma;
    }

    calcularQuantidade(quantidade){
        return this._valor * quantidade;
    }

    calcularDesconto(desconto){
    	let descontAux = (desconto/100);
        let result = this._valor * descontAux;
         
        return this._valor - result;
    }
}