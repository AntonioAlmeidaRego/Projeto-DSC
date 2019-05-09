/*---------------------------------------------------------------------------------------------------------------------*/

/* Variaveis Globais */
const extensao = ".jpg";
var exe = "";
var emailEmpty = false;
var clearfield;
var tags;


/*----------------------------------------------------------------------------------------------------------------------*/


/* esconder as divs dos inputs */

function esconderDiv(form, inicio, fim){
    for(let i = inicio; i < fim;i++){
        clearfield.hide(form[i].id+"-div");
    }
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

    /* formularios de todos os cadastros */
  

    if($("#form1").length){
    	esconderDiv(document.getElementById("form1"), 0, document.getElementById("form1").length);
    }
    if($("#form-categoria").length){
        esconderDivCategoria(document.getElementById("form-categoria"), 0, document.getElementById("form-categoria").length);
    }
    if($("#form-editora").length){
    	esconderDiv(document.getElementById("form-editora"), 0, document.getElementById("form-editora").length);
    }
    if($("#form-autor").length){
    	esconderDiv(document.getElementById("form-autor"), 0, document.getElementById("form-autor").length);
    }
    if($("#form-livro").length){
        esconderDiv(document.getElementById("form-livro"), 0, document.getElementById("form-livro").length);
    }
    if($("#form-update-user").length){
        esconderDiv(document.getElementById("form-update-user"), 0, document.getElementById("form-update-user").length);
    }

    /* Esconder as divs de cadastro */

    clearfield.hide("father-login");
    clearfield.hide("father-cadastro");
    clearfield.hide("father-cadastro-categoria");
   
    /* disabilitar os campos */

    $("#cidade").attr("disabled", true);
    $("#estado").attr("disabled", true);
});
/*---------------------------------------------------------------------------------------------------------------------*/

/* Eventos Login */

/* Verificar se o email é válido */
function verificarEmail(id){
    let requerid = new RequiredController(document.getElementById(id));
    return requerid.requiredEmail();
}
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

            let submitRequest = new SubmitRequest("post", "http://localhost:8080/usuariojson/usuario");
            let request = new RequestController();
            request.getUsuario(submitRequest, $("#email-login").val(), $("#senha-login").val());
            let json = JSON.parse(request.getJson("json"));
            session.addSession(json.nome, json.email, json.id, "user");
            $(this).submit();
        }
    }
});
/*-----------------------------------------------------------------------------------------------------------------------*/
/* Eventos Cadastro */

$("input").focus(function () {
    clearfield.hide(this.id+"-div");
});

/* Eventos Cadastro Categoria */

$("input").focus(function () {
    clearfield.hide(this.id+"-div-categoria");
});

/* Evento textArea */


$("#sinopsie-livro").focus(function () {
    clearfield.hide(this.id+"-div");
});

/* Evento select -- editora-livro*/

$("#editora-livro").click(function () {
    clearfield.hide(this.id+"-div");
});

/* Evento de click para o buttom cadastro de usuario */
$("#update-user").click(function (event) {
    let requerid = new RequiredController(this);
    let form = document.getElementById("form-update-user");


    if((!requerid.requiredAll(event, form, form.length-2))){
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


$("#cadastro-usuario").click(function (event) {
    //event.preventDefault();
    let requerid = new RequiredController(this);
    let form = document.getElementById("form1");
    let inputs = document.getElementsByTagName("input");
    //event.preventDefault();

    if((!requerid.requiredAll(event, form, form.length-1)) && (requerid.requiredSenha("senha", "senha-confirmar"))){
        $("#cidade").attr("disabled", false);
        $("#bairro").attr("disabled", false);
        $("#estado").attr("disabled", false);
        clearfield.hide("father-cadastro");
        emailEmpty = true;
        $("#cadastro").submit();
        alert("ENTROU ");
    } else{
        event.preventDefault();
        for(let i = 0; i < form.length-1;i++){
            if(requerid.required(i, form)){
                if(form[i].id == "email"){
                    emailEmpty = true;
                }
                clearfield.show(form[i].id+"-div");
                tags.updateElement(document.getElementById(form[i].id+"-div"), "span", "campo obrigatório!");
            }
        }

        if(($("#senha-confirmar").val() != $("#senha").val())){
            clearfield.show("senha-confirmar-div");
            tags.updateElement(document.getElementById("senha-confirmar-div"), "span", "senhas inválidas!");
        }
         if(emailEmpty == false){
             if(!verificarEmail("email")){
                 clearfield.show("father-cadastro");
                 clearfield.clear("father-cadastro");
                 tags.updateElement(document.getElementById("father-cadastro"), "span", "Email inválido. São aceitos: @hotmail.com, @gmail.com e/ou @outlook.com");
             }
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

   if((!required.required(1, formCategoria)) && (!$("#nome-categoria").val() == "") && (!$("#cidade-editora").val() == "")){
        clearfield.hide("father-cadastro-categoria");
        $("#cadastro-categoria").submit();
   }else{
       for(let j = 1; j < formCategoria.length;j++){
           for(let i = 1; i < inputs.length;i++){
               if(inputs[i].id == formCategoria[j].id){
                   if(required.requiredInput(inputs[i].id)){
                       event.preventDefault();
                       clearfield.show(formCategoria[j].id+"-div-categoria");
                       console.log(formCategoria[j].id);
                       tags.updateElement(document.getElementById(formCategoria[j].id+"-div-categoria"), "span", "campo obrigatório!");
                   }
               }
           }
       }
   }
});

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* Eventos cadastro Editora */

$("#reset-editora").click(function() {
	$("#cidade-editora").val("");
	$("#nome-editora").val("");
});

$("input").focus(function() {
	/*$("input").keyup(function(event) {
		let mascara = new MascaraController(this);
		if(mascara.keyCodeBackspaceAndDelete(event)){
			$(this).val("");
		}
	});*/
});

$("#cadastro-editora").click(function(event) {
	let required = new RequiredController(this);
    let formEditora = document.getElementById("form-editora");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");
    
    if((!required.required(1, formEditora)) && (!$("#nome-editora").val() == "") && (!$("#cidade-editora").val() == "")){
    	$(this).submit();
    }else{
    	for(let j = 1; j < formEditora.length;j++){
            for(let i = 1; i < inputs.length;i++){
                if(inputs[i].id == formEditora[j].id){
                    if(required.requiredInput(inputs[i].id)){
                        event.preventDefault();
                        clearfield.show(formEditora[j].id+"-div");
                        tags.updateElement(document.getElementById(formEditora[j].id+"-div"), "span", "campo obrigatório!");
                    }
                }
            }
        }
    }
});

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* Eventos cadastro Autor */

$("#cadastro-autor").click(function(event) {
	event.preventDefault();
	let required = new RequiredController(this);
    let formAutor = document.getElementById("form-autor");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");

    if((!required.required(1, formAutor)) && (!$("#cpf-autor").val() == "") && (!$("#email-autor").val() == "") && (!$("#nome-autor").val() == "")){
    	$(this).submit();
    }else{
    	for(let j = 1; j < formAutor.length;j++){
            for(let i = 1; i < inputs.length;i++){
                if(inputs[i].id == formAutor[j].id){
                    if(required.requiredInput(inputs[i].id)){
                        event.preventDefault();
                        clearfield.show(formAutor[j].id+"-div");
                        tags.updateElement(document.getElementById(formAutor[j].id+"-div"), "span", "campo obrigatório!");
                    }
                }
            }
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

$("#cadastro-livro").click(function (event) {

    let required = new RequiredController(this);
    let formLivro = document.getElementById("form-livro");
    let tags = new TagsView();
    let inputs = document.getElementsByTagName("input");
    let inputCheck = document.getElementById("idsCategoria-livro");
    let select = document.getElementById("editora-livro");
    let inputFoto = document.getElementById("file-capa");

    if((!required.required(1, formLivro)) && (!$("#titulo-livro").val() == "") &&
        (!$("#peso-livro").val() == "") && (!$("#preco-livro").val() == "")
        && (!$("#isbn-livro").val() == "") && (!$("#edicao-livro").val() == "")
        && (!$("#ano-livro").val() == "") && (inputCheck.checked == true) && (select.value != "") && (exe == extensao)
        && (!$("#sinopsie-livro").val() == "")){
        selectEmpty = false;
        $("#cadastro-livro").submit();
    }else{
        event.preventDefault();

        for(let j = 1; j < formLivro.length-1;j++){
            for(let i = 1; i < inputs.length;i++){
                if(formLivro[j].id != "urlCapa"){
                    if((inputs[i].id == formLivro[j].id)){
                        if(required.requiredInput(inputs[i].id)){
                            clearfield.show(formLivro[j].id+"-div");
                            tags.updateElement(document.getElementById(formLivro[j].id+"-div"), "span", "campo obrigatório!");
                        }
                    }
                }
            }
        }

        if(select.value == ""){
            clearfield.show("editora-livro-div");
            tags.updateElement(document.getElementById("editora-livro-div"), "span", "campo obrigatório!");
        }
        if($("#sinopsie-livro").val() == ""){
            clearfield.show("sinopsie-livro-div");
            tags.updateElement(document.getElementById("sinopsie-livro-div"), "span", "campo obrigatório!");
        }
        if(inputCheck.checked == false){
            clearfield.show("idsCategoria-livro-div");
            tags.updateElement(document.getElementById("idsCategoria-livro-div"), "span", "campo obrigatório!");
        }
        if(exe != extensao){
            clearfield.show("file-capa-div");
            tags.updateElement(document.getElementById("file-capa-div"), "span", "campo obrigatório!");
        }
    }
});