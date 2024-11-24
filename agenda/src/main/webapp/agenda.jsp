<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
 	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> listaContatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" href="imagens/phone.png">
<link rel="stylesheet" href="style.css">
<title>Agenda</title>
</head>
<body>
	<!-- Página principal -->
	<div class="relatorio">
		<!-- Titulo -->
	<h1>Agenda de contatos</h1>

			<a href="report" class="botao2">Relatório</a>

	<!-- Tag link que encaminha para outra página html -->
	<a href="novo.html" class="botao3">Novo contato</a>

		</div>

	

	<!-- Tabela dos contatos -->
	<table id="tabelaContatos">

		<!-- Cabeçalho da tabela -->
		<thead>
			<tr>
				<!-- Colunas da tabela -->
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>

		<!-- Corpo da tabela (contém os dados dos contatos) -->
		<tbody>

			<!-- Loop que percorre a lista de contatos e imprime cada um em uma nova linha da tabela -->
			<%
			for (int i = 0; i < listaContatos.size(); i++) {
			%>
			<!-- Misturando HTML e código JSP -->

			<!-- O HTML deve ser escrito fora das tags JSP, pois o código JSP é executado no servidor e o HTML é gerado no cliente -->
			
			<tr>
				<!-- A impressão dos dados de cada contato é feita aqui. Para cada item da lista, 
         		pegamos o objeto da posição 'i' e acessamos os seus atributos usando os métodos getters. -->
         		
				<td><%=listaContatos.get(i).getId_contatos()%></td>
				<td><%=listaContatos.get(i).getNome()%></td>
				<td><%=listaContatos.get(i).getTelefone()%></td>
				<td><%=listaContatos.get(i).getEmail()%></td>
				<!-- Botao que direciona o usuario pra proxima pagina caso ele deseje editar um contato, esse botao tambem é responsavel por captar o ID do contato que o usuario deseja editar-->
				<td><a href="select?Id_contatos=<%=listaContatos.get(i).getId_contatos()%>" class="link">Editar</a>
					<a href="javascript: confirmar(<%=listaContatos.get(i).getId_contatos() %>)" class="botao2">Excluir</a>
				</td>
				
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
	
	<script src="scripts/confirmador.js"></script>
</body>

</html>