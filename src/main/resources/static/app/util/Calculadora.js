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

    _countDays(mes, ano){
        let mesAnt = 0;
        let count = 0;
        let dia = 1;
        let data = new Date(ano, mes, dia);
        mesAnt = data.getMonth();
        while (mesAnt <= mes){
            dia = dia + 1;
            count++;

            if((mesAnt == 11) && (dia == 32)){
                break;
            }
            mesAnt = new Date(ano, mes, dia).getMonth();
        }
        return count;
    }

    verificarAno(mes){
        let ano = new Date().getFullYear();

        if(ano % 4 == 0){
            return this._countDays(mes, ano);
        }else{
            return this._countDays(mes, ano);
        }
    }

    calcularDays(dia, mes, ano){
        let dataAtual = new Date();
        let diaAtual = dataAtual.getDate();
        let count = diaAtual;

        for(let i = 1; i<=dia;i++){
            count++;
            if(count < this.verificarAno(mes)){
                dataAtual = new Date(ano, mes, diaAtual);
                if((dataAtual.getDay() > 0) && (dataAtual.getDay() < 6)){
                    diaAtual = diaAtual + 1;
                }else{
                    diaAtual = dataAtual.getDate() + 1;
                }
            }else if(count == this.verificarAno(mes)){
                diaAtual = 1;
            }else{
                dataAtual = new Date(ano, mes+1, diaAtual);
                if((dataAtual.getDay() > 0) && (dataAtual.getDay() < 6)){
                    diaAtual = diaAtual + 1;
                }else{
                    diaAtual = dataAtual.getDate() + 1;
                }
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