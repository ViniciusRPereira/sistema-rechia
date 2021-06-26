package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Produto;

public class ProdutoService {
	
	public List<Produto> findALL (){
	List<Produto> list = new ArrayList<>();
	list.add(new Produto("9999999999", "214599", "TRYPTIC SOY AGAR - 500G", "DIFCO/USA", 479.0));
	list.add(new Produto("9999947679", "217699", "POLIMIXINA B - 500G", "DIFCO/USA", 90.0));
	list.add(new Produto("9999999999", "AS-214599", "PLATE COUNT AGAR - 500G", "DIFCO/USA", 66.0));
	list.add(new Produto("9999999999", "214599", "LAURYL TRYPTOSE BROTH - 500G", "DIFCO/USA", 22.0));
	return list;
}
}
