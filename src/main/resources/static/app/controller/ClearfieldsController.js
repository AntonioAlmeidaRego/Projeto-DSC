class ClearfieldsController {
    clear(id){
        let tag = document.getElementById(id);
        tag.innerHTML = "";
    }
}