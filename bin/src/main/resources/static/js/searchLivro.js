$("#buscar-livro").click(function (event) {
   if(!($("#search-livro").val() == "")){
        $(this).submit();
   }else{
       event.preventDefault();
       alert("Informe uma informação para a busca de um livro!");
   }
});