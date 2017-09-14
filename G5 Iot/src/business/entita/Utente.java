package business.entita;

/**
 * Classe entità per Utente
 * 
 * @author redtr_000
 *
 */
public class Utente implements Entita {

	/**
	 * Costruttore di classe
	 * 
	 * @param id
	 *            identificativo dell'utente
	 * @param nome
	 *            nome dell'utente
	 * @param cognome
	 *            cognome dell'utente
	 * @param email
	 *            email dell'utente
	 * @param password
	 *            password di login dell'utente
	 * @param admin
	 *            0 se l'utente è un Cliente, 1 se l'utente è un Admin
	 */
	public Utente(String id, String nome, String cognome, String email, String password, String admin) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public Utente(String nome, String cognome, String email, String password) {
		this("", nome, cognome, email, password, "");
	}

	private String id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String admin;

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
