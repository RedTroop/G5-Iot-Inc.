package business.entita;

public class Impianto implements Entita {
	
	public Impianto(String nome, String cliente) {
		this.nome = nome;
		this.cliente = cliente;
	}

	public Impianto(String id, String nome, String cliente) {
		this.id = id;
		this.nome = nome;
		this.cliente = cliente;
	}

	private String id;
	private String nome;
	private String cliente;

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
