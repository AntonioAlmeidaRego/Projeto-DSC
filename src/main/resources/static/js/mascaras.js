/**
 * 
 */

var incremento = 0;
var bool = false;

$("#phone").keypress(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraPhone(event);
});

$("#cpf").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraCPF(event);
});

$("#rg").keypress(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraRG(event);
});

$("#date").keypress(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraDate(event);
});

$("#cep").keyup(function (event){
    let mascara = new MascaraController(this);
    mascara.mascaraCEP(event);
});

$("#reset").click(function () {
    bool = true;
    $("#bairro").attr("disabled", false);
});

$("#cep").keyup(function (event) {
    if((event.which == 8 || event.keyCode == 8) || (event.which == 46 || event.keyCode == 46)){
        $("#cidade").val("");
        $("#estado").val("");
        //$("#estado").attr("type", "reset");
        $("#bairro").attr("disabled", false);
        if($("#bairro").val() != ""){
            $("#bairro").val("");
            //$("#bairro").attr("type", "reset");
        }
        $("#cep-div").hide("slow");
        incremento = 0;
    }else {
        if (event.originalEvent !== undefined) {
            incremento++;
            if (incremento < 9) {
                let api = new Api();
                let form = document.getElementById("form1");
                api.apiCep($(this).val(), form);
            } else if (bool) {
                incremento = 1;
                bool = false;
            }
        }
    }
});

