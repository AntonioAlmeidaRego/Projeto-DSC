   function idsPedidos(className){
        let array = [];
        let idsString = "";
       let ids = document.getElementsByClassName(className);
       for(let i = 0; i < ids.length;i++){
           array.push(ids[i].innerText);
           if(i < ids.length-1){
               idsString += parseInt(array[i]) + ",";
           }else{
               idsString += parseInt(array[i]);
           }
       }


       return idsString;
   }

    $(document).ready(function () {
        $("#error-quantidade").hide();
        if($("#addPedido").length){
        	$("#addPedido").attr("disabled", true);
        }	
        
       if($("#tabela-pedidos").length){
           let cal = new Calculadora();
           let resultado = cal.calcularSomaPedidos("cart_total");
           $(".ids-pedidos").hide();
           $("#resultado-compra").text(resultado.toFixed(2));

           $("#finalizar-compra").click(function () {
               if(idsPedidos("ids-pedidos") != ""){
            	   alert("COMPRA FINALIZADA COM SUCESSO!");
                   let request = new RequestController();
                   let submit = new SubmitRequest("post", "/compra/saveCompra");
                   request.submitCompra(submit, idsPedidos("ids-pedidos"), resultado, true, new Date());
                   window.location.replace("/");
               }else{
            	   alert("CARINHO VAZIO!");
            	   alert("VAMOS ÀS COMPRAS!!!");
            	   window.location.replace("/");
               }
           });
       }
   });

    const val = document.getElementById("preco").textContent;
   $("#quantidade").keyup(function (event) {
       let validacao = new ValidacaoController();
       let tags = new TagsView();
       let divPai = document.getElementById("error-quantidade");
       
       if((!validacao.keyCodeNumber(event)) && (!validacao.keyCodeBackspaceAndDelete(event))){
           $("#error-quantidade").show();
           tags.updateElement(divPai, "span", "Somente números!");
           $("#addPedido").attr("disabled", true);
           $("#quantidade").val("");
       }if(($("#quantidade").val() == "0") || ($("#quantidade").val().length == 0)){
           $("#addPedido").attr("disabled", true);
       }else{
           $("#error-quantidade").hide();
           $("#addPedido").attr("disabled", false);
       }
       let quantidade = document.getElementById("quantidade").value;
       let calc = new Calculadora(val);
       $("#preco").text(calc.calcularQuantidade(quantidade));
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
                        let precoCorreio = document.getElementById("valor-correio").textContent;
                        let prazoCorreio = document.getElementById("prazo-correio").textContent;
                        let str = precoCorreio.split("");
                        let str2 = "";
                        let dia = prazoCorreio[1] + prazoCorreio[2];
                        for(let i = 0; i<str.length;i++){
                            if(str[i] == ","){
                                str[i] = ".";
                                str2 += str[i];
                            }else{
                                str2 += str[i];
                            }
                        }

                        let cal = new Calculadora(null);
                        let result = (parseFloat(str2) + parseFloat($("#preco").text()));
                        session.addSessionPedido(result, $("#idLivro").val(), $("#quantidade").val(), "pedido");
                        session.getSession("pedido");
                        request.submitPedido(submit, new Date(), session.getSession("pedido")._preco, randomPedido,
                            session.getSession("pedido")._idLivro, session.getSession("pedido")._quantidade,
                            session.getSession("user")._idUsuario, cal.calcularDays(dia, new Date().getMonth(), new Date().getFullYear()), str2);
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


