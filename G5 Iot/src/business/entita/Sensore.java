package business.entita;

/**
 * Classe entità per Sensore
 * 
 * @author redtr_000
 *
 */
public class Sensore implements Entita {

	/**
	 * Costruttore di classe
	 * 
	 * @param id
	 *            identificativo del sensore
	 * @param modello
	 *            identificativo del modello
	 * @param impianto
	 *            id dell'impianto in cui si trova il sensore
	 * @param rilevazione
	 *            valore, unità di misura, ora e data della rilevazione
	 * @param tipo
	 *            grandezza misurata
	 */
	public Sensore(String id, String modello, String impianto, String rilevazione, String tipo) {
		this.id = id;
		this.modello = modello;
		this.impianto = impianto;
		this.rilevazione = rilevazione;
		this.tipo = tipo;
	}

	public Sensore(String modello, String impianto, String tipo) {
		this("", modello, impianto, "", tipo);
	}

	private String id;
	private String modello;
	private String impianto;
	private String rilevazione;
	private String tipo;

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getImpianto() {
		return impianto;
	}

	public void setImpianto(String impianto) {
		this.impianto = impianto;
	}

	public String getRilevazione() {
		return rilevazione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setRilevazione(String rilevazione) {
		this.rilevazione = rilevazione;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
