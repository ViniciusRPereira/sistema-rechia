package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.entities.Produto;
import model.services.ProdutoService;

public class ProdutoFormController implements Initializable {

	private Produto entity;
	private ProdutoService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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

	public void setProduto(Produto entity) {
		this.entity = entity;
	}

	public void setProdutoService(ProdutoService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtCadastrar(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
		entity = getFormData();
		service.insert(entity);
		//service.saveOrUpdate(entity);
		notifyDataChangeListeners();
		Utils.currentStage(event).close();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Produto getFormData() {
		Produto obj = new Produto();

		obj.setCodInterno(Utils.tryParseToInt(txtCodInterno.getText()));
		obj.setReferencia(txtReferencia.getText());
		obj.setNome(txtNome.getText());
		obj.setMarca(txtMarca.getText());
		obj.setPreco(Utils.tryParseToDouble(txtPreco.getText()));
		
		return obj;

	}

	@FXML
	public void onBtCancelar(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodInterno);
		Constraints.setTextFieldDouble(txtPreco);
	}

	public void updateFormData() {
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
