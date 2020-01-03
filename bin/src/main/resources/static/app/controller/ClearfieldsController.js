class ClearfieldsController {
    clear(id){
        let tag = document.getElementById(id);
        tag.innerHTML = "";
    }

    hide(id){
       $("#"+id).hide();
    }

    hideTime(id, time){
        $("#"+id).hide(time);
    }

    show(id){
        $("#"+id).show();
    }
}