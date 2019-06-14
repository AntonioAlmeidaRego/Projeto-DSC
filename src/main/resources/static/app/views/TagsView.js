var json = new JsonController;
class TagsView {

    constructor(){
        this._pai;
        this._element;
    }

    criarElement(tagName, classe, pai, id){
        if(tagName != "input"){
            this._pai = document.getElementById(pai.id);
            let element = document.createElement(tagName);
            element.setAttribute("id", id);
            element.setAttribute("class", classe);
            this._element = element;
            this._pai.appendChild(element);

            return element;
        }
    }

    criarElementNoClassAndNoId(tagName, pai){
        if(tagName != "input"){
            this._pai = document.getElementById(pai.id);
            let element = document.createElement(tagName);
            this._element = element;
            this._pai.appendChild(element);
            return element;
        }
    }

    criarElementFatherNoClassAndNoId(tagName, pai){
        if(tagName != "input"){
            this._pai = pai;
            let element = document.createElement(tagName);
            this._element = element;
            this._pai.appendChild(element);
            return element;
        }
    }

    criarElementInput(tagName, classe, pai, type, id){
        if(tagName == "input"){
            this._pai = document.getElementById(pai.id);
            let element = document.createElement(tagName);
            element.setAttribute("id", id);
            element.setAttribute("class", classe);
            element.setAttribute("type", type);
            this._element = element;
            this._pai.appendChild(element);
        }
    }

    updateElementInputEmpty(tag, texto){
        return $("#"+tag.id).val(texto);
    }

    updateElementInput(tag, texto){
        return $("#"+tag.id).val($("#"+tag.id).val()+texto);
    }

    updateElementWithoutFather(tag, texto){
        this._element.innerHTML = this.creatHtml(tag, texto);
    }
    
    updateElement(pai, tag, texto){
        pai.innerHTML = this.creatHtml(tag, texto);
    }

    creatHtml(tag, texto){
        return "<"+tag+">"+texto+"<"+tag+"/>";
    }
 
}