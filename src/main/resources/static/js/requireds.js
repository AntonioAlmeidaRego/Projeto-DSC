var incremento = 0;
var mostrou = false;
$(document).ready(function () {
    $("#cidade").attr("disabled", true);
    $("#estado").attr("disabled", true);
});

$("#email").keyup(function() {
	
	let clearfild = new ClearfieldsController();
	let requerid = new RequiredController(this);
	clearfild.clear("email-div");
	let form = document.getElementById("form1");
	/*if(requerid.requiredEmail(form, 7)){
		clearfild.clear("email-div");
		$("#cadastro").attr("disabled", false);
	}else{
		$("#cadastro").attr("disabled", true);
		$("#email-div").show();
	}*/
	
});

$("#cadastro").click(function (event) {
	var clearfild = new ClearfieldsController();
    let requerid = new RequiredController(this);
    let form = document.getElementById("form1");
    if(requerid.requiredAll(event, form, 7) == false){
        $("#cidade").attr("disabled", false);
        $("#bairro").attr("disabled", false);
        $("#estado").attr("disabled", false);
        $("#cadastro").submit();

        for(let i = 0;i<form.length-1;i++){
        	document.getElementById(form[i].id).value = "";
        }
    }else{
        event.preventDefault();
    }
    
    if(event.originalEvent !== undefined){
        incremento++;
        if(incremento == 2){
            clearfild.clear("nome-div");
            clearfild.clear("cidade-div");
            clearfild.clear("estado-div");
            clearfild.clear("rua-div");
            clearfild.clear("bairro-div");
            incremento = 0;
        }
    }
});
