package business.servizi;

import business.entita.Utente;

public class ServizioLogin {
	private static final String MENOUNO = "-1";
	//private static final ServizioLogin ISTANZA = new ServizioLogin();
	private static Utente utenteLoggato = new Utente(MENOUNO, "", "", "", "", "0");

	// costrutture privato
	private ServizioLogin() {
	}

	public static Utente getUtenteLoggato() {
		return utenteLoggato;
	}

	public static boolean effettuaLogin(String email, String pwd) {
		boolean ret = false;

		ServizioUtenti serv = new ServizioUtenti();
		Utente utenteTemp = serv.controlloIdentità(email, pwd);
		if (utenteTemp != null) {
			utenteLoggato = utenteTemp;

			System.out.println(utenteLoggato.getEmail() + " " + utenteLoggato.getPassword() + " " + utenteLoggato.getAdmin());
			
			ret = true;
		} else {
			utenteLoggato = new Utente(MENOUNO, "", "", "", "", "0");
		}

		return ret;
	}
}
