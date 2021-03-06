package business.servizi;

import java.util.List;

import business.entita.Tipo;
import integration.DAO.DaoTipo;

/**
 * La classe offre servizi per la gestione di un Tipo
 * 
 * @author redtr_000
 *
 */
public class ServizioTipi implements CRUD<Tipo> {
	private final DaoTipo daoTipo = new DaoTipo();

	/**
	 * @see business.servizi.CRUD#inserisci(java.lang.Object)
	 */
	@Override
	public boolean inserisci(Object oggetto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean elimina(Object oggetto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tipo> visualizzaTutti() {
		List<Tipo> ret = daoTipo.visualizzaTutti();
		return ret;
	}

	@Override
	public List<Tipo> cerca(String campo, String valore) {
		// TODO Auto-generated method stub
		return null;
	}

}
