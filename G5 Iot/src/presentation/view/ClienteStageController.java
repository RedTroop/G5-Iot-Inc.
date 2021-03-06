package presentation.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Classe controller per lo Stage Cliente principale
 * 
 * @author redtr_000
 *
 */
public class ClienteStageController {

	@FXML
	private AnchorPane content;
	@FXML
	private Button homeButton;
	private static final Logger LOGGER = Logger.getLogger(ClienteStageController.class.getName());

	/**
	 * Metodo richiamato prima che lo stage venga caricato
	 */
	@FXML
	public void initialize() {

		AdminStageController.funzione = 1;

		homeButton.setOnAction((event) -> {
			goHome();
		});

		System.out.println("Matteo Lac Impianto!");

		goHome();

	}

	/**
	 * Carica e visualizza la homepage (lista impianti cliente)
	 */
	private void goHome() {
		AnchorPane newLoadedPane;
		System.out.println("Visualizza Impianto!");
		try {
			newLoadedPane = FXMLLoader
					.load(getClass().getClassLoader().getResource("presentation/view/ListaImpianti.fxml"));
			content.getChildren().clear();
			content.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Caricamento homepage cliente fallito");

		}
	}

}
