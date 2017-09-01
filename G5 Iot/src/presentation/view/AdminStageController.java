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
/**
 * @author redtr_000
 *
 */
public class AdminStageController {

	/**
	 * Costante contenente URL del frame Nuovo Cliente
	 */
	public static final String NUOVO_CLIENTE = "presentation/view/NuovoCliente.fxml";

	/**
	 * Costante contenente URL del frame Lista Sensori
	 */
	public static final String LISTA_SENSORI = "presentation/view/ListaSensori.fxml";

	/**
	 * Costante contenente URL del frame Lista Impianti
	 */
	public static final String LISTA_IMPIANTI = "presentation/view/ListaImpianti.fxml";

	/**
	 * Costante contenente URL del frame Nuovo Impianto
	 */
	public static final String NUOVO_IMPIANTO = "presentation/view/NuovoImpianto.fxml";

	/**
	 * Costante contenente URL del frame Lista Cliente
	 */
	public static final String LISTA_CLIENTI = "presentation/view/ListaClienti.fxml";

	/**
	 * Costante contenente URL del frame Modifica Impianto
	 */
	public static final String MODIFICA_IMPIANTO = "presentation/view/ListaSensoriModifica.fxml";

	/**
	 * Costante contenente URL del frame Aggiorna Database
	 */
	public static final String AGGIORNA_DB = "presentation/view/ListaModelli.fxml";

	/**
	 * Variabile utilizzata per memorizzare la funzione selezionata dall'utente
	 * <p>
	 * 
	 * 1: Mostra Sensori <br>
	 * 2: Crea Impianto <br>
	 * 3: Elimina Impianto <br>
	 * 4: Modifica Impianto
	 */
	protected static int funzione = 0;

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