package presentation.view;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.entita.Utente;
import business.servizi.ServizioImpianti;
import business.servizi.ServizioLogin;
import integration.DAO.DaoImpianto.Coppia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Classe controller per il frame di selezione impianto
 * 
 * @author redtr_000
 *
 */
public class ListaImpiantiController {

	private final ServizioImpianti servizio = new ServizioImpianti();

	private ObservableList<Coppia> tableImpiantiData = FXCollections.observableArrayList();

	protected static Coppia impiantoSelezionato = null;

	@FXML
	private AnchorPane content;
	@FXML
	private TableView<Coppia> tableImpianti;
	private AnchorPane newLoadedPane;
	@FXML
	private Text textImpianti;

	private static final Logger LOGGER = Logger.getLogger(ListaImpiantiController.class.getName());

	/**
	 * Metodo richiamato prima che lo stage venga caricato
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {

		TableColumn<Coppia, String> idCol = new TableColumn<Coppia, String>("ID");
		idCol.setMinWidth(30);
		idCol.setCellValueFactory(new PropertyValueFactory<>("idI"));

		TableColumn<Coppia, String> nomeCol = new TableColumn<Coppia, String>("Nome Impianto");
		nomeCol.setMinWidth(100);
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nomeI"));

		TableColumn<Coppia, String> clienteCol = new TableColumn<Coppia, String>("ID Cliente");
		clienteCol.setMinWidth(30);
		clienteCol.setCellValueFactory(new PropertyValueFactory<>("idU"));

		TableColumn<Coppia, String> nomeCCol = new TableColumn<Coppia, String>("Nome Cliente");
		nomeCCol.setMinWidth(100);
		nomeCCol.setCellValueFactory(new PropertyValueFactory<>("nomeU"));

		TableColumn<Coppia, String> cognomeCCol = new TableColumn<Coppia, String>("Cognome Cliente");
		cognomeCCol.setMinWidth(100);
		cognomeCCol.setCellValueFactory(new PropertyValueFactory<>("cognomeU"));

		tableImpianti.getColumns().addAll(idCol, nomeCol, clienteCol, nomeCCol, cognomeCCol);

		// LISTA IMPIANTI
		if (AdminStageController.funzione == 1) {

			Utente utente = ServizioLogin.getUtenteLoggato();
			if (utente.getAdmin().equals("1")) {
				tableImpiantiData = stampaLista(servizio.visualizzaTuttiC());
			} else {
				tableImpiantiData = stampaLista(servizio.cercaC(utente.getID()));
			}

			tableImpianti.setItems(tableImpiantiData);

			tableImpianti.setRowFactory(tv -> {
				TableRow<Coppia> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Coppia rowData = row.getItem();
						impiantoSelezionato = rowData;
						System.out.println(rowData.getI().getNome());
						goScene(AdminStageController.LISTA_SENSORI);
					}
				});

				return row;
			});
		}

		// MODIFICA
		else if (AdminStageController.funzione == 4) {

			System.out.println("impianti " + ListaClientiController.utenteSelezionato.getNome());
			System.out.println("funzione: " + AdminStageController.funzione);

			tableImpiantiData = stampaLista(servizio.cercaC(ListaClientiController.utenteSelezionato.getID()));

			textImpianti.setText(ListaClientiController.utenteSelezionato.getNome() + " "
					+ ListaClientiController.utenteSelezionato.getCognome());

			tableImpianti.setItems(tableImpiantiData);

			tableImpianti.setRowFactory(tv -> {
				TableRow<Coppia> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Coppia rowData = row.getItem();
						impiantoSelezionato = rowData;
						System.out.println(rowData.getI().getNome());
						goScene(AdminStageController.MODIFICA_IMPIANTO);
					}
				});

				return row;
			});

			// ELIMINA
		} else if (AdminStageController.funzione == 3) {

			System.out.println("impianti " + ListaClientiController.utenteSelezionato.getNome());
			System.out.println("funzione: " + AdminStageController.funzione);

			tableImpiantiData = stampaLista(servizio.cercaC(ListaClientiController.utenteSelezionato.getID()));

			textImpianti.setText(ListaClientiController.utenteSelezionato.getNome() + " "
					+ ListaClientiController.utenteSelezionato.getCognome());

			tableImpianti.setItems(tableImpiantiData);

			tableImpianti.setRowFactory(tv -> {
				TableRow<Coppia> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (!row.isEmpty()) {
						Coppia rowData = row.getItem();
						impiantoSelezionato = rowData;
						System.out.println(rowData.getI().getNome());

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Avviso Eliminazione");
						alert.setHeaderText(null);
						alert.setContentText("Eliminare l'impianto " + impiantoSelezionato.getNomeI() + " ("
								+ impiantoSelezionato.getIdI() + ") " + "dell'utente " + impiantoSelezionato.getNomeU()
								+ " " + impiantoSelezionato.getCognomeU() + " (" + impiantoSelezionato.getIdU() + ")?");

						Optional<ButtonType> result = alert.showAndWait();

						if (result.get() == ButtonType.OK) {
							System.out.println("Elimina: " + impiantoSelezionato.getIdI());
							boolean resQ = servizio.elimina(impiantoSelezionato.getI());

							Alert alert2;
							String aContent;

							if (resQ) {
								alert2 = new Alert(AlertType.INFORMATION);
								aContent = "Eliminazione impianto per l'utente selezionato effettuata con successo!";

							} else {
								alert2 = new Alert(AlertType.ERROR);
								aContent = "Eliminazione impianto fallita!";
							}

							goScene(AdminStageController.LISTA_IMPIANTI);

							alert2.setTitle("Avviso Eliminazione");
							alert2.setHeaderText(null);
							alert2.setContentText(aContent);
							alert2.showAndWait();

						}
					}
				});

				return row;
			});
		}
	}

	public void goScene(String scene) {
		System.out.println("Matteo Lac " + scene);
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
	private ObservableList<Coppia> stampaLista(List<Coppia> lista) {
		tableImpiantiData.clear();
		for (Coppia i : lista) {
			tableImpiantiData.add(i);
		}
		return tableImpiantiData;
	}

}
