package presentation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe di avvio contenente il "main"
 * 
 * @author redtr_000
 *
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		primaryStage.setTitle("G5 Iot Inc.");
		primaryStage.setResizable(false);

		initRootLayout();

		showLogin();
	}

	/**
	 * Imposta lo stage principale e lo mostra
	 */
	private void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Caricamento stage fallito");
		}
	}

	/**
	 * Shows the login inside the root layout.
	 */
	public void showLogin() {
		try {
			// Load login.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
			AnchorPane login = (AnchorPane) loader.load();

			// Set login into the center of root layout.
			rootLayout.setCenter(login);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Caricamento frame login fallito");
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
