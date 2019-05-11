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

    _getUsuario(url, method, email, senha){

        return new Promise(resolve => {
            $.ajax({
                url: url,
                method: method,
                data:{
                    email: email,
                    senha: senha,
                }
            }).done(function (data) {
                resolve(data);
            }).fail(function () {
                resolve("ERROR");
            });
        });
    }

    _getLivro(url, method, idLivro, idUsuario){
        return new Promise(resolve => {
            $.ajax({
                url: url,
                method: method,
                data:{
                    idLivro: idLivro,
                    idUsuario: idUsuario,
                }
            }).done(function () {
                resolve("pedido adicionado com sucesso");
            }).fail(function () {
                resolve("pedido já adicionado!");
            });
        });
    }

    async getJsonLivro(url, method, idLivro, idUsuario){
        return await this._getLivro(url, method, idLivro, idUsuario);
    }

    async getJsonUsuario(url, method, email, senha){
        return await this._getUsuario(url, method, email, senha);
    }
}