

$(".delete").click(function (event) {
   let result = confirm("Deseja Remover ?");
   if(result){
        alert("ENTIDADE REMOVIDA!");
   }else{
       event.preventDefault();
   }
});

$(".cart_quantity_delete").click(function (event) {
    let result = confirm("Deseja Remover Pedido ?");
    event.preventDefault();
    if(result){
    	alert("PEDIDO CANCELADO!");
        let objeto = this.dataset;
        let idPedido = JSON.parse(JSON.stringify(objeto)).objeto;
        let request = new RequestController();
        let submit = new SubmitRequest("GET","/pedido/cancelaPedido");
        request.cancelePedido(submit, session.getSession("user")._idUsuario, idPedido);
        deleteLineTable(this.parentElement.parentElement);
        let cal = new Calculadora();
        let resultado = cal.calcularSomaPedidos("cart_total");
        $("#resultado-compra").text(resultado.toFixed(2));
    }else{
        event.preventDefault();
    }
});

function deleteLineTable(tr) {
    let tablePedidos = document.getElementById("tabela-pedidos");
    for(let i = 0; i < tablePedidos.children.length;i++){
        if(tablePedidos.children[i] == tr){
            tablePedidos.children.item(i).innerHTML = "";
            break;
        }
    }
}

