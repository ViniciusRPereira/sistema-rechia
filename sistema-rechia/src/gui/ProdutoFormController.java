package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.entities.Produto;

public class ProdutoFormController implements Initializable {
	
	private Produto entity;
	
	@FXML
	private GridPane gPane;

	@FXML
	private TextField txtCodInterno;
	
	@FXML
	private TextField txtReferencia;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtMarca;
	
	@FXML
	private TextField txtPreco;
	
	@FXML
	private Label LabelErrorCodInterno;
	
	@FXML
	private Label LabelErroPreco;
	
	@FXML
	private Button btCadastrar;
	
	@FXML
	private Button btCancelar;
	
	public void setProduto (Produto entity) {
		this.entity = entity;
	}
	
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
		Constraints.setTextFieldInteger(txtCodInterno);
		Constraints.setTextFieldDouble(txtPreco);
	}
	
	public void updateFormData () {
		if (entity == null) {
			throw new IllegalStateException("Entity was null!");
		}	
		txtCodInterno.setText(String.valueOf(entity.getCodInterno()));
		txtReferencia.setText(entity.getReferencia());
		txtNome.setText(entity.getNome());
		txtMarca.setText(entity.getMarca());
		txtPreco.setText(String.valueOf(entity.getPreco()));
	} 
	

}
