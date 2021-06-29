package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Produto;
import model.services.ProdutoService;

public class ProdutoListController implements Initializable, DataChangeListener {

	private ProdutoService service;

	@FXML
	private TableView<Produto> tableViewProduto;

	@FXML
	private TableColumn<Produto, Integer> tableColumnCodInterno;

	@FXML
	private TableColumn<Produto, String> tableColumnReferencia;

	@FXML
	private TableColumn<Produto, Produto> tableColumnEdit;

	@FXML
	TableColumn<Produto, Produto> tableColumnRemove;

	@FXML
	private TableColumn<Produto, String> tableColumnNome;

	@FXML
	private TableColumn<Produto, String> tableColumnMarca;

	@FXML
	private TableColumn<Produto, Double> tableColumnPreco;

	@FXML
	private Button btNew;

	private ObservableList<Produto> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Produto obj = new Produto();
		createDialogForm(obj, "/gui/ProdutoForm.fxml", parentStage);
	}

	public void setProdutoService(ProdutoService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnCodInterno.setCellValueFactory(new PropertyValueFactory<>("codInterno"));
		tableColumnReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProduto.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null!");
		}
		List<Produto> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Produto obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProdutoFormController controller = loader.getController();
			controller.setProduto(obj);
			controller.setProdutoService(new ProdutoService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastro de novo produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private void initEditButtons() {
		tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEdit.setCellFactory(param -> new TableCell<Produto, Produto>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Produto obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/ProdutoForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemove.setCellFactory(param -> new TableCell<Produto, Produto>() {
			private final Button button = new Button("Remover");

			@Override
			protected void updateItem(Produto obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Produto obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Tem certeza que deseja remover?");

		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			}
			catch (DbIntegrityException e) {
				Alerts.showAlert("Erro ao remover objeto", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	@Override
	public void onDataChanged() {
		updateTableView();
	}
}
