/**
 * 
 */


class MascaraController {

	constructor(id) {
		let input = document.getElementById(id.id);
		this._mascara = new Mascara(input);
		this._view = new TagsView();
	}

	mascaraCEP(event){
        if((event.which == 96 || event.keyCode == 96) || (event.which == 97 || event.keyCode == 97) 
        		|| (event.which == 98 || event.keyCode == 98) ||
            (event.which == 99 || event.keyCode == 99) || (event.which == 100 || event.keyCode == 100)
            || (event.which == 101 || event.keyCode == 101)
            || (event.which == 102 || event.keyCode == 102)
            || (event.which == 103 || event.keyCode == 103)
            || (event.which == 104 || event.keyCode == 104)
            || (event.which == 105 || event.keyCode == 105)){
            if(($("#"+this._mascara.input.id).val().length == 5)){
                this._view.updateElementInput(this._mascara.input, "-");
            }
            if($("#"+this._mascara.input.id).val().length > 0 && $("#"+this._mascara.input.id).val().length < 9){
                $("input").mouseup(function(){
                    $("#alert-preenche-cep").html("<div class='alert alert-danger' role='alert'>campo CEP não preenchida, Por favor preencha!</div>");
                });
            }else if($("#"+this._mascara.input.id).val().length == 9){
                $("input").stop();
                $("#alert-preenche-cep").remove();
                $("#alert-numero-cep").remove();
                $("#"+this._mascara.input.id).attr("maxlength","9");
            }
        }else{
            $("#"+this._mascara.input.id).val("");
            $("#"+this._mascara.input.id).attr("type","text");
            $("#alert-numero-cep").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");
        }

        //console.log(event.which);
    }

	mascaraDate(event){
        if((event.which == 96 || event.keyCode == 96) || (event.which == 97 || event.keyCode == 97) 
        		|| (event.which == 98 || event.keyCode == 98) ||
                (event.which == 99 || event.keyCode == 99) || (event.which == 100 || event.keyCode == 100)
                || (event.which == 101 || event.keyCode == 101)
                || (event.which == 102 || event.keyCode == 102)
                || (event.which == 103 || event.keyCode == 103)
                || (event.which == 104 || event.keyCode == 104)
                || (event.which == 105 || event.keyCode == 105)){
            if(($("#"+this._mascara.input.id).val().length == 2)){
                this._view.updateElementInput(this._mascara.input, "/");
            }
            if(($("#"+this._mascara.input.id).val().length == 5)){
                this._view.updateElementInput(this._mascara.input, "/");
            }
            if($("#"+this._mascara.input.id).val().length > 0 && $("#"+this._mascara.input.id).val().length < 9){
                $("input").mouseup(function(){
                    $("#alert-preenche-date").html("<div class='alert alert-danger' role='alert'>campo Data não preenchida, Por favor preencha!</div>");
                });
            }else if($("#"+this._mascara.input.id).val().length == 10){
                $("input").stop();
                $("#alert-preenche-date").remove();
                $("#alert-numero-date").remove();
                $("#"+this._mascara.input.id).attr("maxlength","10");
            }
        }else{
            $("#"+this._mascara.input.id).val("");
            $("#"+this._mascara.input.id).attr("type","text");
            $("#alert-numero-date").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");

        }

    }

	mascaraRG(event){
        if((event.which == 96 || event.keyCode == 96) || (event.which == 97 || event.keyCode == 97) 
        		|| (event.which == 98 || event.keyCode == 98) ||
                (event.which == 99 || event.keyCode == 99) || (event.which == 100 || event.keyCode == 100)
                || (event.which == 101 || event.keyCode == 101)
                || (event.which == 102 || event.keyCode == 102)
                || (event.which == 103 || event.keyCode == 103)
                || (event.which == 104 || event.keyCode == 104)
                || (event.which == 105 || event.keyCode == 105)){
            if(($("#"+this._mascara.input.id).val().length == 1)){
                this._view.updateElementInput(this._mascara.input, ".");
            }
            if(($("#"+this._mascara.input.id).val().length > 4) && ($("#"+this._mascara.input.id).val().length < 7)){
                this._view.updateElementInput(this._mascara.input, ".");
            }
            if(($("#"+this._mascara.input.id).val().length > 7) && ($("#"+this._mascara.input.id).val().length < 8)){
                this._view.updateElementInput(this._mascara.input, ".");
            }
            if($("#"+this._mascara.input.id).val().length > 0 && $("#"+this._mascara.input.id).val().length < 8){
                $("input").mouseup(function(){
                    $("#alert-preenche-rg").html("<div class='alert alert-danger' role='alert'>campo RG não preenchida, Por favor preencha!</div>");
                });
            }else if($("#"+this._mascara.input.id).val().length == 9){
                $("input").stop();
                $("#alert-preenche-rg").remove();
                $("#alert-numero-rg").remove();
                $("#"+this._mascara.input.id).attr("maxlength","9");
            }
        }else{
            $("#"+this._mascara.input.id).val("");
            $("#"+this._mascara.input.id).attr("type","text");
            $("#alert-numero-rg").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");
        }
    }

    mascaraCPF(event){
        if((event.which == 96 || event.keyCode == 96) || (event.which == 97 || event.keyCode == 97) 
        		|| (event.which == 98 || event.keyCode == 98) ||
                (event.which == 99 || event.keyCode == 99) || (event.which == 100 || event.keyCode == 100)
                || (event.which == 101 || event.keyCode == 101)
                || (event.which == 102 || event.keyCode == 102)
                || (event.which == 103 || event.keyCode == 103)
                || (event.which == 104 || event.keyCode == 104)
                || (event.which == 105 || event.keyCode == 105)){
            if(($("#"+this._mascara.input.id).val().length > 2) && ($("#"+this._mascara.input.id).val().length <= 3)){
                this._view.updateElementInput(this._mascara.input, ".");
            }
            if(($("#"+this._mascara.input.id).val().length > 6) && ($("#"+this._mascara.input.id).val().length <= 7)){
                this._view.updateElementInput(this._mascara.input, ".");
            }
            if(($("#"+this._mascara.input.id).val().length > 10) && ($("#"+this._mascara.input.id).val().length <= 11)){
                this._view.updateElementInput(this._mascara.input, "-");
            }
            if(($("#"+this._mascara.input.id).val().length > 0) && ($("#"+this._mascara.input.id).val().length <= 10)){
               $("input").mouseup(function(){
                   $("#alert-preenche-cpf").html("<div class='alert alert-danger' role='alert'>campo CPF não preenchida, Por favor preencha!</div>");
               });
            }
            if($("#"+this._mascara.input.id).val().length == 14){
                $("input").stop();
                $("#alert-preenche-cpf").remove();
                $("#alert-numero-cpf").remove();
                $("#"+this._mascara.input.id).attr("maxlength","14");
            }
        }else{
            $("#"+this._mascara.input.id).val("");
            $("#"+this._mascara.input.id).attr("type","text");
            $("#alert-numero-cpf").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");
        }
    }
	
	mascaraPhone(event){
		if(event.keyCode == 48 || event.keyCode == 49
        		|| event.keyCode == 50 || event.keyCode == 51 || event.keyCode == 52
                || event.keyCode == 53 || event.keyCode == 54
                || event.keyCode == 55 || event.keyCode == 56
                || event.keyCode == 57){
            if(($("#"+this._mascara.input.id).val().length >= 0) && ($("#"+this._mascara.input.id).val().length < 1)){
                this._view.updateElementInput(this._mascara.input, "(");
            }
            if(($("#"+this._mascara.input.id).val().length > 2) && ($("#"+this._mascara.input.id).val().length <= 3)){
                this._view.updateElementInput(this._mascara.input, ") ");
            }
            if(($("#"+this._mascara.input.id).val().length > 9) && ($("#"+this._mascara.input.id).val().length <= 10)){
                this._view.updateElementInput(this._mascara.input, "-");
            }
            if($("#"+this._mascara.input.id).val().length > 0 && $("#"+this._mascara.input.id).val().length <= 14){
                $("input").mouseup(function(){
                    $("#alert-preenche-phone").html("<div class='alert alert-danger' role='alert'>Telefone não preenchida, Por favor preencha!</div>");
                });
            }else if($("#"+this._mascara.input.id).val().length == 15){
                $("input").stop();
                $("#alert-preenche-phone").remove();
                $("#alert-numero-phone").remove();
                $("#"+this._mascara.input.id).attr("maxlength","15");
            }
        }else{
          $("#"+this._mascara.input.id).val("");
          $("#"+this._mascara.input.id).attr("type","text");
          $("#alert-numero-phone").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");
        }
	}
	
}