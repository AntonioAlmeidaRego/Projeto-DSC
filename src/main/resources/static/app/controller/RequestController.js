class RequestController {

    constructor(){
        this._teste;
    }

     submitPedido(submitRequest, dataPedido, valorTotal, codigoPedido, idLivro, quantidade, idUsuario, prazoDias, valorFrete){

        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                data: dataPedido,
                valorTotal: parseFloat(valorTotal),
                codigo: codigoPedido,
                idLivro: idLivro,
                quantidade: quantidade,
                idUsuario: idUsuario,
                prazoEntrega: prazoDias,
                valorFrete: parseFloat(valorFrete),
            }
        });
    }

    submitFavorito(submitRequest, idLivro){
        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                idLivro: idLivro,
            }
        });
    }

    cancelePedido(submitRequest, idUsuario, idPedido){
        $.ajax({
           url: submitRequest.getUrl() + "/"+idUsuario+"/"+idPedido,
           method: submitRequest.getMethod(),
        });
    }

    submitCompra(submit, idsPedido, valorCompra, finalizar, date){
        $.ajax({
           url: submit.getUrl(),
           method: submit.getMethod(),
           data:{
               idsPedidos: idsPedido,
               valorCompra: valorCompra,
               finalizouPedido: finalizar,
               date: date,
           }
        });
    }

    _getCategoriaLivros(url, method, data){
        return new Promise(resolve => {
            $.ajax({
                url: url,
                method: method,
                /*data:{
                    id: data,
                }*/
            }).done(function (data) {
                resolve(data);
            }).fail(function () {
                resolve("Error");
            });
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

    _addFavorito(url, method, idLivro, idUsuario){
        return new Promise(resolve => {
            $.ajax({
                url: url,
                method: method,
                data:{
                    idLivro: idLivro,
                    idUsuario: idUsuario
                }
            }).done(function () {
                resolve("livro adicionado como favorito");
            }).fail(function () {
                resolve("livro já adicionado!");
            });
        });
    }

    async getJsonLivro(url, method, idLivro, idUsuario){
        return await this._getLivro(url, method, idLivro, idUsuario);
    }

    async getJsonUsuario(url, method, email, senha){
        return await this._getUsuario(url, method, email, senha);
    }

    async getJsonLivrosCategoria(url, method){
        return await this._getCategoriaLivros(url, method);
    }

    async getJsonLivroFavorito(url, method, idLivro, idUsuario){
        return await this._addFavorito(url, method, idLivro, idUsuario);
    }
}