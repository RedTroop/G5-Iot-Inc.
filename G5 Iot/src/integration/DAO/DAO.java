package integration.DAO;

import java.util.List;

/**
 * Interfaccia per pattern DAO (Data Access Object)
 * 
 * @author redtr_000
 *
 * @param <Entita>
 */
public interface DAO<Entita> {

	/**
	 * Inserisce entità nel database
	 * 
	 * @param e
	 *            entità da inserire
	 * @return true se inserimento riuscito, false se inserimento fallito
	 */
	boolean inserisci(Entita e);

	/**
	 * Elimina entità dal database
	 * 
	 * @param e
	 *            entità da rimuovere
	 * @return true se eliminazione riuscita, false se eliminazione fallita
	 */
	boolean elimina(Entita e);

	/**
	 * Restituisce tutte le entità presenti nel database
	 * 
	 * @return lista contenente le entità
	 */
	List<Entita> visualizzaTutti();

	/**
	 * @param campo
	 * @param valore
	 * @return
	 */
	List<Entita> cerca(String campo, String valore);
}
