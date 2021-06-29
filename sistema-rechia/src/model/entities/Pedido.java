package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	
	private Integer notaFiscal;
	private Date dataPedido;
	
	private Cliente cliente;
	private List<ItemPedido> itemPedido = new ArrayList<>();
	
	

}
