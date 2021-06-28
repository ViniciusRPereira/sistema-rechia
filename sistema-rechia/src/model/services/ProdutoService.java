package model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Produto;

 public class ProdutoService {
	
	
	 public List<Produto> findAll() {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
				"SELECT * FROM produto");
			rs = st.executeQuery();

			List<Produto> list = new ArrayList<>();

			while (rs.next()) {
				Produto obj = new Produto();
				obj.setCodInterno(rs.getInt("codInterno"));
				obj.setReferencia(rs.getString("referecia"));
				obj.setNome(rs.getString("nome"));
				obj.setMarca(rs.getString("marca"));
				obj.setPreco(rs.getDouble("preco"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	} 
} 
