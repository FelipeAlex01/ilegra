package br.com.ilegra.objetos;

public class Cliente {

	private String id;
	private String cnpj;
	private String nome;
	private String negocio;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNegocio() {
		return this.negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

}
