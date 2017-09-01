package integration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Sensore;
import integration.DBConnector;

/**
 * Classe per la gestione/esecuzione delle query per i dati relativi ai Sensori
 * 
 * @author redtr_000
 *
 */
public class DaoSensore implements DAO<Sensore> {

	private static final String INSERISCI_QUERY = "INSERT INTO sensori (modello, Impianto) VALUES (?,?)";
	private static final String ELIMINA_QUERY = "DELETE FROM sensori WHERE ID = ?";
	private static final String CERCA_QUERY = "SELECT sensori.*, modellisensori.Tipo FROM sensori, modellisensori WHERE sensori.modello = modellisensori.Codice AND impianto = ? AND tipo LIKE ?";
	private PreparedStatement query = null;
	private ResultSet result;

	private static final Logger LOGGER = Logger.getLogger(DaoSensore.class.getName());

	@Override
	public boolean inserisci(Sensore s) {
		boolean ret = false;
		try {
			// oggetto connessione
			Connection conn = DBConnector.getConnector().getConnessione();
			// preparo la query
			query = conn.prepareStatement(INSERISCI_QUERY);
			query.setString(1, s.getModello());
			query.setString(2, s.getImpianto());

			query.execute();
			ret = true;

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Inserimento del sensore nel database non riuscito");
		}
		return ret;
	}

	@Override
	public boolean elimina(Sensore s) {
		boolean ret = false;
		try {
			// oggetto connessione
			Connection conn = DBConnector.getConnector().getConnessione();
			// preparo la query
			query = conn.prepareStatement(ELIMINA_QUERY);
			query.setString(1, s.getID());

			query.execute();
			ret = true;

			conn.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Eliminazione del modello nel database non riuscito");
		}
		return ret;
	}

	@Override
	public List<Sensore> visualizzaTutti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sensore> cerca(String campo, String valore) {
		List<Sensore> ret = null;
		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(CERCA_QUERY);
			query.setString(2, "%" + campo + "%");
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
	private List<Sensore> creaLista(ResultSet res) {
		List<Sensore> lista = new LinkedList<Sensore>();

		try {
			while (res.next()) {

				String id = res.getString("ID");
				String modello = res.getString("modello");
				String impianto = res.getString("Impianto");
				String rilevazione = res.getString("rilevazione");
				String tipo = res.getString("tipo");

				Sensore sens = new Sensore(id, modello, impianto, rilevazione, tipo);

				lista.add(sens);

			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Creazione lista non riuscita");
		}

		return lista;
	}

}
