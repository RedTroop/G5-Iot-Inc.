package business.servizi;

import java.util.List;

import business.entit�.Entit�;

public interface CRUD<T> extends Entit� {

	boolean inserisci(Object oggetto);

	boolean elimina(Object oggetto);

	List<T> visualizzaTutti();

	List<T> cerca(String campo, String valore);

}
