package br.com.ilegra.objetos;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	private String id;
	private String venda;
	private List<Item> items;
	private String vendedor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVenda() {
		return venda;
	}

	public void setVenda(String venda) {
		this.venda = venda;
	}

	public List<Item> getItems() {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

}
