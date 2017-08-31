package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private static final Logger LOGGER = Logger.getLogger(DBConnector.class.getName());


	/**
	 * Costruttore privato per la classe, contentente le credenziali di accesso
	 * al server
	 */
	private DBConnector() {

		String databaseUser = null;
		String databasePwd = null;
		String databaseUri = null;
		InputStream in = null;

		try {

			// ottengo informazioni su db, user e pwd dal file dbconfig.ini
			File f = new File("C:" + File.separatorChar + "Users" + File.separatorChar + "redtr_000" + File.separatorChar
					+ "workspace" + File.separatorChar + "G5 Iot" + File.separatorChar + ".settings" + File.separatorChar
					+ "dbconfig.ini");
			
			in = new FileInputStream(f);
			Properties props = new Properties();
			props.loadFromXML(in);

			databaseUri = props.getProperty("URI");
			databaseUser = props.getProperty("USER_NAME");
			databasePwd = props.getProperty("PASSWORD");

			new com.mysql.jdbc.Driver();

			connessione = DriverManager.getConnection(databaseUri, databaseUser, databasePwd);

			System.out.println("Database Connected! :D");

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Connessione al database non riuscita");
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File delle credenziali di accesso al database non trovato");
		} catch (InvalidPropertiesFormatException e) {
			LOGGER.log(Level.SEVERE, "Proprietà XML non valide");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Errore nella lettura del file");
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
