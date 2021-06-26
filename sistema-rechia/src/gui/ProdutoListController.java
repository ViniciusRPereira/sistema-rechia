package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;
import model.services.ProdutoService;

public class ProdutoListController implements Initializable {
	
	private ProdutoService service;

	@FXML
	private TableView<Produto> tableViewProduto;

	@FXML
	private TableColumn<Produto, Integer> tableColumnCodInterno;

	@FXML
	private TableColumn<Produto, String> tableColumnReferencia;

	@FXML
	private TableColumn<Produto, String> tableColumnNome;

	@FXML
	private TableColumn<Produto, String> tableColumnMarca;

	@FXML
	private TableColumn<Produto, Double> tableColumnPreco;

	@FXML
	private Button btNew;
	
	private ObservableList<Produto> obsList;

	public void onBtNewAction() {
		System.out.println("Clicado!");
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
		List<Produto> list = service.findALL();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);
	}
	
}
