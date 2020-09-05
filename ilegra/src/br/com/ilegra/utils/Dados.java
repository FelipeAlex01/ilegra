package br.com.ilegra.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Dados {

	private FileReader fileReader;
	private File file;
	private BufferedReader bufferedReader;

	public Dados(File file) {

		if (this.file == null) {
			this.file = file;
		}
	}

	public BufferedReader getBufferedReader() throws Exception {

		try {

			if (this.bufferedReader == null) {
				this.bufferedReader = new BufferedReader(this.getFileReader());
			}

		} catch (Exception e) {
			throw new Exception("Erro ao carregar o arquivo");
		}

		return this.bufferedReader;
	}

	public FileReader getFileReader() throws Exception {

		try {

			if (this.fileReader == null) {
				this.fileReader = new FileReader(this.file.getPath());
			}
		} catch (Exception e) {
			throw new Exception("Erro na leitura do arquivo");
		}

		return this.fileReader;
	}

	public FileOutputStream criarArquivo(String pathName) throws Exception {

		File file = new File(pathName).getAbsoluteFile();
		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(file);
		} catch (Exception e) {
			throw new Exception("Erro ao criar arquivo de saida");
		}

		return fileOutputStream;
	}
}
