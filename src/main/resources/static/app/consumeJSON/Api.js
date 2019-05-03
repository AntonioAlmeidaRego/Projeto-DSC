var estado = "";
var cidade = "";
var bairro = "";
var siglaEstado = "";
class Api {

    constructor(){
        this._view = new TagsView();
    }

    apiCep(cep, form){
        if(cep.length > 0){
            if(cep.length == 9){
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
                            if(form[i].id == "cidade"){

                                view.updateElementInput(form[i], cidade).attr("disabled", true);
                            }
                            if(form[i].id == "estado"){
                                if(estado != ""){
                                    view.updateElementInput(form[i], estado).attr("disabled",true);
                                }
                            }
                            if(form[i].id == "bairro"){
                                if(bairro != ""){
                                    view.updateElementInput(form[i], bairro).attr("disabled",true);
                                }
                            }
                        }
                    })
                    .fail(function (data) {
                        $("#cep-div").show("slow");
                        let pai = document.getElementById("cep-div");
                        pai.setAttribute("class", "alert alert-danger");
                        view.updateElement(pai, "div", data.statusText);
                    });


            }
        }
    }
}