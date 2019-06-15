

$("#date-relatorio").change(function (event) {
    let tags = new TagsView;
    let clearfield = new ClearfieldsController;
    let validacao = new ValidacaoController;
    if(validacao.validacaoDate($(this).val().split("-")[0], new Date().getFullYear())){
    	event.preventDefault();
        clearfield.hide(this.id+"-div");
        $("#consultar-relatorio").attr("disabled", false);
    }else{
        clearfield.show(this.id+"-div");
        $("#consultar-relatorio").attr("disabled", true);
        tags.updateElement(document.getElementById(this.id+"-div"), "span", "Date inválida!");
    }
});



$("#consultar-relatorio").click(function(event){
    if($("#date-relatorio").val() != ""){
        event.preventDefault();
        let tags = new TagsView();
        let api = new Api;
        let objetoJSONPedido = api.apiRelatorioPedido($("#date-relatorio").val().split("-")[0], $("#date-relatorio").val().split("-")[1], $("#date-relatorio").val().split("-")[2]);
        objetoJSONPedido.then(function (data) {
            clearTable(document.getElementById("tbody-pedidos"));
            criarTable(document.getElementById("table-pedidos"),data, tags);
        });
        let objetoJSONCompra = api.apiRelatorioCompra($("#date-relatorio").val().split("-")[0], $("#date-relatorio").val().split("-")[1], $("#date-relatorio").val().split("-")[2]);
        objetoJSONCompra.then(function (data) {
            clearTable(document.getElementById("tbody-compras"));
            criarTableCompras(document.getElementById("table-compras"), data, tags);
        });
    }else{
        clearfield.show("date-relatorio"+"-div");
        $("#consultar-relatorio").attr("disabled", true);
        tags.updateElement(document.getElementById("date-relatorio"+"-div"), "span", "Campo obrigatório!");
    }
});

$(document).ready(function () {
   $("#date-relatorio-div").hide();
    $("#alert-compras").hide();
    $("#alert-pedidos").hide();
});

function criarTableCompras(table, data, tags) {
    if(data != "Lista vazia"){
        $("#alert-compras").hide();
        let k = 0;
        let tbody = table.children[1];
        let colunas = table.children[0].children[0].children;
        let tr = tags.criarElementNoClassAndNoId("tr", tbody);
        for(let i = 0; i<data.length;i++){
            let pedidos = data[i];
            for(let c = 0;c <colunas.length;c++){
                $.each(data[i], function (f, val) {
                    if(k == colunas.length){
                        tr = tags.criarElementNoClassAndNoId("tr", tbody);
                        k = 0;
                    }
                    if(f != "id"){
                        if(f == "pedidos"){
                            $.each(pedidos.pedidos[c].usuario, function (chave, value) {
                                if(chave == "nome"){
                                    criarTd(value, tr, tags);
                                }
                            });
                            $.each(pedidos.pedidos[c].livros, function (chave2, value) {
                                criarTd(value.titulo, tr, tags);
                            });
                        }else{
                            criarTd(val, tr, tags);
                        }
                    }
                    k++;
                });
            }
        }
    }else{
        $("#alert-compras").show();
    }
}

function criarTable(table, data, tags){
    if(data != "Lista vazia"){
        $("#alert-pedidos").hide();
        let k = 0;
        let tbody = table.children[1];
        let colunas = table.children[0].children[0].children;
        let tr = tags.criarElementNoClassAndNoId("tr", tbody);
        for(let i = 0; i<data.length;i++){
            let usuario = data[i];
            let livros = data[i];
            for(let j = 0; j<colunas.length;j++){
                $.each(data[i], function (i, val) {
                    if(k == colunas.length){
                        tr = tags.criarElementNoClassAndNoId("tr", tbody);
                        k = 0;
                    }
                    if(i == colunas[j].id){
                        if(colunas[j].id == "usuario"){
                            $.each(usuario.usuario, function (chave, value) {
                                if(chave == "nome"){
                                    criarTd(value, tr, tags);
                                }
                            });
                        }
                        else if(colunas[j].id != "livros"){
                            criarTd(val, tr, tags);
                        }else{
                            $.each(livros.livros, function (chave2, value) {
                                criarTd(value.titulo, tr, tags);
                            });
                        }
                        k++;
                    }
                });
            }
        }
    }else{
        $("#alert-pedidos").show();
    }
}

function criarTd(val, tr, tags){
    let td = tags.criarElementFatherNoClassAndNoId("td", tr);
    tags.updateElement(td, "span", val);
}

function clearTable(tbody){
    tbody.innerHTML = "";
}