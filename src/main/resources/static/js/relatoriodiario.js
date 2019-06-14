

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
    event.preventDefault();
    let tags = new TagsView();
	let api = new Api;
    let objetoJSON = api.apiRelatorio($("#date-relatorio").val().split("-")[0], $("#date-relatorio").val().split("-")[1], $("#date-relatorio").val().split("-")[2],$("#date-relatorio").val().split("-")[0], $("#date-relatorio").val().split("-")[1], $("#date-relatorio").val().split("-")[2]);
    objetoJSON.then(function (data) {
        clearTable(document.getElementById("tbody-pedidos"));
        criarTable(document.getElementById("table-pedidos"),data, tags);
    });

});

$(document).ready(function () {
   $("#date-relatorio-div").hide();
});

function criarTableCompras(table, data, tags) {
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
}

function criarTable(table, data, tags){
    let k = 0;
    let tbody = table.children[1];
    let colunas = table.children[0].children[0].children;
    let tr = tags.criarElementNoClassAndNoId("tr", tbody);
    for(let i = 0; i<data.length;i++){
        let usuario = data[i];
        let livros = data[i];
        console.log(data[i]);
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


}

function criarTd(val, tr, tags){
    let td = tags.criarElementFatherNoClassAndNoId("td", tr);
    tags.updateElement(td, "p", val);
}

function clearTable(tbody){
    tbody.innerHTML = "";
}