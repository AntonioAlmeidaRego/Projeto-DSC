class ClearfieldsController {
    clear(id){
        let tag = document.getElementById(id);
        tag.innerHTML = "";
    }

    hide(id){
       $("#"+id).hide();
    }

    show(id){
        $("#"+id).show();
    }
}