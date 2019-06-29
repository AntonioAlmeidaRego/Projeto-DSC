/*---------------------------------------------------------------------------------------------------------------------*/

/* Variaveis Globais */
const extensao = ".jpg";

var exe = "";
var exeAutor = "";
var emailEmpty = false;
var entrou = false;
var clearfield;
var tags;


/*----------------------------------------------------------------------------------------------------------------------*/


/* esconder as divs dos inputs */

function esconderDiv(form, inicio, fim){
    for(let i = inicio; i < fim;i++){
        clearfield.hide(form[i].id+"-div");
    }
}

function esconderDivLivro() {
    clearfield.hide("idsCategoria-livro-div");
}

function esconderDivAutor() {
    clearfield.hide("idLivros-autor-div");
}

function esconderDivCategoria(form, inicio, fim){
    for(let i = inicio; i < fim;i++){
        clearfield.hide(form[i].id+"-div-categoria");
    }
}


/* --------------------------------------------------------------------------------------------------------------------- */




/* Inicializa todas as funções do JavaScript */
$(document).ready(function () {

    clearfield = new ClearfieldsController();
    tags = new TagsView();

    if($("#imagem-capa").length){
        exe = extensao;
    }

    /*if($("#imagem-foto").length){
        exeAutor = extensao;
    }*/

    if($("#aux-Preco").length){
        clearfield.hide("aux-Preco");
    }

    /* disabilitar os campos */

    $("#cidade").attr("disabled", true);
    $("#estado").attr("disabled", true);
    $("#update-user-estado").attr("disabled", true);
    $("#update-user-municipio").attr("disabled", true);
});
/*---------------------------------------------------------------------------------------------------------------------*/

/* Cadastro de Tempo */

$("#tempo-dia").keypress(function (event) {
    let validacao = new ValidacaoController;
    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números!";
        $("#cadastro-tempo").attr("disabled", true);
    }else{
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }
});

$("#tempo-dia").keyup(function () {
    let validacao = new ValidacaoController;
    if(validacao.validacaoDia($(this).val())){
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }else{
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Dia inválido!";
        $("#cadastro-tempo").attr("disabled", true);
    }
});

$("#tempo-hora").keypress(function (event) {
    let validacao = new ValidacaoController;
    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente núemros!";
        $("#cadastro-tempo").attr("disabled", true);
    }else{
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }
});

$("#tempo-hora").keyup(function () {
    let validacao = new ValidacaoController;
    if(validacao.validacaoHora($(this).val())){
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.hide(this.id+"-div");
    }else{
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Hora inválida!";
        $("#cadastro-tempo").attr("disabled", true);
    }
});

$("#tempo-mes").keypress(function (event) {
    let validacao = new ValidacaoController;
    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números!";
        $("#cadastro-tempo").attr("disabled", true);
    }else{
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }
});

$("#tempo-mes").keyup(function () {
    let validacao = new ValidacaoController;
    if(validacao.validacaoMes($(this).val())){
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }else{
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Mês inválido!";
        $("#cadastro-tempo").attr("disabled", true);
    }
});

$("#tempo-segundo").keypress(function (event) {
    let validacao = new ValidacaoController;
    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "campo obrigatório!";
        $("#cadastro-tempo").attr("disabled", true);
    }else{
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }
});

$("#tempo-segundo").keyup(function () {
    let validacao = new ValidacaoController;
    if(validacao.validacaoSegundo($(this).val())){
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }else{
        $("#cadastro-tempo").attr("disabled", true);
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Segundo inválido!";
    }
});


$("#tempo-minuto").keypress(function (event) {
    let validacao = new ValidacaoController;
    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "campo obrigatório!";
        $("#cadastro-tempo").attr("disabled", true);
    }else{
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }
});

$("#tempo-minuto").keyup(function () {
    let validacao = new ValidacaoController;
    if(validacao.validacaoMinutos($(this).val())){
        $("#cadastro-tempo").attr("disabled", false);
        clearfield.clear(this.id+"-div");
    }else{
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Minuto inválido!";
        $("#cadastro-tempo").attr("disabled", true);
    }
});


$("#cadastro-tempo").click(function (event) {
    let form = document.getElementById("form-tempo");
    let required = new RequiredController(this);
    let select = document.getElementById("descontos");
    if((required.requiredInputNumberAll(form, 1, form.length-2)) && (select.value != "")){
        $("#cadastro-tempo").submit();
    }else{
        event.preventDefault();
        for(let i = 1; i<form.length-2;i++){
            if(form[i].id != "descontos"){
                if(!required.requiredInputNumber(form, i)){
                    clearfield.clear(form[i].id+"-div");
                    let div = tags.criarTag(document.getElementById(form[i].id+"-div"), "div");
                    div.setAttribute("class", "alert alert-danger");
                    let span = tags.criarTag(div, "span");
                    span.textContent = "campo obrigatório!";
                }
            }
        }
        if(select.value == ""){
            clearfield.clear("descontos-div");
            let div = tags.criarTag(document.getElementById("descontos-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
    }
});

/* Eventos Login */

function loginSemSesion(idBotao) {
    $(idBotao).submit();
}

/* Alterar Senha */

$("#alterarSenha").click(function(event) {
	event.preventDefault();
	window.location.replace("/usuario/alterarSenha");
});

/* Verificar se o email é válido */
function verificarEmail(id){
    let requerid = new RequiredController(document.getElementById(id));
    return requerid.requiredEmail();
}

/* Evento para o cadastro de promocao */

$("#cadastro-promocao").click(function (event) {

   if(($("#promocao-desconto").val().length == 0) || ($("#promocao-desconto").val() == "") || ($("#promocao-desconto").val() == "0")){
      event.preventDefault();
       clearfield.clear("promocao-desconto-div");
       let div = tags.criarTag(document.getElementById("promocao-desconto-div"), "div");
       div.setAttribute("class", "alert alert-danger");
       let span = tags.criarTag(div, "span");
       span.textContent = "campo obrigatório!";
   }else {
       $("#promocao-desconto").val(parseInt($("#promocao-desconto").val()));
       $("#cadastro-promocao").submit();
   }

});

/*---------------------------------------------------------------------------------------------------------------------*/

/* Evento de click para o buttom login */
$("#login").click(function (event) {
    clearfield.show("father-login");
    if(($("#email-login").val().length == 0) && $("#senha-login").val().length == 0){
        event.preventDefault();
        tags.updateElement(document.getElementById("father-login"), "span", "Preencha os campos!");
    }else {
        if(!verificarEmail("email-login")){
            event.preventDefault();
            clearfield.clear("father-login");
            tags.updateElement(document.getElementById("father-login"), "span", "Email inválido. São aceitos: @hotmail.com, @gmail.com e/ou @outlook.com");
        }else {
            clearfield.hide("father-login");

            if($("#email-login").val() != "antonio.alm1020@gmail.com"){
               let request = new RequestController();
               let objeto = request.getJsonUsuario("/usuariojson/usuario", "get"
                   ,$("#email-login").val(), $("#senha-login").val());
               objeto.then(function (data) {
                   if(data != "ERROR"){
                       session.addSession(data.nome, data.email, data.id, "user");
                       if((session.getSession("user")._idUsuario !== undefined) && (session.getSession("user") != null)){
                           $(this).submit();
                       }
                   } 
               });
            }else{
                loginSemSesion("#login");
            }
        }
    }
});
/*-----------------------------------------------------------------------------------------------------------------------*/
/* Eventos Cadastro */

$("input").focus(function () {
    clearfield.clear(this.id+"-div");
});

/* Evento textArea */


$("#sinopsie-livro").focus(function () {
    clearfield.hide(this.id+"-div");
});

/* Evento select -- editora-livro*/

$("#editora-livro").click(function () {
    clearfield.hide(this.id+"-div");
});

$("#descontos").click(function () {
    clearfield.hide(this.id+"-div");
});

/* Evento CheckBox livro*/

$("input").focus(function () {
    clearfield.hide("idsCategoria-livro-div");
});

/* Evento checkbox autor */

$("input").focus(function () {
   clearfield.hide("idLivros-autor-div");
});

/* Evento de click para o buttom cadastro de usuario */
$("#update-user").click(function (event) {
    let requerid = new RequiredController(this);
    let form = document.getElementById("form-update-user");


    if((!requerid.requiredAll(event, form, form.length-2))){
        $("#update-user-municipio").attr("disabled", false);
        $("#update-user-bairro").attr("disabled", false);
        $("#update-user-estado").attr("disabled", false);
        emailEmpty = true;
        $("#cadastro").submit();
    } else{
        event.preventDefault();
        for(let i = 1; i < form.length-2;i++){
            if(requerid.required(i, form)){
                console.log(form[i].id);
                if(form[i].id == "update-user-email"){
                    emailEmpty = true;
                }
                clearfield.show(form[i].id+"-div");
                tags.updateElement(document.getElementById(form[i].id+"-div"), "span", "campo obrigatório!");
            }
        }
        if(!verificarEmail("update-user-email")){
            clearfield.show("update-user-email-div");
            clearfield.clear("update-user-email-div");
            tags.updateElement(document.getElementById("update-user-email-div"), "span", "Email inválido. São aceitos: @hotmail.com, @gmail.com e/ou @outlook.com");
        }

    }
});

$("#cep-padrao").keyup(function (event) {
    let val = new ValidacaoController;
    let tags = new TagsView;
    if(val.validacaoFieldLength(8, this)){
    	if($("#cadastro-usuario").length){
	        $("#cadastro-usuario").attr("disabled", true);
            document.getElementById("preencha-div").innerHTML = "";
            let div = tags.criarTag(document.getElementById("preencha-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "Preencha o campo obrigatório!";
    	}    
    }else{
        if($("#cadastro-usuario").length){
            $("#cadastro-usuario").attr("disabled", false);
            document.getElementById("preencha-div").innerHTML = "";
        }
    }
});

/*Mascara do CEP API*/
$("#update-user-cep").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraCEP(event);
    if(mascara.keyCodeBackspaceAndDelete(event)){
        console.log();
        if(document.getElementById("update-user-estado").disabled){
            $("#update-user-municipio").val("");
        }
        if(document.getElementById("update-user-municipio").disabled){
            $("#update-user-estado").val("");
        }
        if(document.getElementById("update-user-bairro").disabled){
            $("#update-user-bairro").attr("disabled", false);
            if($("#update-user-bairro").val() != ""){
                $("#update-user-bairro").val("");
            }
        }
        entrou = false;
        $("#update-user-cep-div").hide("slow");
    }else {
        if(mascara.keyCodeNumber(event)){
            if(event.length === undefined){
                if(($("#update-user-cep").val().length == tamanhoCep) && (entrou == false)){

                    let api = new Api();
                    entrou = true;
                    let form = document.getElementById("form-update-user");
                    api.apiCep($(this).val(), form);
                }
            }
        }else{
            entrou = false;
            $("#update-user-cep").val("");
            $("#update-user-estado").val("");
            $("#update-user-municipio").val("");
        }
    }
});

function isValiddataAniversario(dataAniver){
    let validacao = new ValidacaoController;
    console.log(dataAniver);
    return validacao.validacaoDateNascimento(dataAniver, new Date().getFullYear());
}

$("#date").keyup(function () {
    let dataNascimento = $(this).val().split("/");
    if(!isValiddataAniversario(dataNascimento[2])){
        clearfield.show("date-div");
        tags.updateElement(document.getElementById("date-div"), "span", "Date inválida!");
    }else{
        clearfield.hide("date-div");
    }
});

$("input").keyup(function(event) {
    if(this.id == "email"){
        let val = new ValidacaoController;
        if(verificarEmail(this.id) && !val.notAllowedCharacters(event, this)){
            document.getElementById("email-div").innerHTML = "";
            if($("#cadastro-usuario").length){
                $("#cadastro-usuario").attr("disabled", false);
            }
            if($("#cadastro-autor").length){
                $("#cadastro-autor").attr("disabled", false);
            }
        }else{
            $("#email-div").show();
            document.getElementById("email-div").innerHTML = "";
            let div = tags.criarTag(document.getElementById("email-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "Email inválido. São aceitos: @hotmail.com, @gmail.com e/ou @outlook.com";

            if($("#cadastro-usuario").length){
                $("#cadastro-usuario").attr("disabled", true);
            }
            if($("#cadastro-autor").length) {
                $("#cadastro-autor").attr("disabled", true);

            }
        }
    }
});


$("#cadastro-usuario").click(function (event) {
    let requerid = new RequiredController(this);
    let form = document.getElementById("form1");
    let inputs = document.getElementsByTagName("input");
    let dataNascimento = document.getElementById("date").value.split("/");

    if((!requerid.requiredAll(event, form, form.length-1)) && (requerid.requiredSenha("senha", "senha-confirmar"))
     && isValiddataAniversario(dataNascimento[2])){
        $("#cidade").attr("disabled", false);
        $("#bairro").attr("disabled", false);
        $("#estado").attr("disabled", false);
        $("#cadastro").submit();
    } else{
        event.preventDefault();
        for(let i = 0; i < form.length-1;i++){
            if(form[i].id != "estado" && form[i].id != "cidade"){
                if((requerid.required(i, form))){
                    clearfield.clear(form[i].id+"-div");
                    let div = tags.criarTag(document.getElementById(form[i].id+"-div"), "div");
                    div.setAttribute("class", "alert alert-danger");
                    let span = tags.criarTag(div, "span");
                    span.textContent = "campo obrigatório!";
                    $("#cadastro-tempo").attr("disabled", true);
                }
            }
        }

        if(($("#senha-confirmar").val() != $("#senha").val())){
            clearfield.clear("senha-confirmar-div");
            let div = tags.criarTag(document.getElementById("senha-confirmar-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "senhas inválidas!";
            $("#cadastro-tempo").attr("disabled", true);
        }

    }


});
/*-----------------------------------------------------------------------------------------------------------------------*/

/* Eventos cadastro categoria */

$("#cadastro-categoria").click(function (event) {
   let required = new RequiredController(this);
   let formCategoria = document.getElementById("form-categoria");
   let tags = new TagsView();

    let inputs = document.getElementsByTagName("input");

   if((!$("#nome-categoria").val() == "")){
        $("#cadastro-categoria").submit();
   }else{
       event.preventDefault();
       for(let j = 1; j < formCategoria.length;j++){
           for(let i = 1; i < inputs.length;i++){
               if(inputs[i].id == formCategoria[j].id){
                   if(required.requiredInput(inputs[i].id)){
                       clearfield.clear(formCategoria[j].id+"-div");
                       let div = tags.criarTag(document.getElementById(formCategoria[j].id+"-div"), "div");
                       div.setAttribute("class", "alert alert-danger");
                       let span = tags.criarTag(div, "span");
                       span.textContent = "campo obrigatório!";
                   }
               }
           }
       }
   }
});

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* Eventos cadastro Editora */

$("#cadastro-editora").click(function(event) {
	let required = new RequiredController(this);
    let formEditora = document.getElementById("form-editora");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");
    
    if((!$("#nome-editora").val() == "") && (!$("#cidade-editora").val() == "")){
    	$(this).submit();
    }else{
        event.preventDefault();
    	for(let j = 1; j < formEditora.length;j++){
            for(let i = 1; i < inputs.length;i++){
                if(inputs[i].id == formEditora[j].id){
                    if(required.requiredInput(inputs[i].id)){
                        console.log(formEditora[j].id);
                        clearfield.clear(formEditora[j].id+"-div");
                        let div = tags.criarTag(document.getElementById(formEditora[j].id+"-div"), "div");
                        div.setAttribute("class", "alert alert-danger");
                        let span = tags.criarTag(div, "span");
                        span.textContent = "campo obrigatório!";
                    }
                }
            }
        }
    }
});

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* Eventos cadastro Autor */


$("#cpf").keyup(function (event) {
    let val = new ValidacaoController;
    let tags = new TagsView;
    if(val.validacaoFieldLength(13, this)){
        if($("#cadastro-usuario").length){
            $("#cadastro-usuario").attr("disabled", true);
            document.getElementById("preencha-div").innerHTML = "";
            let div = tags.criarTag(document.getElementById("preencha-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "Preencha o campo obrigatório!";
        }
        if($("#cadastro-autor").length){
        	$("#cadastro-autor").attr("disabled", true);
            document.getElementById("preencha-div").innerHTML = "";
            let div = tags.criarTag(document.getElementById("preencha-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "Preencha o campo obrigatório!";
        }
    }else{
        if($("#cadastro-usuario").length){
            $("#cadastro-usuario").attr("disabled", false);
            document.getElementById("preencha-div").innerHTML = "";
        }
        if($("#cadastro-autor").length){
            $("#cadastro-autor").attr("disabled", false);
            document.getElementById("preencha-div").innerHTML = "";
        }
    }
});
 
$("#cadastro-autor").click(function(event) {
	let required = new RequiredController(this);
    let formAutor = document.getElementById("form-autor");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");
    let inputsCheck = document.getElementsByClassName("checkbox-autores");

    if((!required.required(2, formAutor)) && (!$("#cpf").val() == "") && (!$("#email").val() == "") && (!$("#nome-autor").val() == "")
        && (isInputCheckBox(inputsCheck, required))){
    	$(this).submit();
    }else{
        event.preventDefault();
    	for(let j = 1; j < formAutor.length-1;j++){
            for(let i = 1; i < inputs.length-1;i++){
                if(formAutor[j].id != "file-capa"){
                    if(inputs[i].id == formAutor[j].id){
                        if(required.requiredInput(inputs[i].id)){
                        	clearfield.clear(formAutor[j].id+"-div");
                            let div = tags.criarTag(document.getElementById(formAutor[j].id+"-div"), "div");
                            div.setAttribute("class", "alert alert-danger");
                            let span = tags.criarTag(div, "span");
                            span.textContent = "campo obrigatório!";
                        }
                    }
               }
            }
        }
        if(!isInputCheckBox(inputsCheck, required)){
            clearfield.clear("idLivros-autor-div");
            let div = tags.criarTag(document.getElementById("idLivros-autor-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
        if(exe != extensao){
            clearfield.clear("file-capa-div");
            let div = tags.criarTag(document.getElementById("file-capa-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
    }
});

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* Eventos cadastro Livro */

$("#file-capa").change(function () {
    let name;
    if(this.files.length > 0) name = this.files[0].name;

    exe = name.substring((name.length-4), name.length);

    if(exe != extensao){
        alert("Tipo de imagem inválida!");
        $(this).val("");
    }
});

function isInputCheckBox(inputscheck, required){
    for(let i = 0; i < inputscheck.length;i++){
        if(required.requiredCheckBox(inputscheck, inputscheck[i].id)){
            return true;
        }
    }
    return false;
}

$("#cadastro-livro").click(function (event) {
   // event.preventDefault();
    let required = new RequiredController(this);
    let formLivro = document.getElementById("form-livro");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");
    let select = document.getElementById("editora-livro");
    let inputsCheck = document.getElementsByClassName("checkbox1");
    let inputFoto = document.getElementById("file-capa");

    if((!$("#titulo-livro").val() == "") &&
        (!$("#peso-livro").val() == "") && (!$("#preco-livro").val() == "")
        && (!$("#isbn-livro").val() == "") && (!$("#edicao-livro").val() == "")
        && (!$("#ano-livro").val() == "") && (isInputCheckBox(inputsCheck, required)) && (select.value != "")
        && (!$("#sinopsie-livro").val() == "") && (!$("#comprimento-livro").val() == "")
        && (!$("#largura-livro").val() == "") && (!$("#altura-livro").val() == "")
        && (!$("#quantidade-livro").val() == "")){
        selectEmpty = false;
        $("#cadastro-livro").submit();
    }else{
        event.preventDefault();

        for(let j = 1; j < formLivro.length-1;j++){
            for(let i = 1; i < inputs.length;i++){
                if(formLivro[j].id != "file-capa"){
                    if((inputs[i].id == formLivro[j].id)){
                        if(required.requiredInput(inputs[i].id)){
                            clearfield.clear(formLivro[j].id+"-div");
                            let div = tags.criarTag(document.getElementById(formLivro[j].id+"-div"), "div");
                            div.setAttribute("class", "alert alert-danger");
                            let span = tags.criarTag(div, "span");
                            span.textContent = "campo obrigatório!";
                        }
                    }
                }
            }
        }

        if(select.value == ""){
            clearfield.clear("editora-livro-div");
            let div = tags.criarTag(document.getElementById("editora-livro-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
        if($("#sinopsie-livro").val() == ""){
            clearfield.clear("sinopsie-livro-div");
            let div = tags.criarTag(document.getElementById("sinopsie-livro-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
        if(exe != extensao){
            clearfield.clear("file-capa-div");
            let div = tags.criarTag(document.getElementById("file-capa-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
        if(!isInputCheckBox(inputsCheck, required)){
            clearfield.clear("idsCategoria-livro-div");
            let div = tags.criarTag(document.getElementById("idsCategoria-livro-div"), "div");
            div.setAttribute("class", "alert alert-danger");
            let span = tags.criarTag(div, "span");
            span.textContent = "campo obrigatório!";
        }
    }

});

/* Validar campos Cadastro de Livro*/
$("#preco-livro").keyup(function (event) {
    let validacao = new ValidacaoController();
    if(!validacao.keyCodeNumber(event)){
        $(this).val("");
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números";
    }if(validacao.validacaoFieldLength(10, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","10");
    }if(!validacao.validacaoFieldDesconto(this, 39.99)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Valor declarado não têm desconto (valor minimo: 40.00, valor maximo: 3000)";
    }if(!validacao.validacaoFieldIntervalValors(this, 19.4, 3001)){
        $("#cadastro-livro").attr("disabled", true);
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Valor declarado não permitido (valor minimo: 19,5, valor maximo: 3000)";
    }else if(validacao.validacaoFieldDesconto(this, 39.99)){
    	clearfield.clear(this.id+"-div");
        $("#cadastro-livro").attr("disabled", false);
    }else{
        $("#cadastro-livro").attr("disabled", false);
    }

 });

$("input").focus(function () {
    if(this.id == $("#preco-livro").id){
        if($("#preco-livro").val() != "" && $("#preco-livro").val().length != 0){
            $("#preco-livro").val(parseFloat($("#preco-livro").val()).toFixed(2));
        }
    }
});

$("#cadastro-livro").click(function () {
    if($("#preco-livro").val() != "" && $("#preco-livro").val().length != 0){
        $("#preco-livro").val(parseFloat($("#preco-livro").val()).toFixed(2));
    }
});

$("#peso-livro").keyup(function (event) {
   let validacao = new ValidacaoController();

    if(!validacao.keyCodeNumber(event)){
    	clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(5, this)){
        clearfield.hide(this.id+"-div");
        $(this).attr("maxlength","5");
    }
    if(!validacao.validacaoFieldValors(this, 0)){
    	clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "O peso não pode ser inferior a 0";
        $("#cadastro-livro").attr("disabled", true);
    }else{
        $("#cadastro-livro").attr("disabled", false);
    }
});

$("#comprimento-livro").keyup(function (event) {
    let validacao = new ValidacaoController();


    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(5, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","5");
    }
    if(!validacao.validacaoFieldValors(this, 16.0)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "O comprimento não pode ser inferior a 16 cm";
        $("#cadastro-livro").attr("disabled", true);
    }else{
        $("#cadastro-livro").attr("disabled", false);
    }

});

$("#largura-livro").keyup(function (event) {
    let validacao = new ValidacaoController();

    if(!validacao.keyCodeNumber(event)){
        clearfield.clear(this.id+"-div");
        let div = tags.criarTag(document.getElementById(this.id+"-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(5, this)){
        clearfield.hide(this.id+"-div");
        $(this).attr("maxlength","5");
    }
    if(!validacao.validacaoFieldValors(this, 11.0)){
        clearfield.clear("editora-livro-div");
        let div = tags.criarTag(document.getElementById("editora-livro-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "A largura não pode ser inferior a 11.cm";
        $("#cadastro-livro").attr("disabled", true);
    }else{
        $("#cadastro-livro").attr("disabled", false);
    }

});

$("#altura-livro").keyup(function (event) {
    let validacao = new ValidacaoController();

    if(!validacao.keyCodeNumber(event)){
        clearfield.clear("altura-livro-div");
        let div = tags.criarTag(document.getElementById("altura-livro-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números!";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(5, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","5");
    }
    if(!validacao.validacaoFieldValors(this,2.0)){
        clearfield.clear("altura-livro-div");
        let div = tags.criarTag(document.getElementById("altura-livro-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "A altura não pode ser inferior a 2 cm";
        $("#cadastro-livro").attr("disabled", true);
    }else{
        $("#cadastro-livro").attr("disabled", false);
    }
});
 

$("#ano-livro").keyup(function (event) {
   let validacao = new ValidacaoController();
   if(!validacao.keyCodeNumber(event)){
       clearfield.clear("ano-livro-div");
       let div = tags.criarTag(document.getElementById("ano-livro-div"), "div");
       div.setAttribute("class", "alert alert-danger");
       let span = tags.criarTag(div, "span");
       span.textContent = "Somente números!";
       $(this).val("");
   }
   if((validacao.validacaoFieldLength(4, this))){
       $(this).attr("maxlength","4");
       clearfield.clear(this.id+"-div");
       $("#cadastro-livro").attr("disabled",false);
       $("#cadastro-livro").css("cursor","pointer");
   }
   if(!(validacao.validacaoFieldLimitDate(4, this, 2019))){
	   $(this).attr("maxlength","4");
       clearfield.clear("ano-livro-div");
       let div = tags.criarTag(document.getElementById("ano-livro-div"), "div");
       div.setAttribute("class", "alert alert-danger");
       let span = tags.criarTag(div, "span");
       span.textContent = "Digite um ano válido!";
       $("#cadastro-livro").attr("disabled", true);
       $("#cadastro-livro").css("cursor","no-drop");
   }
     
});

$("#edicao-livro").keyup(function (event) {
   let validacao = new ValidacaoController();

    if(!validacao.keyCodeNumber(event)){
        clearfield.clear("edicao-livro-div");
        let div = tags.criarTag(document.getElementById("edicao-livro-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números!";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(10, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","10");
    }
});

$("#isbn-livro").keyup(function (event) {
   let validacao = new ValidacaoController();

    if(validacao.validacaoFieldLength(30, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","30");
    }

});

/* Validação campo de desconto */
$("#promocao-desconto").keyup(function (event) {
    let validacao = new ValidacaoController();

    if(!validacao.keyCodeNumber(event)){
        clearfield.clear("promocao-desconto-div");
        let div = tags.criarTag(document.getElementById("promocao-desconto-div"), "div");
        div.setAttribute("class", "alert alert-danger");
        let span = tags.criarTag(div, "span");
        span.textContent = "Somente números!";
        $(this).val("");
    }
    if(validacao.validacaoFieldLength(2, this)){
        clearfield.clear(this.id+"-div");
        $(this).attr("maxlength","2");
    }
});

/* Calcular Desconto do Livro */

const auxPreco = document.getElementById("aux-Preco");

$("#preco-livro").keyup(function (event) {
    let val = new ValidacaoController();

    if(!val.keyCodeBackspaceAndDelete(event)){
        auxPreco.textContent = $("#preco-livro").val();
    }else{
        auxPreco.textContent = "";
    }
});

$("#descontos").click(function (event) {
    let validacao = new ValidacaoController();
    if(($("#preco-livro").val() == "") && ($("#preco-livro").val().length == 0)){
        alert("Preencha o valor do livro!");
        $("#descontos:first-child").val($("#descontos:first-child").val());
    }else{
        if(this[this.selectedIndex].textContent != "Sem desconto"){
            let calc = new Calculadora(parseFloat(auxPreco.textContent));
            let result = calc.calcularDesconto(parseInt(this[this.selectedIndex].textContent));
           // $("#preco-livro").val(parseFloat(result));
            $("#preco-livro").val(parseFloat(result).toFixed(2));
        }else{
            $("#preco-livro").val(parseFloat(auxPreco.textContent).toFixed(2));
        }
    }
});

