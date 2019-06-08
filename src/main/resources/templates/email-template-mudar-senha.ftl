
<meta charset="UTF-8">

<div class="conteudo" style="margin-top: 0px;">
	<!DOCTYPE html>
	<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Alterar Senha</title>
</head>
<body style="background-color: #438EC2;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td class="tema-fundo" style="background-color: #438EC2;">

					<table width="650" align="center" border="0" cellspacing="0"
						cellpadding="0">
						<tbody>
							<tr>
								<td height="50"></td>
							</tr>
							<tr>
								<td>
									<table width="580" align="center" border="0" cellspacing="0"
										cellpadding="0">
										<tbody>
											<tr>
												<td class="conteudo-principal"
													style="background-color: #ffffff; color: #000000; padding: 20px 70px 0 70px; text-align: center;">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tbody>
															<tr>
																<td align="center"
																	class="conteudo-principal-chamada conteudo-editavel"
																	style="font-family: Georgia, serif; font-size: 28px; padding: 50px 0 20px 0;">Alteração
																	de Senha</td>
															</tr>
															<tr>
																<td align="center"
																	class="conteudo-principal-titulo conteudo-editavel"
																	style="font-family: Georgia, serif; font-size: 46px; padding: 20px 0 20px 0;">Olá
																	${name}
																</td>
															</tr>
															<tr>
																<td>
																	<hr>
																</td>
															</tr>
															<tr>
																<td align="center"
																	class="conteudo-principal-texto conteudo-editavel"
																	style="font-family: Georgia, serif; font-size: 16px; padding: 25px 0 0 0;">
																	Você solicitou a redefinição de sua
																	senha de acesso ao Livraria DSC. ${data} . 
																	Click no link para alterar sua senha <a
																	href="http://localhost:8080/usuario/formAlterar/${link}">http://localhost:8080/usuario/formAlterar/${link}</a>.
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td height="250"></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
	</html>
</div>
