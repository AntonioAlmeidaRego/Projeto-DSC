class RequestController {

    constructor(){
        this._teste;
    }

    submitPedido(submitRequest, dataPedido, valorTotal, codigoPedido, idLivro, quantidade, idUsuario){

        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                data: dataPedido,
                valorTotal: valorTotal,
                codigo: codigoPedido,
                idLivro: idLivro,
                quantidade: quantidade,
                idUsuario: idUsuario,
            }
        });
    }

    getUsuario(submit, email, senha){

        $.ajax({
            url: submit.getUrl(),
            method: submit.getMethod(),
            data:{
                email: email,
                senha: senha,
            }
        }).done(function (data) {
            localStorage.setItem("json", JSON.stringify(data));
        });
    }

    getLivro(submit, idLivro, idUsuario){
        $.ajax({
            url: submit.getUrl(),
            method: submit.getMethod(),
            data:{
                idLivro: idLivro,
                idUsuario: idUsuario,
            }
        }).fail(function () {
            localStorage.setItem("errorPedido", JSON.stringify("pedido já adicionado!"));
        });
    }

    getJson(key){
        return localStorage.getItem(key);
    }

    writeJson(key, data){
    	console.log(data);
        localStorage.setItem(key, data);
    }
}