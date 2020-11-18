package Exemplo;//Nessa classe a mágica do jogo acontece.

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JogoDaForca extends JFrame implements ActionListener {

	private JButton Enviar = new JButton("Enviar");
	private JTextField CaixaDeTexto = new JTextField();
	private JLabel lLabel1 = new JLabel("Dica : ");
	private JLabel lLabel2 = new JLabel("Palavra : ");
	private JLabel lLabel3 = new JLabel("");
	private JLabel lLabel4 = new JLabel("Classificação : ");
	ArrayList<String> LetrasDigitadas = new ArrayList<String>();

	private String sPalavraCorreta = "";
	private ArrayList<String> Palavras;

	public ArrayList<String> getPalavras() {
		return Palavras;
	}

	public void setPalavras(ArrayList<String> palavras) {
		Palavras = palavras;
	}

	public ArrayList<String> getDicas() {
		return Dicas;
	}

	public void setDicas(ArrayList<String> dicas) {
		Dicas = dicas;
	}

	private ArrayList<String> Dicas;
	private String sDica;//Dica sorteada.
	private String sPalavra;//Palavra sorteada.
	private int tamanho_da_palavra = 0;//var para saber a quantidade de letras da palavra.
	private int acertou = 0;//var que adiciona 1 para cada acerto.

	public int GetIndexAleatorio() {//Criando o método do random para sortear. 
		Random r = new Random();

		return r.nextInt(Palavras.size());
	}

	public JogoDaForca() {
		super("Jogo");//Janela do jogo em si.
	}

	public String SepararPalavra(String Palavra) {//Aqui ele está transformando a palavra do jogo toda em maiuscula.

		String PalavraNova = "";
		for (int i = 0; i < Palavra.toUpperCase().length(); i++) {
			PalavraNova += Palavra.toUpperCase().substring(i, i + 1) + " ";
		}
		return PalavraNova;
	}

	public String SetUnderScore(int lenght) {//Aqui está trocando a posição da palavra do jogo pelo "_ ".

		String sPalavra = "";
		for (int i = 0; i < lenght; i++) {
			sPalavra += "_ ";
		}
		return sPalavra;
	}

	public void CarregarForm(String Classificao) {
		setSize(400, 200);

		int index = GetIndexAleatorio();//Esse index está pegando o método para sortear.
		this.getContentPane().setLayout(null);

		sDica = Dicas.get(index).toString();//Sorteando a dica.
		sPalavra = Palavras.get(index).toString();//Sorteando a palavra.
		sPalavraCorreta = sPalavra;//A palavra correta está recebendo a palavra sorteada(sPalavra). 
		tamanho_da_palavra = sPalavra.length();//Recebendo o tamanho da palavra para ser usado na condição de quando faltar 3 letras.
		Font arial = new Font("Arial", 1, 18);

		lLabel1.setFont(arial);

		lLabel1.setText("Dicas : " + sDica);
		lLabel1.setBounds(10, 10, 400, 300);

		lLabel4.setFont(arial);

		lLabel4.setText("Classificação : " + Classificao);
		lLabel4.setBounds(10, 10, 240, 30);

		lLabel2.setFont(new Font("Arial", 1, 18));
		lLabel2.setBounds(10, 50, 180, 30);

		lLabel3.setFont(new Font("Arial", 1, 18));
		
		lLabel3.setText(SetUnderScore(sPalavra.length()));
		lLabel3.setBounds(100, 50, 400, 30);

		CaixaDeTexto.setBounds(10, 100, 100, 30);
		Enviar.setBounds(120, 100, 100, 30);

		this.getContentPane().add(Enviar);
		this.getContentPane().add(CaixaDeTexto);

		this.getContentPane().add(lLabel1);
		this.getContentPane().add(lLabel3);
		this.getContentPane().add(lLabel4);
		this.getContentPane().add(lLabel2);
		Enviar.addActionListener(this);
		setLocationRelativeTo(null);

		setResizable(false);
	}

	public ArrayList<Integer> GetIndices(String Palavra, String Valor) {//Pegando e retornando o indice da palavra.

		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < Palavra.length(); i++) {

			String sChar = Palavra.toUpperCase().substring(i, i + 1);//Pegando o indice da palavra e substituindo pela letra digitada.

			if (sChar.toUpperCase().equals(Valor.trim())) {//Só adiciona o valor se for igual.

				indices.add(i);
			}

		}
		return indices;

	}

	public String NovaPalavra(ArrayList<Integer> Lista, String Valor, String Palavra) {

		//int j = 0;
		String[] NovaPalavra = Palavra.split(" ");
		String NewPalavra = "";
		for (int i = 0; i < Lista.size(); i++) {
			NovaPalavra[Lista.get(i)] = Valor;
			acertou++;//Adicionando 1 a cada vez que o usuário acertar uma letra.
		}

		for (int i = 0; i < NovaPalavra.length; i++) {//Essa NewPalavra é a NovaPalavra após os acertos.
			NewPalavra += NovaPalavra[i] + " ";
		}

		// NovaPalavra += "_ ";

		return NewPalavra;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Enviar) {
			String Valor = CaixaDeTexto.getText();
			String Palavra = SepararPalavra(sPalavra);
			String PalavraOculta = lLabel3.getText();
			int tamanho = Valor.length();

			if (!LetrasDigitadas.contains(Valor)) {
				LetrasDigitadas.add(Valor);
				if (tamanho == 1) {

					if (sPalavra.toUpperCase().contains(Valor.toUpperCase())) {

						
						ArrayList<Integer> indices = GetIndices(
								sPalavra.toUpperCase(), Valor.toUpperCase());
						lLabel3.setText(NovaPalavra(indices,
								Valor.toUpperCase(), PalavraOculta));

						if ((tamanho_da_palavra - acertou) <= 3) {//Condição para quando faltar 3 letras.
							String palavraCorreta = "";
							while (!sPalavra.toUpperCase().equals(
									palavraCorreta.toUpperCase()))
								palavraCorreta = JOptionPane
										.showInputDialog("Digite a Palavra");

							if (sPalavra.toUpperCase().equals(
									palavraCorreta.toUpperCase())) {
								lLabel3.setText(Palavra);
								JOptionPane.showMessageDialog(null,
										"Parabéns você acertou");
							}
						}

					}

					// lLabel3

				} else {

					if (sPalavra.toUpperCase().equals(Valor.toUpperCase())) {
						lLabel3.setText(Palavra);
						JOptionPane.showMessageDialog(null,
								"Parabéns você acertou");
					}else{
						JOptionPane.showMessageDialog(null, "Digite apenas uma letra ou a palavra correta");

					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "Você já digitou [" + Valor + "]");
			}

		}

	}
}
