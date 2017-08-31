package presentation.view;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Utente;
import business.servizi.ServizioUtenti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ListaClientiController {

	@FXML
	private AnchorPane content;
	@FXML
	private TableView<Utente> tableClienti;
	private AnchorPane newLoadedPane;

	private final ServizioUtenti servizio = new ServizioUtenti();

	private ObservableList<Utente> tableUtentiData = FXCollections.observableArrayList();

	protected static Utente utenteSelezionato;
	private static final Logger LOGGER = Logger.getLogger(ListaClientiController.class.getName());

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {

		if (AdminStageController.funzione == 2)
			tableClienti.setRowFactory(tv -> {
				TableRow<Utente> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Utente rowData = row.getItem();
						utenteSelezionato = rowData;
						System.out.println(rowData.getNome());

						goScene(AdminStageController.NUOVO_IMPIANTO);
					}
				});

				return row;
			});

		else if (AdminStageController.funzione == 3)
			tableClienti.setRowFactory(tv -> {
				TableRow<Utente> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Utente rowData = row.getItem();
						utenteSelezionato = rowData;
						System.out.println(rowData.getNome());
						goScene(AdminStageController.LISTA_IMPIANTI);
					}
				});

				return row;
			});

		else if (AdminStageController.funzione == 4) {
			tableClienti.setRowFactory(tv -> {
				TableRow<Utente> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Utente rowData = row.getItem();
						utenteSelezionato = rowData;
						System.out.println(rowData.getNome());
						goScene(AdminStageController.LISTA_IMPIANTI);

					}
				});

				return row;
			});
		}

		// tableClienti.setOnMouseClicked((event) -> {
		// goScene(AdminStageController.LISTA_IMPIANTI);
		// });

		TableColumn<Utente, String> idCol = new TableColumn<Utente, String>("ID");
		idCol.setMinWidth(30);
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Utente, String> nomeCol = new TableColumn<Utente, String>("Nome");
		nomeCol.setMinWidth(100);
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Utente, String> cognomeCol = new TableColumn<Utente, String>("Cognome");
		cognomeCol.setMinWidth(100);
		cognomeCol.setCellValueFactory(new PropertyValueFactory<>("cognome"));

		TableColumn<Utente, String> emailCol = new TableColumn<Utente, String>("Email");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		tableUtentiData = stampaLista(servizio.visualizzaTutti());

		tableClienti.setItems(tableUtentiData);
		tableClienti.getColumns().addAll(idCol, nomeCol, cognomeCol, emailCol);

	}

	public void goScene(String scene) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource(scene));
			content.getChildren().clear();
			content.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Caricamento pagina fallito");

		}
	}

	/**
	 * Tasforma la lista in una observable list da mostrare nella tabella
	 * 
	 * @param lista
	 *            lista contenente il resultset
	 * @return observable list da mostrare
	 */
	private ObservableList<Utente> stampaLista(List<Utente> lista) {
		tableUtentiData.clear();
		for (Utente u : lista) {
			tableUtentiData.add(u);
		}
		return tableUtentiData;
	}

}
