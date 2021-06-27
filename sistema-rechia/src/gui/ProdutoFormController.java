package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProdutoFormController implements Initializable {

	@FXML
	private TextField codInterno;
	
	@FXML
	private TextField referencia;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField marca;
	
	@FXML
	private TextField preco;
	
	@FXML
	private Label LabelErrorCodInterno;
	
	@FXML
	private Label LabelErroPreco;
	
	@FXML
	private Button btCadastrar;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCadastrar () {
		System.out.println("onCadastrarAction");
	}
	
	@FXML
	public void onBtCancelar () {
		System.out.println("onCancelarAction");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(codInterno);
		Constraints.setTextFieldDouble(preco);
	}

}
