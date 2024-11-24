<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="containner">
    <!-- Título da página, informando que a página é para editar um contato -->
    <h1>Editar Contato</h1>
    
    <!-- O formulário que irá atualizar os dados do contato. O atributo 'action' define para onde o formulário será enviado (neste caso, 'update'). -->
    <!-- 'name' define o nome do formulário, que pode ser usado no backend para referenciar este formulário -->
    <form name="furmularioContato" action="update">
        
        <!-- A tabela será usada para organizar os campos do formulário em linhas e colunas -->
        <table>
        
            <!-- Linha 1: campo de texto para o ID do contato -->
            <!-- O campo de ID é 'readonly', ou seja, não pode ser alterado pelo usuário -->
            <tr>
                <td>
                    <!-- O campo de entrada exibe o valor do ID a partir da requisição, o valor é preenchido dinamicamente pelo servidor -->
                    <input type="text" name="id" id="id_contato" readonly value="<% out.println(request.getAttribute("id")); %>">
                </td>
            </tr>
            
            <!-- Linha 2: campo de texto para o nome do contato -->
            <tr>
                <td>
                    <!-- O campo de entrada para o nome, com valor preenchido dinamicamente a partir da requisição -->
                    <input type="text" name="nome" class="nomeEEmail" value="<% out.println(request.getAttribute("nome")); %>">
                </td>
            </tr>
            
            <!-- Linha 3: campo de texto para o telefone do contato -->
            <tr>
                <td>
                    <!-- O campo de entrada para o telefone, com valor preenchido dinamicamente a partir da requisição -->
                    <input type="text" name="telefone" class="fone" value="<% out.println(request.getAttribute("telefone")); %>">
                </td>
            </tr>
            
            <!-- Linha 4: campo de texto para o e-mail do contato -->
            <tr>
                <td>
                    <!-- O campo de entrada para o e-mail, com valor preenchido dinamicamente a partir da requisição -->
                    <input type="text" name="email" class="nomeEEmail" value="<% out.println(request.getAttribute("email")); %>">
                </td>
            </tr>
        </table>

        <!-- O botão "Salvar" que chama a função JavaScript 'validar()', que provavelmente será responsável por verificar os dados antes de enviar o formulário -->
        <input type="button" value="Salvar" class="botaoAdicionar" onclick="validar()">
    </form>
</div>
	

	<script src="scripts/conferencia.js"></script>
</body>
</html>