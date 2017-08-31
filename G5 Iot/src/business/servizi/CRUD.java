package business.servizi;

import java.util.List;

import business.entita.Entita;

/**
 * Interfaccia per la tavola CRUD (Create, Read, Update, Delete) + Cerca
 * 
 * @author redtr_000
 *
 */
public interface CRUD<T extends Entita> {

	/**
	 * Inserisce entit� nel database
	 * 
	 * @param oggetto
	 *            entit� da inserire
	 * @return true se inserimento riuscito, false se inserimento fallito
	 */
	boolean inserisci(Object oggetto);

	/**
	 * Elimina entit� dal database
	 * 
	 * @param oggetto
	 *            entit� da rimuovere
	 * @return true se eliminazione riuscita, false se eliminazione fallita
	 */
	boolean elimina(Object oggetto);

	/**
	 * Restituisce tutte le entit� presenti nel database
	 * 
	 * @return lista contenente le entit�
	 */
	List<T> visualizzaTutti();

	/**
	 * @param campo
	 * @param valore
	 * @return
	 */
	List<T> cerca(String campo, String valore);

}
