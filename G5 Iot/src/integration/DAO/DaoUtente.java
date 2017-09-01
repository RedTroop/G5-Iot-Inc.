package integration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Utente;
import integration.DBConnector;

/**
 * Classe per la gestione/esecuzione delle query per i dati relativi agli Utenti
 * 
 * @author redtr_000
 *
 */
public class DaoUtente implements DAO<Utente> {

	private static final String VISUALIZZA_TUTTI_QUERY = "SELECT * FROM utenti WHERE admin = 0";
	private static final String INSERISCI_QUERY = "INSERT INTO `test`.`utenti` (`Nome`, `Cognome`, `Email`, `Password`) VALUES (?, ?, ?, ?)";
	private static final String CERCA_QUERY = "SELECT * FROM utenti WHERE email = ?";

	private PreparedStatement query = null;
	private ResultSet result;

	private static final Logger LOGGER = Logger.getLogger(DaoSensore.class.getName());

	@Override
	public boolean inserisci(Utente u) {
		boolean ret = false;
		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(INSERISCI_QUERY);
			query.setString(1, u.getNome());
			query.setString(2, u.getCognome());
			query.setString(3, u.getEmail());
			query.setString(4, u.getPassword());

			query.execute();

			ret = true;

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Inserimento del sensore nel database non riuscito");
		}

		return ret;
	}

	@Override
	public boolean elimina(Utente e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Utente> visualizzaTutti() {
		List<Utente> ret = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(VISUALIZZA_TUTTI_QUERY);
			result = query.executeQuery();
			ret = creaLista(result);

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}

		return ret;
	}

	@Override
	public List<Utente> cerca(String campo, String valore) {
		List<Utente> ret = null;
		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(CERCA_QUERY);
			query.setString(1, valore);
			result = query.executeQuery();
			ret = creaLista(result);

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}

		return ret;
	}

	/**
	 * Crea una lista a partire dal resultset
	 * 
	 * @param res
	 *            risultato della query
	 * @return lista generata
	 */
	private List<Utente> creaLista(ResultSet res) {
		List<Utente> lista = new LinkedList<Utente>();

		try {
			while (res.next()) {

				String id = res.getString("ID");
				String nome = res.getString("Nome");
				String cognome = res.getString("Cognome");
				String email = res.getString("Email");
				String password = res.getString("Password");
				String admin = res.getString("Admin");

				Utente utente = new Utente(id, nome, cognome, email, password, admin);

				lista.add(utente);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Creazione lista non riuscita");
		}

		return lista;
	}

}
