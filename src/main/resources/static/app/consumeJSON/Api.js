var estado = "";
var cidade = "";
var bairro = "";
var siglaEstado = "";
class Api {

    constructor(){
        this._view = new TagsView();
    }

    _getRelatorio(anoPedido, mesPedido, diaPedido, anoCompra, mesCompra, diaCompra){
        return new Promise(resolve => {
            $.getJSON("/relatoriojson/gerarRelatorio/"+anoPedido+"/"+mesPedido+"/"+diaPedido
                +"/"+anoCompra+"/"+mesCompra+"/"+diaCompra)
            .done(function (data) {
                resolve(data);
            })
            .fail(function () {
                resolve("Erro");
            });
        });
    }

    async apiRelatorio(anoPedido, mesPedido, diaPedido, anoCompra, mesCompra, diaCompra){
        return await this._getRelatorio(anoPedido, mesPedido, diaPedido, anoCompra, mesCompra, diaCompra);
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

}