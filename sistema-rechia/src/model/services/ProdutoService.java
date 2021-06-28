package model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.entities.Produto;

public class ProdutoService {

	public List<Produto> findAll() {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT * FROM produto");
			rs = st.executeQuery();

			List<Produto> list = new ArrayList<>();

			while (rs.next()) {
				Produto obj = new Produto();
				obj.setCodInterno(rs.getInt("codInterno"));
				obj.setReferencia(rs.getString("referencia"));
				obj.setNome(rs.getString("nome"));
				obj.setMarca(rs.getString("marca"));
				obj.setPreco(rs.getDouble("preco"));
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public void insert(Produto obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO produto " + "(codInterno, referencia, nome, marca, preco ) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getCodInterno());
			st.setString(2, obj.getReferencia());
			st.setString(3, obj.getNome());
			st.setString(4, obj.getMarca());
			st.setDouble(5, obj.getPreco());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCodInterno(id);
				}

			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void update(Produto obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"UPDATE produto " + "SET referencia = ?, nome = ?, marca = ?, preco = ?" + 
					"WHERE codInterno = ?");

			st.setString(1, obj.getReferencia());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getMarca());
			st.setDouble(4, obj.getPreco());
			st.setInt(5, obj.getCodInterno());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void saveOrUpdate(Produto obj) {

		 if (obj.getCodInterno() == null) { 
			insert(obj);;

		} else {
			update(obj);
		}
	}
}
