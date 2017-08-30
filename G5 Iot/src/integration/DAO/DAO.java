package integration.DAO;

import java.util.List;

public interface DAO<Entit�> {

	boolean inserisci(Entit� e);
	
	boolean elimina(Entit� e);
	
	List<Entit�> visualizzaTutti();
	
	List<Entit�> cerca(String campo, String valore);
}
