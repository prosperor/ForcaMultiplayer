package Exemplo;//Clase da manipulação de arquivos.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ManipulaTXT {

	private ArrayList<String> getPalavrasCadastradas(String CaminhoArquivo)//Função para pegar as palavras do getPalavrasCadastradas, verificar se estar cadastrada e colocar na lista.
			throws IOException { 
		ArrayList<String> listPalavras = new ArrayList<String>();
		File file = new File(CaminhoArquivo);
		if (file.exists()) {
			FileReader fileReader = new FileReader(CaminhoArquivo);
			BufferedReader reader = new BufferedReader(fileReader);
			String data = null;
			String[] sArrayValores;
			while ((data = reader.readLine()) != null) {
				sArrayValores = data.split(";");//Vai separar a palavra da dica pelo ";". 
				listPalavras.add(sArrayValores[0]);//Adicionando no Array.
			}

			fileReader.close();
		}
		return listPalavras;
	}
	
	//Para gravar a palavra.
	private void Gravar(String palavra, String Dica, String Classificacao,
			String Nivel) throws IOException {

		ArrayList<String> ListaPalavras = getPalavrasCadastradas("c:\\Nivel " + Nivel + ".txt");//Aqui ele vai salvar a palavra pela quantidade de letras.

		if (!ListaPalavras.contains(palavra)) {//Essa condição é para salvar a palavra e verificar se já existe ou não.
			FileWriter arq = new FileWriter("c:\\Nivel " + Nivel + ".txt", true);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.write(palavra + ";" + Dica + ";" + Classificacao + " \r\n");
			arq.close();

			JOptionPane.showMessageDialog(null, "Palavra Inserida");
		} else {
			JOptionPane.showMessageDialog(null, "Esta Palavra já foi inserida");
		}
	}
	
	//Aqui é para definir o nível da palavra.
	public void InserirNoTxt(String palavra, String Dica, String Classificacao) {
		try {
			Gravar(palavra, Dica, Classificacao, ((palavra.length() <= 7) ? "1" : "2"));
		} catch (IOException e) {

			e.printStackTrace();//Isso serve para mostrar o erro.
		}
	}
}