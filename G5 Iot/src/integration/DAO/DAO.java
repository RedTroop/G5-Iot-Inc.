package integration.DAO;

import java.util.List;

public interface DAO<Entità> {

	boolean inserisci(Entità e);
	
	boolean elimina(Entità e);
	
	List<Entità> visualizzaTutti();
	
	List<Entità> cerca(String campo, String valore);
}
