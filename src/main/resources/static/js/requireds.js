/*---------------------------------------------------------------------------------------------------------------------*/

/* Variaveis Globais */
var incremento = 0;
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

function esconderDivEditora(form, inicio, fim){
	for(let i = inicio; i < fim;i++){
		clearfield.hide(form[i].id+"-div");
	}
}

/* --------------------------------------------------------------------------------------------------------------------- */

/* Inicializa todas as funções do JavaScript */
$(document).ready(function () {
    clearfield = new ClearfieldsController();
    tags = new TagsView();

    /* formularios de todos os cadastros */
    
    var form

    if($("#form1").length){
    	esconderDiv(document.getElementById("form1"), 0, document.getElementById("form1").length);
    }
    if($("#form-categoria").length){
        esconderDivCategoria(document.getElementById("form-categoria"), 0, document.getElementById("form-categoria").length);
    }
    if($("#form-editora").length){
    	esconderDivEditora(document.getElementById("form-editora"), 0, document.getElementById("form-editora").length);
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
            tags.updateElement(document.getElementById("father-login"), "span", "Email inválido!");
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

/* Evento de click para o buttom cadastro de usuario */
$("#cadastro-usuario").click(function (event) {
    ////event.preventDefault();
    let requerid = new RequiredController(this);
    let form = document.getElementById("form1");

    if(!requerid.requiredAll(event, form, form.length-1)){
        $("#cidade").attr("disabled", false);
        $("#bairro").attr("disabled", false);
        $("#estado").attr("disabled", false);
        clearfield.hide("father-cadastro");
        emailEmpty = true;
        $("#cadastro").submit();
    } else{
        for(let i = 0; i < form.length-1;i++){
            if(requerid.required(i, form)){
                event.preventDefault();
                if(form[i].id == "email"){
                    emailEmpty = true;
                }
                clearfield.show(form[i].id+"-div");
                tags.updateElement(document.getElementById(form[i].id+"-div"), "span", "campo obrigatório!");
            }
        }
         if(emailEmpty == false){
             if(!verificarEmail("email")){
                 event.preventDefault();
                 clearfield.show("father-cadastro");
                 clearfield.clear("father-cadastro");
                 tags.updateElement(document.getElementById("father-cadastro"), "span", "Email inválido!");
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
	$("input").keyup(function(event) {
		let mascara = new MascaraController(this);
		if(mascara.keyCodeBackspaceAndDelete(event)){
			$(this).val("");
		}
	});
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








