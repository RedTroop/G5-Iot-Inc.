package integration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Impianto;
import business.entita.Utente;
import integration.DBConnector;

/**
 * Classe per la gestione/esecuzione delle query per i dati relativi agli
 * Impianti
 * 
 * @author Matteo
 *
 */
public class DaoImpianto implements DAO<Impianto> {

	private static final String VISUALIZZA_TUTTI_QUERY = "SELECT impianti.*, utenti.nome AS nome_cliente, utenti.cognome AS cognome_cliente FROM impianti, utenti WHERE impianti.Cliente = utenti.ID";
	private static final String INSERISCI_QUERY = "INSERT INTO `test`.`impianti` (`Nome`, `Cliente`) VALUES (?, ?);";
	private static final String ELIMINA_QUERY = "DELETE FROM `test`.`impianti` WHERE `ID`= ?";
	private static final String CERCA_QUERY = "SELECT impianti.*, utenti.nome AS nome_cliente, utenti.cognome AS cognome_cliente FROM impianti, utenti WHERE impianti.Cliente = utenti.ID AND Cliente = ?";

	private PreparedStatement query = null;

	private static final Logger LOGGER = Logger.getLogger(DaoImpianto.class.getName());

	/**
	 * @see integration.DAO.DAO#inserisci(java.lang.Object)
	 */
	@Override
	public boolean inserisci(Impianto i) {
		Boolean ret = false;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();

			query = conn.prepareStatement(INSERISCI_QUERY);
			query.setString(1, i.getNome());
			query.setString(2, i.getCliente());

			query.execute();
			ret = true;

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Inserimento dell'impianto nel database non riuscito");
		}

		return ret;
	}

	@Override
	public boolean elimina(Impianto i) {
		Boolean ret = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();

			query = conn.prepareStatement(ELIMINA_QUERY);
			// int id = Integer.parseInt(i.getID());
			query.setString(1, i.getID());

			query.execute();
			ret = true;

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Eliminazione dell'impianto nel database non riuscita");
		}

		return ret;
	}

	@Override
	public List<Impianto> visualizzaTutti() {
		List<Impianto> ret = null;
		ResultSet result = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(VISUALIZZA_TUTTI_QUERY);
			result = query.executeQuery();
			ret = creaLista(result);

			conn.close();
			result.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}
		return ret;
	}

	@Override
	public List<Impianto> cerca(String campo, String valore) {
		List<Impianto> ret = null;
		ResultSet result = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(CERCA_QUERY);
			query.setString(1, valore);
			result = query.executeQuery();
			ret = creaLista(result);

			conn.close();
			result.close();
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
	private List<Impianto> creaLista(ResultSet res) {
		List<Impianto> lista = new LinkedList<Impianto>();

		try {

			while (res.next()) {

				String id = res.getString("ID");
				String nome = res.getString("Nome");
				String cliente = res.getString("Cliente");

				Impianto impianto = new Impianto(id, nome, cliente);

				lista.add(impianto);

			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Creazione lista fallita");
		}

		return lista;
	}

	/**
	 * Classe di supporto per collegare un Impianto ad un Utente
	 * 
	 * @author redtr_000
	 *
	 */
	public class Coppia {
		private Coppia(Impianto imp, Utente ute) {
			i = imp;
			u = ute;
			idI = i.getID();
			nomeI = i.getNome();
			idU = u.getID();
			nomeU = u.getNome();
			cognomeU = u.getCognome();
		}

		Impianto i;
		Utente u;
		String idI;
		String nomeI;
		String idU;
		String nomeU;
		String cognomeU;

		public Impianto getI() {
			return i;
		}

		public Utente getU() {
			return u;
		}

		public String getIdI() {
			return idI;
		}

		public String getNomeI() {
			return nomeI;
		}

		public String getIdU() {
			return idU;
		}

		public String getNomeU() {
			return nomeU;
		}

		public String getCognomeU() {
			return cognomeU;
		}
	}

	/**
	 * Crea una lista a partire dal resultset
	 * 
	 * @param res
	 *            risultato della query
	 * @return lista generata
	 */
	private List<Coppia> creaListaC(ResultSet res) {
		List<Coppia> lista = new LinkedList<Coppia>();

		try {

			Impianto impianto;
			Utente utente;
			Coppia c;

			while (res.next()) {

				String ID = res.getString("ID");
				String nome = res.getString("Nome");
				String cliente = res.getString("Cliente");

				String nomeC = res.getString("nome_cliente");
				String cognomeC = res.getString("cognome_cliente");

				impianto = new Impianto(ID, nome, cliente);

				utente = new Utente(cliente, nomeC, cognomeC, "", "", "0");
				c = new Coppia(impianto, utente);

				lista.add(c);

			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Creazione lista non riuscita");
		}

		return lista;
	}

	public List<Coppia> cercaC(String valore) {
		List<Coppia> ret = null;
		ResultSet result = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(CERCA_QUERY);
			query.setString(1, valore);
			result = query.executeQuery();
			ret = creaListaC(result);

			conn.close();
			result.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}

		return ret;
	}

	public List<Coppia> visualizzaTuttiC() {
		List<Coppia> ret = null;
		ResultSet result = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(VISUALIZZA_TUTTI_QUERY);
			result = query.executeQuery();
			ret = creaListaC(result);

			conn.close();
			result.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}

		return ret;
	}

}
