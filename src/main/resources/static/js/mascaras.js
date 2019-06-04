/**
 * 
 */
/* Variaveis globais */
const tamanhoCep = 9;
var entrou = false;

/* Mascara do telefone */

$("#phone").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraPhone(event);
});

/* Mascara do CPF autor */

$("#cpf-autor").keyup(function (event) {
   let mascara = new MascaraController(this);
   mascara.mascaraCPF(event);
});

/* Mascara do CPF */

$("#cpf").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraCPF(event);
});

/* Mascara RG */

$("#rg").keypress(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraRG(event);
});

/* Mascara date */

$("#date").keypress(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraDate(event);

});

/* Mascara do Cep e faz o consumo da Api*/

$("#cep").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraCEP(event);
    if(mascara.keyCodeBackspaceAndDelete(event)){
        $("#cidade").val("");
        $("#estado").val("");
        $("#bairro").attr("disabled", false);
        if($("#bairro").val() != ""){
            $("#bairro").val("");
        }
        entrou = false;
        $("#cep-div").hide("slow");
    }else {
        if(mascara.keyCodeNumber(event)){
            if(event.length === undefined){
                if(($("#cep").val().length == tamanhoCep) && (entrou == false)){
                    let api = new Api();
                    entrou = true;
                    let form = document.getElementById("form1");
                    api.apiCep($(this).val(), form);
                }
            }
        }else{
            entrou = false;
            $("#cep").val("");
            $("#estado").val("");
            $("#cidade").val("");
        }
    }
});


$("#reset").click(function () {
    $("#bairro").attr("disabled", false);
});

