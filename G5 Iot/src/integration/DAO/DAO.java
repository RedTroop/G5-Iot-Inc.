package integration.DAO;

import java.util.List;

public interface DAO<Entita> {

	/**
	 * Inserisce entit� nel database
	 * 
	 * @param e
	 *            entit� da inserire
	 * @return true se inserimento riuscito, false se inserimento fallito
	 */
	boolean inserisci(Entita e);

	/**
	 * Elimina entit� dal database
	 * 
	 * @param e
	 *            entit� da rimuovere
	 * @return true se eliminazione riuscita, false se eliminazione fallita
	 */
	boolean elimina(Entita e);

	/**
	 * Restituisce tutte le entit� presenti nel database
	 * 
	 * @return lista contenente le entit�
	 */
	List<Entita> visualizzaTutti();

	/**
	 * @param campo
	 * @param valore
	 * @return
	 */
	List<Entita> cerca(String campo, String valore);
}
