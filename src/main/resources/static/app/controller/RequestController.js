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

    /*getSubmitPedido(submitRequest, codigo){
        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                codigo: codigo,
            }
        }).done(this.writeJson);
    }*/

    /*writeJson(data){
        localStorage.setItem("id", data.id);
    }

    readJson(){
        return localStorage.getItem("id");
    }*/


}