
$(document).ready(function () {
    if($(".nav-tabs").length){
        let pai = document.getElementsByClassName("nav-tabs");
        a = pai.item(0).children.item(0).children.item(0);
        startEvent(a);
    }
    $("a").click(function (event) {
       waitEvent(this, event);
    });
});

/* Fazer requisição para as categorias */

function waitEvent(element, event) {
    if(event.length === undefined){
        let tags = new TagsView();
        let request = new RequestController();
        let objeto = request.getJsonLivrosCategoria("/livrojson/livros/"+element.id, "GET", element.id);
        objeto.then(function (data) {
            clearTags();
            criarTags(data, tags);
        });
    }
}

function startEvent(element) {
    let tags = new TagsView();
    let request = new RequestController();
    let objeto = request.getJsonLivrosCategoria("/livrojson/livros/"+element.id, "GET", element.id);
    objeto.then(function (data) {
        clearTags();
        criarTags(data, tags);
    });
}

function criarTags(data, tags) {
    $.each(data, function (i2, info) {
        let element = tags.criarElement("div", "col-sm-3", document.getElementById("fatherAll"), Math.random());
        let element2 = tags.criarElement("div", "product-image-wrapper", element, Math.random());
        let element3 = tags.criarElement("div", "single-products", element2, Math.random());
        let element4 = tags.criarElement("div", "productinfo text-center", element3, Math.random());
        let img = tags.criarElementNoClassAndNoId("img", element4);
        let h2 = tags.criarElementNoClassAndNoId("h2", element4);
        let p = tags.criarElementNoClassAndNoId("p", element4);
        let a = tags.criarElement("a", "btn btn-default add-to-cart", element4, Math.random());
        let i = tags.criarElement("i", "fa fa-shopping-cart", a, Math.random());
        img.setAttribute("src", "/livro/imagem/"+info.id);

        h2.textContent = info.preco;
        p.textContent = info.titulo;

        a.setAttribute("href", "/livro/detalheLivro/"+info.id);
        a.textContent ="Detalhes";

    });
}

function clearTags() {
    document.getElementById("fatherAll").innerHTML = "";
}







