package br.com.ilegra.excecoes;

public class ExcecaoFimArquivo extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoFimArquivo(String pDsDebug) {
		super(pDsDebug);
	}
}
