package presentation.view;

import java.io.IOException;

import business.servizi.ServizioLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button loginButt;
	@FXML
	private AnchorPane content;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;

	@FXML
	public void initialize() {
		loginButt.setOnAction((event) -> {
			handleClick(event);
		});

		loginButt.setTooltip(new Tooltip("Inserisci email e password!"));
	}

	/**
	 * Called when the user clicks on the login button.
	 */
	@FXML
	private void handleClick(ActionEvent event) {
		System.out.println("Login: " + email.getText() + ", " + password.getText());
		boolean isLogin = verificaLogin(email.getText(), password.getText());

		boolean admin = ServizioLogin.getUtenteLoggato().getAdmin().equals("1");

		// DBConnector test = DBConnector.getConnector();

		Parent root;

		if (isLogin)
			if (!admin) {
				try {
					root = FXMLLoader
							.load(getClass().getClassLoader().getResource("presentation/view/ClienteStage.fxml"));
					Stage stage = new Stage();
					stage.setTitle("G5 Iot Inc.");
					stage.setScene(new Scene(root));
					stage.setResizable(false);

					stage.show();
					// Hide this current window (if this is what you want)
					((Node) (event.getSource())).getScene().getWindow().hide();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					root = FXMLLoader
							.load(getClass().getClassLoader().getResource("presentation/view/AdminStage.fxml"));
					Stage stage = new Stage();
					stage.setTitle("G5 Iot Inc.");
					stage.setScene(new Scene(root));
					stage.setResizable(false);

					stage.show();
					// Hide this current window (if this is what you want)
					((Node) (event.getSource())).getScene().getWindow().hide();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

	}

	private boolean verificaLogin(String email, String pass) {

		boolean resLogin = ServizioLogin.effettuaLogin(email, pass);

		Alert alert;
		String aTitle = "Avviso Login";
		String aContent = "";

		if (resLogin) {
			
			aContent = "Login avvenuto con successo!";
			alert = new Alert(AlertType.INFORMATION);

		} else {

			aContent = "Login fallito! \nEmail e/o password errati.";
			alert = new Alert(AlertType.ERROR);

			this.email.requestFocus();
		}

		alert.setTitle(aTitle);
		alert.setHeaderText(null);
		alert.setContentText(aContent);
		alert.showAndWait();

		return resLogin;
	}
}
