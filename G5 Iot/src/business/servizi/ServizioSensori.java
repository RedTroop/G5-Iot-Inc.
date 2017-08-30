package business.servizi;

import java.util.List;

import business.entita.Sensore;
import integration.DAO.DaoSensore;

public class ServizioSensori implements CRUD<Sensore> {

	final static String ERRORE = "ERR";
	final static String ANOMALIA = "ANM";
	final static String SUCCESSO = "SUC";

	final static String ERR_TRASMISSIONE = "403";
	final static String ERR_MALFUNZIONAMENTO = "402";

	final static String MARCA_MANDRIOTA = "MA";
	final static String MARCA_SET = "SE";
	final static String MARCA_UVA = "US";

	final static String TEMPERATURA = "T";
	final static String UMIDITA = "U";
	final static String PRESSIONE = "P";

	final static String GRADI = "°";

	private DaoSensore daoSensore = new DaoSensore();

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

	private String formattaString(String rilevazione) {
		// char[] ril = rilevazione.toCharArray();
		
		String format = null;
		if (rilevazione.length() > 3) {
			String descr = rilevazione.substring(rilevazione.length() - 3);
			if (descr.equalsIgnoreCase(ERRORE)) {
				rilevazione = rilevazione.substring(5, rilevazione.length() - 3);
				if (rilevazione.equalsIgnoreCase(ERR_TRASMISSIONE))
					format = " ---  Errore di trasmissione!  --- ";
				else if (rilevazione.equalsIgnoreCase(ERR_MALFUNZIONAMENTO))
					format = " ---  Malfunzionamento sensore!  --- ";
			} else {
				String marca = rilevazione.substring(3, 5);
				if (marca.equalsIgnoreCase(MARCA_MANDRIOTA)) {
					format = formattaMA(rilevazione);
				} else if (marca.equalsIgnoreCase(MARCA_SET)) {
					format = formattaSE(rilevazione);
				} else if (marca.equalsIgnoreCase(MARCA_UVA)) {
					format = formattaUS(rilevazione);
				} else
					format = "MARCA INESISTENTE";
			}

			if (descr.equalsIgnoreCase(ANOMALIA)) {
				format = format + "  <!!!>";
			}
		}
		return format;
	}

	private String formattaMA(String rilevazione) {
		String format = "";

		String tipo = rilevazione.substring(0, 1);
		String decimale = rilevazione.substring(5, rilevazione.length() - 3);

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

		if (tipo.equalsIgnoreCase(TEMPERATURA)) {
			ore = decimale.substring(0, 2);
			min = decimale.substring(2, 4);
			anno = decimale.substring(4, 8);
			mese = decimale.substring(8, 10);
			giorno = decimale.substring(10, 12);
			segno = decimale.substring(12, 13);
			valore = decimale.substring(13);
			misura = "°";
		} else if (tipo.equalsIgnoreCase(UMIDITA)) {
			ore = decimale.substring(0, 2);
			min = decimale.substring(2, 4);
			anno = decimale.substring(4, 8);
			mese = decimale.substring(8, 10);
			giorno = decimale.substring(10, 12);
			valore = decimale.substring(12);
			misura = "%";
		}

		format = ordina(ore, min, giorno, mese, anno, segno, valore, misura);

		return format;
	}

	private String formattaSE(String rilevazione) {
		String format = "";

		String tipo = rilevazione.substring(0, 1);
		String decimale = rilevazione.substring(5, rilevazione.length() - 3);

		String ore = "";
		String min = "";
		String anno = "";
		String mese = "";
		String giorno = "";
		String segno = "";
		String valore = "";

		String misura = "";

		if (tipo.equalsIgnoreCase(PRESSIONE)) {
			ore = decimale.substring(13, 15);
			min = decimale.substring(15);
			anno = decimale.substring(9, 13);
			mese = decimale.substring(5, 7);
			giorno = decimale.substring(7, 9);
			valore = decimale.substring(0, 5);
			misura = " hPa";

		} else if (tipo.equalsIgnoreCase(UMIDITA)) {
			ore = decimale.substring(11, 13);
			min = decimale.substring(13);
			anno = decimale.substring(7, 11);
			mese = decimale.substring(3, 5);
			giorno = decimale.substring(5, 7);
			valore = decimale.substring(0, 3);
			misura = "%";
		}

		format = ordina(ore, min, giorno, mese, anno, segno, valore, misura);

		return format;
	}

	private String formattaUS(String rilevazione) {
		String format = "";

		String tipo = rilevazione.substring(0, 1);
		String decimale = rilevazione.substring(5, rilevazione.length() - 3);

		String ore = "";
		String min = "";
		String anno = "";
		String mese = "";
		String giorno = "";
		String segno = "";
		String valore = "";

		String misura = "";

		if (tipo.equalsIgnoreCase(PRESSIONE)) {
			ore = decimale.substring(13, 15);
			min = decimale.substring(15);
			anno = decimale.substring(4, 8);
			mese = decimale.substring(2, 4);
			giorno = decimale.substring(0, 2);
			valore = decimale.substring(8, 13);
			misura = " hPa";

		} else if (tipo.equalsIgnoreCase(TEMPERATURA)) {
			ore = decimale.substring(12, 14);
			min = decimale.substring(14);
			anno = decimale.substring(4, 8);
			mese = decimale.substring(2, 4);
			giorno = decimale.substring(0, 2);
			segno = decimale.substring(8, 9);
			valore = decimale.substring(9, 12);
			misura = "°";
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
