package business.servizi;

import java.util.List;

import business.entita.Impianto;
import integration.DAO.DaoImpianto;
import integration.DAO.DaoImpianto.Coppia;

/**
 * La classe offre servizi per la gestione di un Impianto
 * 
 * @author redtr_000
 *
 */
public class ServizioImpianti implements CRUD<Impianto> {
	private final DaoImpianto daoImpianto = new DaoImpianto();

	/**
	 * @see business.servizi.CRUD#inserisci(java.lang.Object)
	 */
	@Override
	public boolean inserisci(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Impianto) {
			Impianto imp = (Impianto) oggetto;
			ret = daoImpianto.inserisci(imp);
		}
		return ret;
	}

	/**
	 * @see business.servizi.CRUD#elimina(java.lang.Object)
	 */
	@Override
	public boolean elimina(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Impianto) {
			Impianto imp = (Impianto) oggetto;
			ret = daoImpianto.elimina(imp);
		}
		return ret;
	}

	/**
	 * @see business.servizi.CRUD#visualizzaTutti()
	 */
	@Override
	public List<Impianto> visualizzaTutti() {
		List<Impianto> ret = daoImpianto.visualizzaTutti();
		return ret;
	}

	/**
	 * @see business.servizi.CRUD#cerca(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Impianto> cerca(String campo, String valore) {
		List<Impianto> ret = daoImpianto.cerca(campo, valore);
		return ret;
	}

	/**
	 * Cerca per la classe di supporto Coppia
	 * 
	 * @param valore
	 *            coppia da cercare
	 * @return coppia trovata
	 */
	public List<Coppia> cercaC(String valore) {
		List<Coppia> ret = daoImpianto.cercaC(valore);
		return ret;
	}

	/**
	 * Visualizza Tutti per la classe di supporto Coppia
	 * 
	 * @return lista delle coppie da visualizzare
	 */
	public List<Coppia> visualizzaTuttiC() {
		List<Coppia> ret = daoImpianto.visualizzaTuttiC();
		return ret;
	}

}
