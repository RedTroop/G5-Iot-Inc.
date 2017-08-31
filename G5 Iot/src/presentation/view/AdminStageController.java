package presentation.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Classe controller per lo Stage Admin principale
 * 
 * @author redtr_000
 *
 */
public class AdminStageController {

	public static final String NUOVO_CLIENTE = "presentation/view/NuovoCliente.fxml";
	public static final String LISTA_SENSORI = "presentation/view/ListaSensori.fxml";
	public static final String LISTA_IMPIANTI = "presentation/view/ListaImpianti.fxml";
	public static final String NUOVO_IMPIANTO = "presentation/view/NuovoImpianto.fxml";
	public static final String LISTA_CLIENTI = "presentation/view/ListaClienti.fxml";
	public static final String MODIFICA_IMPIANTO = "presentation/view/ListaSensoriModifica.fxml";
	public static final String AGGIORNA_DB = "presentation/view/ListaModelli.fxml";

	protected static int funzione = 0; // 1 se mostra sensori, 2 se crea
										// impianto, 3 se
	// elimina impianto, 4 modifica impianto

	@FXML
	private AnchorPane content;
	@FXML
	private Button homeButton;
	@FXML
	private Button nuovoClienteButt;
	@FXML
	private Button nuovoImpiantoButt;
	@FXML
	private Button eliminaImpiantoButt;
	@FXML
	private Button modificaImpiantoButt;
	@FXML
	private Button aggiornaDBButt;

	AnchorPane newLoadedPane;
	private static final Logger LOGGER = Logger.getLogger(AdminStageController.class.getName());

	@FXML
	public void initialize() {

		homeButton.setOnAction((event) -> {
			funzione = 1;
			goScene(LISTA_IMPIANTI);
		});

		nuovoClienteButt.setOnAction((event) -> {
			goScene(NUOVO_CLIENTE);
		});

		nuovoImpiantoButt.setOnAction((event) -> {
			funzione = 2;
			goScene(LISTA_CLIENTI);
		});

		eliminaImpiantoButt.setOnAction((event) -> {
			funzione = 3;
			goScene(LISTA_CLIENTI);
		});

		modificaImpiantoButt.setOnAction((event) -> {
			funzione = 4;
			goScene(LISTA_CLIENTI);
		});

		aggiornaDBButt.setOnAction((event) -> {
			goScene(AGGIORNA_DB);
		});

	}

	public void goScene(String scene) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource(scene));
			content.getChildren().clear();
			content.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Caricamento pagina admin fallito");

		}
	}

}