

$(document).ready(function () {
   const val = document.getElementById("preco").textContent;
   $("#quantidade").keyup(function () {
      if(($("#quantidade").val() == "0") || ($("#quantidade").val().length == 0)){
         $("#addPedido").attr("disabled", true);
      }else{
         $("#addPedido").attr("disabled", false);
      }
      let quantidade = document.getElementById("quantidade").value;
      let calc = new Calculadora(val);
      $("#preco").text(calc.calcularQuantidade(quantidade));
      console.log();
   });

   $("#cep").keyup(function () {
      let apiCorreios = new Api();
      apiCorreios.apiCorreios($("#cep").val(), $("#preco").val(), val);
   });

   $("#addPedido").click(function (event) {
	        event.preventDefault();
	      if(!($("#quantidade").val() == "0") || !($("#quantidade").val().length == 0)){
	    	 let randomPedido = Math.random();
	         let submit = new SubmitRequest("post", "http://localhost:8080/pedido/savePedido");
	         let request = new RequestController();
	         request.submitPedido(submit, new Date(), $("#preco").text(), randomPedido, $("#idLivro").val(), $("#quantidade").val(), $("#idUsuario").val());
	         alert("Pedido adicionado com Sucesso!");
	      }
	   });
});