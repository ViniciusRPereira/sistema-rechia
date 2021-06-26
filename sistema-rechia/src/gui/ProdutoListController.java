package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;

public class ProdutoListController implements Initializable {

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

	public void onBtNewAction() {
		System.out.println("Clicado!");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
}
