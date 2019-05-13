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