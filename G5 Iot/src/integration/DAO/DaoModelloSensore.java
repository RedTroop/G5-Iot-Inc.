package integration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import business.entita.ModelloSensore;
import integration.DBConnector;

public class DaoModelloSensore implements DAO<ModelloSensore> {

	private static final String VISUALIZZA_TUTTI_QUERY = "SELECT * FROM modellisensori WHERE disponibilit� = 1";
	private static final String INSERISCI_QUERY = "INSERT INTO modellisensori (Codice, Tipo, Marca) VALUE (?, ?, ?)";
	private static final String ELIMINA_QUERY = "UPDATE modellisensori SET disponibilit� = 0 WHERE Codice = ?";
	private static final String CERCA_QUERY = "SELECT * FROM modellisensori WHERE Tipo = ? AND disponibilit� = 1";

	private PreparedStatement query = null;

	@Override
	public boolean inserisci(ModelloSensore m) {
		Boolean ret = false;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();

			query = conn.prepareStatement(INSERISCI_QUERY);
			query.setString(1, m.getCodice());
			query.setString(2, m.getTipo());
			query.setString(3, m.getMarca());

			query.execute();
			ret = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public boolean elimina(ModelloSensore m) {
		Boolean ret = false;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();

			query = conn.prepareStatement(ELIMINA_QUERY);
			query.setString(1, m.getCodice());

			query.execute();
			ret = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<ModelloSensore> visualizzaTutti() {
		List<ModelloSensore> ret = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(VISUALIZZA_TUTTI_QUERY);

			ret = creaLista(query.executeQuery());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<ModelloSensore> cerca(String campo, String valore) {
		List<ModelloSensore> ret = null;

		try {
			Connection conn = DBConnector.getConnector().getConnessione();
			query = conn.prepareStatement(CERCA_QUERY);
			query.setString(1, valore);
			ret = creaLista(query.executeQuery());

		} catch (SQLException e) {
			e.printStackTrace();
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
	private List<ModelloSensore> creaLista(ResultSet res) {
		List<ModelloSensore> lista = new LinkedList<ModelloSensore>();

		try {
			while (res.next()) {

				String codice = res.getString("Codice");
				String tipo = res.getString("Tipo");
				String marca = res.getString("Marca");

				ModelloSensore modello = new ModelloSensore(codice, tipo, marca);

				lista.add(modello);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
