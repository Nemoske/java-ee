package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
// Anotação que indica quais urls serão trabalhadas e acessadas pela aplicação
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })

//Controller está herdando métodos e atributos da classe HttpServlet
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// Objeto dao para realizar conexão

	/** The Dao. */
	DAO Dao = new DAO();

	// Objeto JavaBeans(contato) para realizar os métodos setters e getters

	/** The contatos. */
	JavaBeans contatos = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	// Método doGet responsável por capturar a requisição HTTP e verificar o caminho
	// da URL (action).
	// Se o caminho for "/main", ele chama o método "contatos". O método "contatos"
	// redireciona o usuário para a página "agenda.jsp".

	// Se o caminho for "/insert", ele chama o método "novoContatos". O método
	// "novoContatos"
	// seta as informações que vieram da url e passam para objeto "contatos".

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			deletarContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		}

		else {
			response.sendRedirect("index.jsp");
		}
	}

	// Método contatos responsável por redirecionar a requisição para a página
	// "agenda.jsp" utilizando response.sendRedirect().

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// (HttpServletRequest request, HttpServletResponse response) PADRAO
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Mudando a resposta do servidor para "agenda.jsp", ou seja, mudando a URL do
		// navegador
		// response.sendRedirect("agenda.jsp");

		// Criando um ArrayList para armazenar os dados retornados pela classe DAO
		ArrayList<JavaBeans> listaContatos = Dao.read();

		// Mudando o nome da lista para "contatos" para ser utilizada na pagina html,
		// essa alteração está sendo salva no atributo do método "resquest" que mais
		// tarde será enviado para a pagina
		// é possivel utilizar o nome original do atributo, porém isso não é uma boa
		// prática, alterar é interessante para trazer mais clareza ao codigo

		request.setAttribute("contatos", listaContatos);

		// Criando um objeto do tipo RequestDispatcher que é uma classe que tem o papel
		// de
		// encaminhar ou incluir a resposta de um recurso para outro dentro do servidor
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");

		// Aqui ocorre o encaminhamento para a página agenda.jsp. A requisição e a
		// resposta
		// são encaminhadas, e na página agenda.jsp, o atributo "contatos" (que contém a
		// lista)
		// pode ser acessado e utilizado para exibir os dados dos contatos.

		rd.forward(request, response);

	}

	// Criar contato
	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// (HttpServletRequest request, HttpServletResponse response) PADRAO
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setando as informações que vieram da url para o objeto contatos

		contatos.setNome(request.getParameter("nome"));
		contatos.setTelefone(request.getParameter("telefone"));
		contatos.setEmail(request.getParameter("email"));

		// Invocando a class DAO - método CREATE

		Dao.create(contatos);

		// Redirecionar para pagina agenda
		response.sendRedirect("main");

	}

	// Editar Contato

	// Método responsável por listar os detalhes de um contato específico com base
	// no ID recebido.
	// (HttpServletRequest request, HttpServletResponse response) PADRAO

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do ID do contato via parâmetro da requisição HTTP.
		// Esse ID é transmitido na URL ou por um link acionado por uma tag JSP com um
		// parâmetro,
		// permitindo identificar qual contato deve ser editado.
		// String id_contato = request.getParameter("Id_contatos");

		// Opcional: Exibe o ID no console para verificar se foi corretamente recebido
		// (útil para depuração).
		// System.out.println(id_contato);

		// Configura o ID no objeto JavaBeans, preparando-o para ser usado pelo método
		// DAO.
		contatos.setId_contatos(request.getParameter("Id_contatos"));

		// Chama o método na classe DAO para buscar os detalhes do contato no banco de
		// dados,
		// preenchendo o objeto `contatos` com as informações recuperadas.
		Dao.selecionarContato(contatos);

//	    //Teste de recebimento
//	    System.out.println(contatos.getId_contatos());
//	    System.out.println(contatos.getNome());
//	    System.out.println(contatos.getTelefone());
//	    System.out.println(contatos.getEmail());

		// Preenchendo formulario dinamicamente

		request.setAttribute("id", contatos.getId_contatos());
		request.setAttribute("nome", contatos.getNome());
		request.setAttribute("telefone", contatos.getTelefone());
		request.setAttribute("email", contatos.getEmail());

		// Encaminhando para o formulario do arquivo editar.jsp

		RequestDispatcher enviando = request.getRequestDispatcher("editar.jsp");
		enviando.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("telefone"));
//		System.out.println(request.getParameter("email"));

		// Settar as variaveis JavaBeans temporariamente

		contatos.setId_contatos(request.getParameter("id"));
		contatos.setNome(request.getParameter("nome"));
		contatos.setTelefone(request.getParameter("telefone"));
		contatos.setEmail(request.getParameter("email"));

		Dao.atualizarContato(contatos);

		// Redirecionando pagina após atualizar o contato
		response.sendRedirect("main");

	}

	/**
	 * Deletar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluído (conferencia.js)
		// String idContato = request.getParameter("idContatos");
		// Teste de recebimento
		// System.out.println(idContato);

		// Setar variavel javaBeans

		contatos.setId_contatos(request.getParameter("idContatos"));
		// Executar remoção usando Objeto DAO
		Dao.removerContato(contatos);

		// Redirecionando pagina após remover o contato
		response.sendRedirect("main");

	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document documento = new Document();
		try {
			// Tipo de conteúdo
			response.setContentType("apllication/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// Criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir documentp -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// Criar cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela com os contatos
			ArrayList<JavaBeans> listaparaPDF = Dao.read();
			for (int i = 0; i < listaparaPDF.size(); i++) {
				tabela.addCell(listaparaPDF.get(i).getNome());
				tabela.addCell(listaparaPDF.get(i).getTelefone());
				tabela.addCell(listaparaPDF.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}

	}
}
