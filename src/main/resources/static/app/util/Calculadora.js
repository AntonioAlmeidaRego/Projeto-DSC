class Calculadora {

    constructor(valor){
        this._valor = valor;
    }

    get valor(){
        return this._valor;
    }

    set valor(valor){
        this._valor = valor;
    }

    calcularFrete(){

    }

    calcularQuantidade(quantidade){
        return this._valor * quantidade;
    }

    calcularDesconto(desconto){
        let valorAux = this._valor;
        let result = desconto / valorAux;
        let result2 = result * valorAux;
        return this._valor - result2;
    }
}