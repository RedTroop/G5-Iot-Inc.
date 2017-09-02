package business.servizi;

import java.util.List;

import business.entita.Sensore;
import integration.DAO.DaoSensore;

/**
 * La classe offre servizi per la gestione di un Servizio
 * 
 * @author redtr_000
 *
 */
public class ServizioSensori implements CRUD<Sensore> {

	private final static String ERRORE = "ERR";
	private final static String ANOMALIA = "ANM";
	private final static String SUCCESSO = "SUC";

	private final static String ERR_TRASMISSIONE = "403";
	private final static String ERR_MALFUNZIONAMENTO = "402";

	private final static String MARCA_MANDRIOTA = "MA";
	private final static String MARCA_SET = "SE";
	private final static String MARCA_UVA = "US";

	private final static String TEMPERATURA = "T";
	private final static String UMIDITA = "U";
	private final static String PRESSIONE = "P";

	private final static String GRADI = "°";
	private final static String PERCENTUALE = "%";
	private final static String ETTOPASCAL = " hPa";

	private final static int CHAR_DESCRIZIONE = 3; // ultimi 3 caratteri della
													// rilevazione
	private final static int CHAR_CODICE = 5; // primi 5 caratteri della
												// rilevazione
	private final static int CHAR_MARCA = 3; // caratteri dal 3 al 5 della
												// rilevazione
	private final static int CHAR_INIZIO = 0; // primo carattere della
												// rilevazione
	private final static int CHAR_TIPO = 1; // primi 2 caratteri della
											// rilevazione

	private final DaoSensore daoSensore = new DaoSensore();

	@Override
	public boolean inserisci(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Sensore) {
			Sensore sens = (Sensore) oggetto;
			ret = daoSensore.inserisci(sens);
		}
		return ret;
	}

	@Override
	public boolean elimina(Object oggetto) {
		boolean ret = false;
		if (oggetto instanceof Sensore) {
			Sensore sens = (Sensore) oggetto;
			ret = daoSensore.elimina(sens);
		}
		return ret;
	}

	@Override
	public List<Sensore> visualizzaTutti() {
		List<Sensore> ret = daoSensore.visualizzaTutti();
		return ret;

	}

	@Override
	public List<Sensore> cerca(String campo, String valore) {
		List<Sensore> ret = daoSensore.cerca(campo, valore);
		for (Sensore s : ret) {
			String ril = s.getRilevazione();
			ril = formattaString(ril);
			s.setRilevazione(ril);
		}
		return ret;

	}

	/**
	 * Traduce le rilevazioni nello standard definito
	 * 
	 * @param rilevazione
	 *            stringa da tradurre
	 * @return stringa tradotta
	 */
	private String formattaString(String rilevazione) {

		String format = "ERRORE";
		if (rilevazione.length() > CHAR_DESCRIZIONE) {
			String descr = rilevazione.substring(rilevazione.length() - CHAR_DESCRIZIONE);
			if (descr.equalsIgnoreCase(ERRORE)) {
				rilevazione = rilevazione.substring(CHAR_CODICE, rilevazione.length() - CHAR_DESCRIZIONE);
				if (rilevazione.equalsIgnoreCase(ERR_TRASMISSIONE))
					format = " ---  Errore di trasmissione!  --- ";
				else if (rilevazione.equalsIgnoreCase(ERR_MALFUNZIONAMENTO))
					format = " ---  Malfunzionamento sensore!  --- ";
			} else if (descr.equalsIgnoreCase(SUCCESSO)) {
				String marca = rilevazione.substring(CHAR_MARCA, CHAR_CODICE);
				if (marca.equalsIgnoreCase(MARCA_MANDRIOTA)) {
					format = formattaMA(rilevazione);
				} else if (marca.equalsIgnoreCase(MARCA_SET)) {
					format = formattaSE(rilevazione);
				} else if (marca.equalsIgnoreCase(MARCA_UVA)) {
					format = formattaUS(rilevazione);
				} else
					format = "MARCA INESISTENTE";
			} else if (descr.equalsIgnoreCase(ANOMALIA)) {
				format = format + "  <!!!>";
			}
		}
		return format;
	}

	/**
	 * Traduce la rilevazione secondo gli standard definiti dall'azienda
	 * Mandriota Sensori
	 * 
	 * @param rilevazione
	 *            stringa da tradurre
	 * @return stringa tradotta
	 */
	private String formattaMA(String rilevazione) {

		String format = "";

		String tipo = rilevazione.substring(CHAR_INIZIO, CHAR_TIPO);
		String decimale = rilevazione.substring(CHAR_CODICE, rilevazione.length() - CHAR_DESCRIZIONE);

		System.out.println("rilevazi: " + rilevazione);
		System.out.println("decimale: " + decimale);

		String ore = "";
		String min = "";
		String anno = "";
		String mese = "";
		String giorno = "";
		String segno = "";
		String valore = "";
		String misura = "";

		final int INIZIO = 0;
		final int FINE_ORE = 2;
		final int FINE_MINUTI = 4;
		final int FINE_ANNO = 8;
		final int FINE_MESE = 10;
		final int FINE_GIORNO = 12;
		final int SEGNO = 1;
		final int FINE_VALORE = 12;

		if (tipo.equalsIgnoreCase(TEMPERATURA)) {
			ore = decimale.substring(INIZIO, FINE_ORE);
			min = decimale.substring(FINE_ORE, FINE_MINUTI);
			anno = decimale.substring(FINE_MINUTI, FINE_ANNO);
			mese = decimale.substring(FINE_ANNO, FINE_MESE);
			giorno = decimale.substring(FINE_MESE, FINE_GIORNO);
			segno = decimale.substring(FINE_GIORNO, FINE_GIORNO + SEGNO);
			valore = decimale.substring(FINE_VALORE + SEGNO);
			misura = GRADI;
		} else if (tipo.equalsIgnoreCase(UMIDITA)) {
			ore = decimale.substring(INIZIO, FINE_ORE);
			min = decimale.substring(FINE_ORE, FINE_MINUTI);
			anno = decimale.substring(FINE_MINUTI, FINE_ANNO);
			mese = decimale.substring(FINE_ANNO, FINE_MESE);
			giorno = decimale.substring(FINE_MESE, FINE_GIORNO);
			valore = decimale.substring(FINE_VALORE);
			misura = PERCENTUALE;
		}

		format = ordina(ore, min, giorno, mese, anno, segno, valore, misura);

		return format;
	}

	private String formattaSE(String rilevazione) {
		String format = "";

		String tipo = rilevazione.substring(CHAR_INIZIO, CHAR_TIPO);
		String decimale = rilevazione.substring(CHAR_CODICE, rilevazione.length() - CHAR_DESCRIZIONE);

		String ore = "";
		String min = "";
		String anno = "";
		String mese = "";
		String giorno = "";
		String segno = "";
		String valore = "";
		String misura = "";

		final int P_INIZIO = 0;
		final int P_FINE_ORE = 15;
		final int P_FINE_MINUTI = 15;
		final int P_FINE_ANNO = 13;
		final int P_FINE_MESE = 7;
		final int P_FINE_GIORNO = 9;
		final int P_FINE_VALORE = 5;

		final int U_INIZIO = 0;
		final int U_FINE_ORE = 13;
		final int U_FINE_MINUTI = 13;
		final int U_FINE_ANNO = 11;
		final int U_FINE_MESE = 5;
		final int U_FINE_GIORNO = 7;
		final int U_FINE_VALORE = 3;

		if (tipo.equalsIgnoreCase(PRESSIONE)) {
			ore = decimale.substring(P_FINE_ANNO, P_FINE_ORE);
			min = decimale.substring(P_FINE_MINUTI);
			anno = decimale.substring(P_FINE_GIORNO, P_FINE_ANNO);
			mese = decimale.substring(P_FINE_VALORE, P_FINE_MESE);
			giorno = decimale.substring(P_FINE_MESE, P_FINE_GIORNO);
			valore = decimale.substring(P_INIZIO, P_FINE_VALORE);
			misura = ETTOPASCAL;

		} else if (tipo.equalsIgnoreCase(UMIDITA)) {
			ore = decimale.substring(U_FINE_ANNO, U_FINE_ORE);
			min = decimale.substring(U_FINE_MINUTI);
			anno = decimale.substring(U_FINE_GIORNO, U_FINE_ANNO);
			mese = decimale.substring(U_FINE_VALORE, U_FINE_MESE);
			giorno = decimale.substring(U_FINE_MESE, U_FINE_GIORNO);
			valore = decimale.substring(U_INIZIO, U_FINE_VALORE);
			misura = PERCENTUALE;
		}

		format = ordina(ore, min, giorno, mese, anno, segno, valore, misura);

		return format;
	}

	private String formattaUS(String rilevazione) {
		String format = "";

		String tipo = rilevazione.substring(CHAR_INIZIO, CHAR_TIPO);
		String decimale = rilevazione.substring(CHAR_CODICE, rilevazione.length() - CHAR_DESCRIZIONE);

		String ore = "";
		String min = "";
		String anno = "";
		String mese = "";
		String giorno = "";
		String segno = "";
		String valore = "";
		String misura = "";

		final int P_INIZIO = 0;
		final int P_FINE_ORE = 15;
		final int P_FINE_MINUTI = 15;
		final int P_FINE_ANNO = 8;
		final int P_FINE_MESE = 4;
		final int P_FINE_GIORNO = 2;
		final int P_FINE_VALORE = 13;

		final int T_INIZIO = 0;
		final int T_FINE_ORE = 14;
		final int T_FINE_MINUTI = 14;
		final int T_FINE_ANNO = 8;
		final int T_FINE_MESE = 4;
		final int T_FINE_GIORNO = 2;
		final int T_FINE_VALORE = 12;
		final int T_SEGNO = 1;

		if (tipo.equalsIgnoreCase(PRESSIONE)) {
			ore = decimale.substring(P_FINE_VALORE, P_FINE_ORE);
			min = decimale.substring(P_FINE_MINUTI);
			anno = decimale.substring(P_FINE_MESE, P_FINE_ANNO);
			mese = decimale.substring(P_FINE_GIORNO, P_FINE_MESE);
			giorno = decimale.substring(P_INIZIO, P_FINE_GIORNO);
			valore = decimale.substring(P_FINE_ANNO, P_FINE_VALORE);
			misura = ETTOPASCAL;

		} else if (tipo.equalsIgnoreCase(TEMPERATURA)) {
			ore = decimale.substring(T_FINE_VALORE, T_FINE_ORE);
			min = decimale.substring(T_FINE_MINUTI);
			anno = decimale.substring(T_FINE_MESE, T_FINE_ANNO);
			mese = decimale.substring(T_FINE_GIORNO, T_FINE_MESE);
			giorno = decimale.substring(T_INIZIO, T_FINE_GIORNO);
			segno = decimale.substring(T_FINE_ANNO, T_FINE_ANNO + T_SEGNO);
			valore = decimale.substring(T_FINE_ANNO + T_SEGNO, T_FINE_VALORE);
			misura = GRADI;
		}

		format = ordina(ore, min, giorno, mese, anno, segno, valore, misura);

		return format;
	}

	private String ordina(String ore, String min, String giorno, String mese, String anno, String segno, String valore,
			String misura) {
		String toReturn = "";

		if (segno.equalsIgnoreCase("1"))
			segno = "+";
		else if (segno.equalsIgnoreCase("0"))
			segno = "-";

		int val = Integer.parseInt(valore);

		toReturn = ore + ":" + min + "   |   " + giorno + "/" + mese + "/" + anno + "   |   " + segno + val + misura;

		return toReturn;
	}

}
