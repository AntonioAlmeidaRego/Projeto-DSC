var estado = "";
var cidade = "";
var bairro = "";
var siglaEstado = "";
class Api {

    constructor(){
        this._view = new TagsView();
    }

    apiCep(cep, form){        
        var view = this._view;
        $.getJSON("http://api.postmon.com.br/v1/cep/"+cep)
            .done(function (data) {
                $.each(data.estado_info, function (i, data) {
                    if(i == "nome"){
                        estado = data;
                    }
                });
                cidade = data.cidade;
                bairro = data.bairro;
                siglaEstado = data.estado;



                for(let i = 0;i<form.length-1;i++){
                    if(form[i].id == "cidade" || form[i].id == "update-user-municipio"){
                        $("#update-user-municipio").val("");
                        view.updateElementInput(form[i], cidade).attr("disabled", true);
                    }
                    if(form[i].id == "estado" || form[i].id == "update-user-estado"){
                        if(estado != ""){
                            $("#update-user-estado").val("");
                            view.updateElementInput(form[i], estado).attr("disabled",true);
                        }
                    }
                    if(form[i].id == "bairro" || form[i].id == "update-user-bairro"){
                        if(bairro != ""){
                            $("#update-user-bairro").val("");
                            view.updateElementInput(form[i], bairro).attr("disabled",true);
                        }
                    }
                }
            })
            .fail(function (data) {
                if($("#cep-div").length){
                    $("#cep-div").show("slow");
                    let pai = document.getElementById("cep-div");
                    pai.setAttribute("class", "alert alert-danger");
                    view.updateElement(pai, "div", data.statusText);
                }
                if($("#update-user-cep-div").length){
                    $("#update-user-cep-div").show("slow");
                    let pai = document.getElementById("update-user-cep-div");
                    pai.setAttribute("class", "alert alert-danger");
                    view.updateElement(pai, "div", data.statusText);
                }
            }); 
    }

    apiCorreios(cep_destino, peso, valor){
        if(cep_destino.length == 9){
            $.ajax({
                url: "https://www.sgpweb.com.br/novo/api/consulta-precos-prazos?chave_integracao=90c067ea2d608e044af5d34790f82365",
                method: "post",
                timeout: 0,
                headers: {
                    "Content-Type": "application/json"
                },
                data: "{" +
                    "\n\t\"identificador\": 1,\n\t\"cep_origem\": \"80230-110\",\n\tcep_destino: \\"+cep_destino+"\,\n\t\"formato\": \"1\",\n\t\"peso\": \\"+peso+
                    ",\n\t\"comprimento\": \"25\",\n\t\"altura\": \"40\",\n\t\"largura\": " +
                    "\"11\",\n\t\"mao_propria\": \"S\",\n\t\"aviso_recebimento\": \"S\",\n\t\"valor_declarado\": \\"+valor+"\",\n\t\"servicos\": [\"04162\", \"04669\"]\n}",
            }).done(function (response) {
                console.log(response);
            });
        }
    }
}