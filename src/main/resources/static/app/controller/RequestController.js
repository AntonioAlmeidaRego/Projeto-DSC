class RequestController {

    constructor(){
    }

    submitPedido(submitRequest, dataPedido, valorTotal){

        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                data: dataPedido,
                valorTotal: valorTotal,
            }
        });
    }

    submitItemPedido(submitRequest, quantidade, valorTotal, idsPedidos, idsLivros){
        $.ajax({
            url: submitRequest.getUrl(),
            method: submitRequest.getMethod(),
            data:{
                quantidade: quantidade,
                valorTotal: valorTotal,
                idsPedidos: idsPedidos,
                idsLivros: idsLivros
            }
        });
    }

}