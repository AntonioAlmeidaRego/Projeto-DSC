

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

   $("#addPedido").click(function (event) {
	        event.preventDefault();
	      if(!($("#quantidade").val() == "0") || !($("#quantidade").val().length == 0)){
	    	 let randomPedido = Math.random();
	         let submit = new SubmitRequest("post", "http://localhost:8080/pedido/savePedido");
	         let submitLivro = new SubmitRequest("post", "http://localhost:8080/livrojson/livroJaAdd");
	         let request = new RequestController();

	         request.getLivro(submitLivro, $("#idLivro").val(), session.getSession("user")._idUsuario);

	         if(localStorage.getItem("errorPedido") != null){
                 alert("Este pedido já foi adicionado!");
             }else{
                 session.addSessionPedido($("#preco").text(), $("#idLivro").val(), $("#quantidade").val(), "pedido");
                 session.getSession("pedido");
                 request.submitPedido(submit, new Date(), session.getSession("pedido")._preco, randomPedido, session.getSession("pedido")._idLivro, session.getSession("pedido")._quantidade, session.getSession("user")._idUsuario);
                 alert("Pedido adicionado com Sucesso!");
                 window.location.replace("/pedido/pedidos/"+session.getSession("user")._idUsuario);
             }
 
	      }
	   });