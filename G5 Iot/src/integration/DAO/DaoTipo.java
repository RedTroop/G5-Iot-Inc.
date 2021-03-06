package integration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Tipo;
import integration.DBConnector;

/**
 * Classe per la gestione/esecuzione delle query per i dati relativi ai Tipi
 * 
 * @author redtr_000
 *
 */
public class DaoTipo implements DAO<Tipo> {

	private static final String SELEZIONA_TUTTI_QUERY = "SELECT * FROM tipi";
	private PreparedStatement query = null;
	private ResultSet result;

	private static final Logger LOGGER = Logger.getLogger(DaoTipo.class.getName());

	/**
	 * @see integration.DAO.DAO#inserisci(java.lang.Object)
	 */
	@Override
	public boolean inserisci(Tipo e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean elimina(Tipo e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tipo> visualizzaTutti() {
		List<Tipo> ret = null;
		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(SELEZIONA_TUTTI_QUERY);

			result = query.executeQuery();
			ret = creaLista(result);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Accesso al database non riuscito");
		}

		return ret;
	}

	@Override
	public List<Tipo> cerca(String campo, String valore) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Crea una lista a partire dal resultset
	 * 
	 * @param res
	 *            risultato della query
	 * @return lista generata
	 */
	private List<Tipo> creaLista(ResultSet res) {
		List<Tipo> lista = new LinkedList<Tipo>();

		try {
			while (res.next()) {

				String tipo = res.getString("Tipo");

				Tipo ti = new Tipo(tipo);

				lista.add(ti);

			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Creazione lista non riuscita");
		}

		return lista;
	}

}
