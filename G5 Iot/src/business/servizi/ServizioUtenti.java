package business.servizi;

import java.util.List;

import business.entita.Utente;
import integration.DAO.DaoUtente;

/**
 * La classe offre servizi per la gestione di un Utente
 * 
 * @author redtr_000
 *
 */
public class ServizioUtenti implements CRUD<Utente> {
	private final DaoUtente daoUtente = new DaoUtente();

	@Override
	public boolean inserisci(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Utente) {
			Utente usr = (Utente) oggetto;
			ret = daoUtente.inserisci(usr);
		}
		return ret;

	}

	@Override
	public boolean elimina(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Utente) {
			Utente usr = (Utente) oggetto;
			ret = daoUtente.elimina(usr);
		}
		return ret;
	}

	@Override
	public List<Utente> visualizzaTutti() {
		List<Utente> ret = daoUtente.visualizzaTutti();
		return ret;
	}

	@Override
	public List<Utente> cerca(String campo, String valore) {
		List<Utente> ret = daoUtente.cerca(campo, valore);
		return ret;
	}

	public Utente controlloIdentita(String email, String pwd) {
		Utente ret = null;
		List<Utente> listaUtenti = daoUtente.cerca("email", email);
		for (Utente usrTemp : listaUtenti) {
			if (usrTemp.getPassword().equals(pwd)) {
				ret = usrTemp;
			}
		}

		return ret;

	}

}
