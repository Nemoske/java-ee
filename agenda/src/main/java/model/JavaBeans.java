package model;

// TODO: Auto-generated Javadoc
//Classe responsável pelo objeto contatos, com essa classe podemos pegar e criar/alterar informações do objeto.
//Ela também é utilizada pela classe Controller

/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The id contatos. */
	//Atributos
	private String id_contatos;
	
	/** The nome. */
	private String nome;
	
	/** The telefone. */
	private String telefone;
	
	/** The email. */
	private String email;
	
	/**
	 * Instantiates a new java beans.
	 */
	//Construtor sem parâmetros
	public JavaBeans() {
		// super é opcional
		super();
	}

	/**
	 * Instantiates a new java beans.
	 *
	 * @param id_contatos the id contatos
	 * @param nome the nome
	 * @param telefone the telefone
	 * @param email the email
	 */
	//Construtor com parâmetros 
	public JavaBeans(String id_contatos, String nome, String telefone, String email) {
		super();
		this.id_contatos = id_contatos;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	
	
	//Métodos
	
		/**
	 * Sets the id contatos.
	 *
	 * @param id_contatos the new id contatos
	 */
	//Metodo setter
	public void setId_contatos(String id_contatos) {
		this.id_contatos = id_contatos;
	}
		
		/**
		 * Gets the id contatos.
		 *
		 * @return the id contatos
		 */
		//Metodo getters
	public String getId_contatos() {
		return id_contatos;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the telefone.
	 *
	 * @param numero the new telefone
	 */
	public void setTelefone(String numero) {
		this.telefone = numero;
	}

	/**
	 * Gets the telefone.
	 *
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;

	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
}

