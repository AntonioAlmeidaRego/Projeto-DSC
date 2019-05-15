class ApiSoap {
    constructor(){

    }

    freteCorreios(cep, peso, comprimento, altura, largura, valor){
        return new Promise( resolve => {
            if(cep.length == 9){
                $.ajax({
                    type: "GET",
                    url: "http://localhost:9000/"+cep+"/"+peso
                        +"/"+comprimento+"/"+altura+"/"+largura
                        +"/"+valor,
                    failure: function (response) {
                        console.log(response);
                    }
                }).done(function (data) {
                     resolve(data);
                });
            }
        });
    }

    async apiCorreios(cep, peso, comprimento, altura, largura, valor){
        return await this.freteCorreios(cep, peso, comprimento, altura, largura, valor);
    }
}