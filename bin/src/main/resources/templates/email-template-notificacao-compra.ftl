
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="http://www.iagentemail.com.br/v3/templates_inline/natal 4/tema_padrao.css" type="text/css" rel="stylesheet" />
<title>Untitled Document</title>
<link rel="stylesheet" class="template-inline-tema" href="templates_inline/natal 4/tema_padrao_important.css"><link rel="stylesheet" href="css/template-editor.css"><script src="ckeditor/ckeditor.js"></script><script src="ckeditor/ckeditor_inline_config.js"></script><script src="ckeditor/ckeditor_complement.js"></script></head>

<body>
<table align="center" width="100%" border="0" cellpadding="0"  style="max-width:600px;width:100% !important;font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;font-size:10px;" cellspacing="0">
  <tr valign="bottom"  style="background:#E4E4E2;color:#BF392B;">
     
  </tr>
  <tr  style="background:#E4E4E2;color:#BF392B;">
    
  </tr>
  <tr>
    <td class="conteudo-editavel"  height="59" width="50%"  colspan=""  valign="top" style="vertical-align:top;background:#E4E4E2;color:#BF392B;font-size:1.7em;" ><p align="center">Olá ${name}, você foi as compras.<br />
Veja abaixo seus livros selecionados no carinho</p></td>
  </tr>

  <tr>
    <td colspan="2">
      <table width="100%" cellpadding="0"  cellspacing="0">
        <tr style="text-align:center;">
             <td width="5%" rowspan="5">&nbsp;</td>
             <#list livros as livro>
		           <td width="30%" class="conteudo-editavel"  height="7" style="font-size:18px;color:#000;padding:20px 0;">${livro.titulo}</td>
             </#list>
           <td width="5%" rowspan="5">&nbsp;</td>
     </tr>
        <tr>          
        	<#list livros as livro>
          			<td width="176" class="conteudo-editavel edicao-imagem-link"  style="font-size:12px;color:#000;padding:20px 0;"><p align="center"><img style="display:block;margin:auto;width:90%;height:auto;" src="http://localhost:8080/livro/imagem/${livro.id}" alt="" width="167" height="151" /></p>
          			</td>
          	</#list>
        </tr>
        <tr style="text-align:center;">
         	 <#list livros as livro>
         	 	<td height="14" class="conteudo-editavel"  style="font-size:14px;color:#000;padding:0px 0;text-decoration:line-through">${livro.preco}</td>
         	 </#list>	
        </tr>
        <tr style="text-align:center;">
        	<#list pedidos as pedido>
           		<td height="34" class="conteudo-editavel" style="font-size:18px;color:#000;padding:10px 0;color:#BF392B;">${pedido.valorTotal}</td>
        	</#list>	
          </tr>
      </table>
    </td>
  </tr>

  <tr>
     <td colspan="3">
      <table width="100%" cellpadding="0"  cellspacing="0">
        <tr>
             <td width="18">&nbsp;</td>
             <td class="conteudo-editavel" width="372" style="font-size:12px;color:#000;text-align:left;padding:20px 0;">
        Livraria DSC - IFRN Pau dos Ferros - Telefone: +42 25 254587 <br />
       	Site - https://livraria-ifrn.herokuapp.com
        </td>
           <td width="23">&nbsp;</td>
     </tr>

      </table>

     </td>
  </tr>
</table>
</body>
</html>
