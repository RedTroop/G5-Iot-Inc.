package business.servizi;

import java.util.List;

import business.entità.Entità;

public interface CRUD<T> extends Entità {

	boolean inserisci(Object oggetto);

	boolean elimina(Object oggetto);

	List<T> visualizzaTutti();

	List<T> cerca(String campo, String valore);

}
