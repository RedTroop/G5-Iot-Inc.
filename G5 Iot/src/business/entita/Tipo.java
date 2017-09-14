package business.entita;

/**
 * Classe entità per Tipo
 * 
 * @author redtr_000
 *
 */
public class Tipo implements Entita {

	/**
	 * Costruttore di classe
	 * 
	 * @param tipo
	 *            grandezza misurata
	 */
	public Tipo(String tipo) {
		this.tipo = tipo;
	}

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
