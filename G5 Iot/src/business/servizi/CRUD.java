package business.servizi;

import java.util.List;

import business.entita.Entita;

public interface CRUD<T> extends Entita {

	boolean inserisci(Object oggetto);

	boolean elimina(Object oggetto);

	List<T> visualizzaTutti();

	List<T> cerca(String campo, String valore);

}
