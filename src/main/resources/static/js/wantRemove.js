

$(".delete").click(function (event) {
   let result = confirm("Deseja Remover ?");
   if(result){
        alert("ENTIDADE REMOVIDA!");
   }else{
       event.preventDefault();
   }
});

