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
	        if((session.getSession("user") != null) && (session.getSession("user")._idUsuario !== undefined)){
                let randomPedido = Math.random();
                let submit = new SubmitRequest("post", "http://localhost:8080/pedido/savePedido");
                let request = new RequestController();
                let objeto = request.getJsonLivro("http://localhost:8080/livrojson/livroJaAdd", "post", $("#idLivro").val(), session.getSession("user")._idUsuario);

                objeto.then(function (data) {
                    if(data == "pedido adicionado com sucesso"){
                        session.addSessionPedido($("#preco").text(), $("#idLivro").val(), $("#quantidade").val(), "pedido");
                        session.getSession("pedido");
                        request.submitPedido(submit, new Date(), session.getSession("pedido")._preco, randomPedido, session.getSession("pedido")._idLivro, session.getSession("pedido")._quantidade, session.getSession("user")._idUsuario);
                        alert("Pedido adicionado com Sucesso!");
                        window.location.replace("/pedido/pedidos/"+session.getSession("user")._idUsuario);
                    }else{
                        alert("Este pedido já foi adicionado!");
                    }
                });
            }else{
                alert("Usuário não autenticado!");
                window.location.replace("/entrar");
            }
	      }
	   });