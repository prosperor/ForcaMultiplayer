package Exemplo;//Classe principal da parte gráfica, onde o usuário escolhe jogar ou salvar novas palavras.

import Exemplo.FormInserirValores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormPrincipal extends JFrame implements ActionListener {

	JButton Botao1 = new JButton("Nova Palavra");
	JButton Botao2 = new JButton("Nivel 1");
	JButton Botao3 = new JButton("Nível 2");
	@SuppressWarnings("rawtypes")
	JComboBox Classificao = new JComboBox();
	JLabel SelecioneClassificao = new JLabel("Selecione a classificação");
	JLabel Niveis = new JLabel("Niveis de Jogo");

	ArrayList<String> Palavras = new ArrayList<String>();
	ArrayList<String> Dicas = new ArrayList<String>();

	JTextField CampoTexto = new JTextField();

	FormInserirValores novaPalavra = new FormInserirValores();

	public FormPrincipal() {
		super("Jogo Forca");//Essa é a primeira tela que aparece.
		setSize(400, 170);

		Classificao.addItem("Animal");
		Classificao.addItem("Objeto");
		Classificao.addItem("Pais");
		Classificao.addItem("Times");

		Botao1.setBounds(10, 100, 200, 30);

		SelecioneClassificao.setBounds(10, 10, 200, 30);
		Classificao.setBounds(170, 10, 210, 30);
		Niveis.setBounds(10, 50, 200, 30);

		Botao2.setBounds(170, 50, 100, 30);
		Botao3.setBounds(280, 50, 100, 30);

		this.getContentPane().setLayout(null);
		this.getContentPane().add(Niveis);
		this.getContentPane().add(SelecioneClassificao);
		this.getContentPane().add(Classificao);
		this.getContentPane().add(Botao1);
		this.getContentPane().add(Botao2);
		this.getContentPane().add(Botao3);
		Botao1.addActionListener(this);
		Botao2.addActionListener(this);
		Botao3.addActionListener(this);
		setVisible(true);
		setResizable(false);
	}

	//Limpar a lista para receber a nova palavra, após digitar verifica se existe ou não.
	private void PalavrasNoTxT(String CaminhoArquivo, String Categoria)
			throws IOException {

		Palavras.clear();//Limpando o campo da palavra.
		Dicas.clear();//Limpando o campo da dica.
		File file = new File(CaminhoArquivo);
		if (file.exists()) {
			FileReader fileReader = new FileReader(CaminhoArquivo);
			BufferedReader reader = new BufferedReader(fileReader);
			String data = null;
			String[] sArrayValores;
			while ((data = reader.readLine()) != null) {
				sArrayValores = data.split(";");
				if (sArrayValores[2].trim().equals(Categoria.trim())) {
					Palavras.add(sArrayValores[0]);//Adicionando na palavra.
					Dicas.add(sArrayValores[1]);//Adicionando  na dica.
				}
			}
			fileReader.close();
		}

	}

	@Override
	//Condições dependendo do botão digitado.
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Botao1) { //Botão para adicionar novas palavras.
			novaPalavra.setVisible(true);
			novaPalavra.getCampoDica().setText("");
			novaPalavra.getCampoTexto().setText("");
		} else if (e.getSource() == Botao2) {//Botão para iniciar o jogo no nível 1.

			File file = new File("c:\\Nivel 1.txt");
			if (file.exists()) {
				JogoDaForca oJogo = new JogoDaForca();
				try {
					PalavrasNoTxT("c:\\Nivel 1.txt", Classificao
							.getSelectedItem().toString());
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				oJogo.setPalavras(Palavras);
				oJogo.setDicas(Dicas);
				oJogo.CarregarForm(Classificao.getSelectedItem().toString());

				oJogo.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "Antes de começar a jogar Insira palavras nível 1");
			}
		} else if (e.getSource() == Botao3) {//Botão para iniciar o jogo no nível 2.

			File file = new File("c:\\Nivel 2.txt");
			if (file.exists()) {
				JogoDaForca oJogo = new JogoDaForca();
				try {
					PalavrasNoTxT("c:\\Nivel 2.txt", Classificao
							.getSelectedItem().toString());
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				oJogo.setPalavras(Palavras);
				oJogo.setDicas(Dicas);
				oJogo.CarregarForm(Classificao.getSelectedItem().toString());

				oJogo.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "Antes de começar a jogar Insira palavras nível 2");
			}
		}
	}

}
