package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	/* Modulo de conexão */
	/* Parâmetros de conexão */

	// Esse método de conexão só funciona se o driver do MySQL estiver baixado e
	// alocado na pasta 'lib'

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = System.getenv("DB_URL");
	
	/** The root. */
	private String root = System.getenv("DB_USER");
	
	
	/** The senha. */
	private String senha = System.getenv("DB_PASSWORD");
	

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		// Variável do tipo Connection para armazenar a conexão com o banco de dados
		Connection con = null;

		// Bloco try-catch para tratamento de possíveis erros de conexão
		try {
			// Carrega o driver JDBC do banco de dados especificado
			Class.forName(driver);

			// DriverManager é uma classe que gerencia os drivers JDBC
			// getConnection cria a conexão com o banco de dados usando URL, usuário e senha
			con = DriverManager.getConnection(url, root, senha);

			// Resumindo: 'con' armazena a conexão criada pelo DriverManager usando os
			// parâmetros fornecidos
		} catch (ClassNotFoundException e) {
			// Exceção lançada se o driver JDBC não for encontrado
			System.out.println("Erro: Driver não encontrado - " + e.getMessage());
		} catch (SQLException erroAoConectar) {
			// Exceção lançada em caso de falha na conexão com o banco de dados
			System.out.println("Erro ao conectar com o banco de dados - " + erroAoConectar.getMessage());
		}

		// Retorna a conexão estabelecida ou null se ocorrer algum erro
		return con;
	}

	/* CRUD */

	/* Método CREATE */

	/**
	 * Creates the.
	 *
	 * @param contato the contato
	 */
	public void create(JavaBeans contato) {
		String insertSql = "insert into contatos (nome,telefone,email) values (?,?,?)";
		try {
			// Abrirr a conexão

			Connection con = conectar();

			// Preparar a query para a execução do banco de dados

			PreparedStatement queryCreate = con.prepareStatement(insertSql);

			// Substituir os parâmetros "?,?,?" pelo conteúdo das variaveis JavaBeans

			queryCreate.setString(1, contato.getNome());
			queryCreate.setString(2, contato.getTelefone());
			queryCreate.setString(3, contato.getEmail());

			// Executar query
			queryCreate.executeUpdate();

			// Encerrar conexão
			queryCreate.close();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/* Método READ */

	/**
	 * Read.
	 *
	 * @return the array list
	 */
	// Este método retorna um ArrayList de objetos do tipo JavaBeans. Isso significa que a lista conterá objetos JavaBeans com dados populados pela consulta SQL.
	public ArrayList<JavaBeans> read() {
	    
	    // Criando uma lista para armazenar os objetos JavaBeans. A lista será preenchida com os dados retornados pela consulta ao banco de dados.
	    ArrayList<JavaBeans> contatos = new ArrayList<>();
	    
	    // Consulta SQL para selecionar todos os registros da tabela 'contatos', ordenados pelo nome
	    String selectSql = "select * from contatos order by nome";
	    
	    // Bloco try-catch para lidar com exceções SQL
	    try {
	        
	        // Estabelecendo conexão com o banco de dados através do método 'conectar'
	        Connection con = conectar();
	        
	        // Preparando a query para ser executada
	        PreparedStatement queryRead = con.prepareStatement(selectSql);
	        
	     // Executa a query e também armazena todos os dados que surgem aparti dessa query, ou seja todos os cadastrados de forma ordenada pelo nome
	        ResultSet retorno = queryRead.executeQuery();
	        
	        // Laço responsável por iterar sobre os resultados da consulta. O 'next()' move o cursor para a próxima linha, retornando 'true' enquanto houver dados.
	        while (retorno.next()) {
	            
	            // Armazenando os dados de cada coluna do banco em variáveis. 'getString()' obtém o valor da coluna no formato String.
	            String id_contato = retorno.getString(1);  // Primeira coluna: id_contato
	            String nome = retorno.getString(2);        // Segunda coluna: nome
	            String telefone = retorno.getString(3);    // Terceira coluna: telefone
	            String email = retorno.getString(4);       // Quarta coluna: email
	            
	            // Criando um novo objeto JavaBeans com os dados obtidos e adicionando-o à lista 'contatos', a referencia desse objeto JavaBeans é própria lista
	            contatos.add(new JavaBeans(id_contato, nome, telefone, email));
	        }
	        
	        // Fechando a conexão após a execução da consulta
	        con.close();
	        
	        // Retornando a lista 'contatos', que agora contém todos os objetos JavaBeans populados com dados do banco
	        return contatos;
	        
	    } catch (SQLException e) {
	        // Em caso de erro, imprimindo a exceção e retornando null
	        System.out.println(e);
	        return null;
	    }
	}
	
	/*Método UPDATE*/
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	//Selecionar contato (parte 1)
	public void selecionarContato(JavaBeans contato) {
		
		//Query de buscar contato para atualizar (padrão)
		String buscarContato = "select * from contatos where id_contatos = ?";
		
		//Try Catch para executar de forma segura com tratamento para futuros erros (padrão)
		try {
			
			//Realizando conexao
			Connection conn = conectar();
			
			//Preparando queryy para ser executada (padrão)
			PreparedStatement querySelect = conn.prepareStatement(buscarContato);
			
			// Substituir os parâmetros "?" pelo conteúdo das variavel do JavaBeans
			querySelect.setString(1,contato.getId_contatos());
			
			// Executa a query e também armazena todos os dados que surgem aparti dessa query, ou seja seleciona todos os ID_contatos existentes no banco de dados e armazena os dados que estão atrelados a eles
			ResultSet retorno = querySelect.executeQuery();
			
			// Laço responsável por iterar sobre os resultados da consulta. O 'next()' move o cursor para a próxima linha, retornando 'true' enquanto houver dados.
			while(retorno.next()) {
				//Esses dados serão transferido para o objeto contato que está no parametro
				contato.setId_contatos(retorno.getString(1)); // Primeira coluna: id_contato
				contato.setNome(retorno.getString(2));  	 // Segunda coluna: nome
				contato.setTelefone(retorno.getString(3));   // Terceira coluna: telefone
				contato.setEmail(retorno.getString(4));		// Quarta coluna: email
			}
			//Encerra a conexão
			conn.close();
			
			
		} catch (SQLException e) {
			System.out.println(e+"Erro linha 156");
		}
	}
	
	/**
	 * Atualizar contato.
	 *
	 * @param contato the contato
	 */
	//Atualizar contato (parte 2)
	public void atualizarContato(JavaBeans contato) {
		String update = "update contatos set nome = ?, telefone = ?, email = ? where id_contatos = ?";
		
		try {
			Connection conn = conectar();
			
			PreparedStatement queryUpdate = conn.prepareStatement(update);
			
			queryUpdate.setString(1, contato.getNome());
			queryUpdate.setString(2, contato.getTelefone());
			queryUpdate.setString(3, contato.getEmail());
			queryUpdate.setString(4, contato.getId_contatos());
			
			queryUpdate.executeUpdate();
			
			queryUpdate.close();
			
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Remover contato.
	 *
	 * @param contato the contato
	 */
	//Deletar contato
	public void removerContato(JavaBeans contato) {
		String delete = "delete  from contatos where id_contatos = ?";
		
		try {
			Connection conn = conectar();
			
			PreparedStatement queryDelete = conn.prepareStatement(delete);
			queryDelete.setString(1, contato.getId_contatos());
			queryDelete.executeUpdate();
			queryDelete.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
}

