package model.entities;

import java.io.Serializable;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codInterno;
	private String referencia;
	private String nome;
	private String marca;
	private Double preco;

	public Produto() {

	}

	public Produto(Integer codInterno, String referencia, String nome, String marca, Double preco) {
		this.codInterno = codInterno;
		this.referencia = referencia;
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
	}

	public Integer getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(Integer codInterno) {
		this.codInterno = codInterno;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codInterno == null) ? 0 : codInterno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codInterno == null) {
			if (other.codInterno != null)
				return false;
		} else if (!codInterno.equals(other.codInterno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codInterno=" + codInterno + "]";
	}

}
