/**
 * 
 */
$("#numero").keypress(function(event){
	if(event.keyCode == 48 || event.keyCode == 49 || event.keyCode == 50 ||
      event.keyCode == 51 || event.keyCode == 52 || event.keyCode == 53 ||
      event.keyCode == 54 || event.keyCode == 55 || event.keyCode == 56 || event.keyCode == 57){
		$("#numero-div").html("");
		$("#numero").val($("#numero").val());
	}else{
		$("#numero").attr("type","reset");
        $("#numero").attr("type","text");
        $("#numero-div").html("<div class='alert alert-danger' role='alert'>Só é aceito números</div>");
	}
});