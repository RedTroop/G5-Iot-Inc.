package integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe (Pattern: singleton) per la connessione ad un DB
 * 
 * @author redtr_000
 *
 */
public class DBConnector {
	
	/**
	 * Connessione a MySQL (sessione)
	 */
	private Connection connessione = null;

	/**
	 * Istanza della classe ConnettoreMySQL
	 */
	private static final DBConnector ISTANZA = new DBConnector();

	/**
	 * Costruttore privato per la classe, contentente le credenziali di accesso al server
	 */
	private DBConnector() {

		try {
			new com.mysql.jdbc.Driver();

			connessione = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "slipknot94");

			System.out.println("Database Connected! :D");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Database NOT Connected! :(");
		}
	}

	/**
	 * @return Oggetto Connection: connessione a MySql
	 */
	public Connection getConnessione() {
		return connessione;
	}
	
	/**
	 * @param connessione
	 *            La connessione da impostare
	 */
	public void setConnessione(Connection connessione) {
		this.connessione = connessione;
	}

	/**
	 * Metodo per accedere al singleton
	 * 
	 * @return Unica istanza della classe ConnettoreMySQL
	 */
	public static DBConnector getConnector() {
		return ISTANZA;
	}
}
