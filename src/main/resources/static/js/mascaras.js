/**
 * 
 */

const tamanhoCep = 9;
var bool = false;
var count = 1;

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
    if(mascara.keyCodeBackspaceAndDelete(event)){
        $("#cidade").val("");
        $("#estado").val("");
        //$("#estado").attr("type", "reset");
        $("#bairro").attr("disabled", false);
        if($("#bairro").val() != ""){
            $("#bairro").val("");
        }
        $("#cep-div").hide("slow");
        count = 1;
    }else {
        if(mascara.keyCodeNumber(event)){
            if(event.length === undefined){
                count++;
                console.log(count);
                if(count == tamanhoCep){
                    if($("#cep").val().length == tamanhoCep){
                        let api = new Api();
                        let form = document.getElementById("form1");
                        api.apiCep($(this).val(), form);
                    }
                    count = 1;
                }
            }
        }else{
            count = 1;
        }
    }
});

$("#reset").click(function () {
    bool = true;
    $("#bairro").attr("disabled", false);
});

