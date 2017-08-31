package presentation.view;

import business.entita.Impianto;
import business.entita.Utente;
import business.servizi.ServizioImpianti;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe controller per il frame di inserimento nuovo impianto
 * 
 * @author redtr_000
 *
 */
public class NuovoImpiantoController {

	@FXML
	private Button okButton;
	@FXML
	private TextField nomeField;

	ServizioImpianti servizioI = new ServizioImpianti();

	/**
	 * Inizializza le azioni relative ai pulsanti e riempie la tableview
	 */
	@FXML
	private void initialize() {

		okButton.setDisable(true);
		nomeField.requestFocus();

		nomeField.setOnKeyReleased((event) -> {
			if (nomeField.getText().isEmpty()) {
				okButton.setDisable(true);
			} else
				okButton.setDisable(false);

		});

		okButton.setOnAction((event) -> {

			Utente u = ListaClientiController.utenteSelezionato;

			Impianto newImpianto = new Impianto(nomeField.getText(), u.getID());

			boolean result = servizioI.inserisci(newImpianto);

			String aContent = "";
			Alert alert;

			if (result) {
				aContent = "Inserimento nuovo impianto per il cliente selezionato avvenuto con successo!";
				alert = new Alert(AlertType.INFORMATION);
				nomeField.clear();
			} else {
				aContent = "Inserimento nuovo impianto fallito! \nNome impianto già presente nel sistema.";
				alert = new Alert(AlertType.ERROR);
				nomeField.requestFocus();
			}

			alert.setTitle("Avviso Inserimento");
			alert.setHeaderText(null);
			alert.setContentText(aContent);
			alert.showAndWait();
		});
	}
}
