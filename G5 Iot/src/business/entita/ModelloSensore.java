package business.entita;

/**
 * Classe entità per Modello Sensore
 * 
 * @author redtr_000
 *
 */
public class ModelloSensore implements Entita {
	public ModelloSensore(String codice, String tipo, String marca) {
		super();
		this.codice = codice;
		this.tipo = tipo;
		this.marca = marca;
	}

	private String codice;

	/*
	 * si poteva creare la vaRIABILE che poteva assumere solo 3 valori ma non
	 * ricordo il nome
	 */
	private String tipo;
	private String marca;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
