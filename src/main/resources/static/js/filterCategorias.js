
$(document).ready(function () {
    $("a").click(function (event) {
       waitEvent(this, event);
    });
});

/* Fazer requisição para as categorias */

function waitEvent(element, event) {
    if(event.length === undefined){
        let tags = new TagsView();
        let request = new RequestController();
        let objeto = request.getJsonLivrosCategoria("http://localhost:8080/livrojson/livros/"+element.id, "GET", element.id);
        objeto.then(function (data) {
            clearTags();
            criarTags(data);
        });
    }
}

function criarTags(data) {
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
        img.setAttribute("src", "http://localhost:8080/"+info.urlImagem);

        h2.textContent = info.preco;
        p.textContent = info.titulo;

        $.each(info.categorias, function (j, info) {
            a.setAttribute("href", "/livro/lista-livros-categoria/"+info.id);
            a.textContent ="Detalhes";
        });
    });
}

function clearTags() {
    document.getElementById("fatherAll").innerHTML = "";
}







