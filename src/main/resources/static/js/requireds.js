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

/* --------------------------------------------------------------------------------------------------------------------- */

/* Inicializa todas as funções do JavaScript */
$(document).ready(function () {
    clearfield = new ClearfieldsController();
    tags = new TagsView();
    var form = document.getElementById("form1");
    
    clearfield.hide("father-login");
    if(form !== undefined){
    	esconderDiv(form, 0, form.length-1);
    }
    clearfield.hide("father-cadastro");
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
                if(form[i].id == "email"){
                    emailEmpty = true;
                }
                clearfield.show(form[i].id+"-div");
                tags.updateElement(document.getElementById(form[i].id+"-div"), "span", "campo obrigatório!");
            }
        }
         if(emailEmpty == false){
             if(!verificarEmail("email")){
                 clearfield.show("father-cadastro");
                 clearfield.clear("father-cadastro");
                 tags.updateElement(document.getElementById("father-cadastro"), "span", "Email inválido!");
             }
         }
        event.preventDefault();
    }


});
/*-----------------------------------------------------------------------------------------------------------------------*/