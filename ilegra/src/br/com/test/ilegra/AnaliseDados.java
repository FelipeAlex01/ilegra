package br.com.test.ilegra;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.ilegra.excecoes.ExcecaoFimArquivo;
import br.com.ilegra.objetos.Cliente;
import br.com.ilegra.objetos.Item;
import br.com.ilegra.objetos.Venda;
import br.com.ilegra.objetos.Vendedor;
import br.com.ilegra.utils.Constantes;
import br.com.ilegra.utils.Dados;
import br.com.ilegra.utils.FuncaoDataHora;

public class AnaliseDados {

	private static Map<String, Vendedor> vendedores = new LinkedHashMap<>();
	private static Map<String, Cliente> clientes = new LinkedHashMap<>();
	private static Map<String, Venda> vendas = new LinkedHashMap<>();
	private static List<String> camposLinhaCorrente;

	public static void main(String[] args) {

		File arquivos = new File(Constantes.ENTRADA).getAbsoluteFile();
		Dados dados = null;

		for (File arquivo : arquivos.listFiles()) {
			dados = new Dados(arquivo);

			try {

				while (true) {
					String linha = dados.getBufferedReader().readLine();

					if (linha == null) {
						throw new ExcecaoFimArquivo("Arquivo finalizado");
					}

					camposLinhaCorrente = Arrays.asList(linha.split("ç"));
					if (camposLinhaCorrente.get(0).equals(Constantes.IDENTIDICADOR_VENDEDOR)) {
						importarVendedor(linha);
					} else if (camposLinhaCorrente.get(0).equals(Constantes.IDENTIDICADOR_CLIENTE)) {
						importarCliente(linha);
					} else if (camposLinhaCorrente.get(0).equals(Constantes.IDENTIDICADOR_VENDA)) {
						importarVenda(linha);
					}
				}

			} catch (Exception e) {

				if (e instanceof ExcecaoFimArquivo) {

					String data = FuncaoDataHora.getDataHoje().replace("/", "-");
					String hora = FuncaoDataHora.getHoraComoStringComMilis().format(new Date()).replace(":", "-");

					try {

						FileOutputStream fileOutputStream = dados.criarArquivo(
								Constantes.SAIDA + File.separator + "saida_" + data + " " + hora + ".txt");
						
						OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "UTF-8");
						
						osw.write("Quantidade de clientes: " + clientes.size() + " \n");
						osw.write("Quantidade de vendedores: " + vendedores.size() + " \n");
						osw.write("ID venda mais cara: " + vendaCara().getVenda() + " \n");
						osw.write("Pior vendedor: " + piorVendedor().getVendedor() + " \n");
						osw.close();

					} catch (Exception exception) {
						System.out.println(exception.getMessage());
					}

				} else {
					System.out.print(e.getCause());
				}
			}
		}
	}

	private static void importarVendedor(String linha) {

		Vendedor vendedor = new Vendedor();
		vendedor.setId(camposLinhaCorrente.get(0));
		vendedor.setCpf(camposLinhaCorrente.get(1));
		vendedor.setNome(camposLinhaCorrente.get(2));
		vendedor.setSalario(new BigDecimal(camposLinhaCorrente.get(3)));

		vendedores.put(vendedor.getCpf(), vendedor);

	}

	private static void importarCliente(String linha) {

		Cliente cliente = new Cliente();
		cliente.setId(camposLinhaCorrente.get(0));
		cliente.setCnpj(camposLinhaCorrente.get(1));
		cliente.setNome(camposLinhaCorrente.get(2));
		cliente.setNegocio(camposLinhaCorrente.get(3));

		clientes.put(cliente.getCnpj(), cliente);

	}

	private static void importarVenda(String linha) {

		List<String> itemsVenda = Arrays.asList(camposLinhaCorrente.get(2).split(","));
		Venda venda = new Venda();
		venda.setId(camposLinhaCorrente.get(0));
		venda.setVenda(camposLinhaCorrente.get(1));

		for (String valores : itemsVenda) {
			List<String> items = Arrays.asList(valores.split("-"));
			Item item = new Item();
			item.setId(Integer.valueOf(items.get(0).replace("[", "")));
			item.setQuantidade(Integer.valueOf(items.get(1)));
			item.setPreco(new BigDecimal(items.get(2).replace("]", "")));
			venda.getItems().add(item);
		}

		venda.setVendedor(camposLinhaCorrente.get(3));

		vendas.put(camposLinhaCorrente.get(1), venda);

	}

	private static Venda vendaCara() {

		Iterator<Venda> iterator = vendas.values().iterator();

		BigDecimal valor = BigDecimal.ZERO;
		Venda vendaCara = new Venda();

		while (iterator.hasNext()) {

			Venda venda = iterator.next();
			BigDecimal somaVenda = BigDecimal.ZERO;
			for (Item item : venda.getItems()) {				
				somaVenda = somaVenda.add(item.getPreco());
			}
			
			if (somaVenda.compareTo(valor) > 0) {
				valor = somaVenda;
				vendaCara = venda;
			}
			
		}

		return vendaCara;
	}
	
	
	private static Venda piorVendedor() {
		
		Iterator<Venda> iterator = vendas.values().iterator();

		BigDecimal valor = new BigDecimal("9999999");
		Venda piorVendedor = new Venda();

		while (iterator.hasNext()) {

			Venda venda = iterator.next();
			BigDecimal somaVenda = BigDecimal.ZERO;
			for (Item item : venda.getItems()) {				
				somaVenda = somaVenda.add(item.getPreco());
			}
			
			if (somaVenda.compareTo(valor) < 0) {
				valor = somaVenda;
				piorVendedor = venda;
			}
		
		}

		return piorVendedor;
		
	}
}
