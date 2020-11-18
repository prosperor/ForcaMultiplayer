package Exemplo;//Classe com os get's e set's e a parte gráfica de salvar novos arquivos.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormInserirValores extends JFrame implements ActionListener {

	public JTextField getCampoTexto() {
		return CampoTexto;
	}

	public void setCampoTexto(JTextField campoTexto) {
		CampoTexto = campoTexto;
	}

	public JTextField getCampoDica() {
		return CampoDica;
	}

	public void setCampoDica(JTextField campoDica) {
		CampoDica = campoDica;
	}

	private String NovaPalavra = "";
	private int NivelPalavra = 0;
	private String Categoria = "";
	private String Dica = "";

	public String getNovaPalavra() {
		return NovaPalavra;
	}

	public void setNovaPalavra(String novaPalavra) {
		NovaPalavra = novaPalavra;
	}

	public int getNivelPalavra() {
		return NivelPalavra;
	}

	public void setNivelPalavra(int nivelPalavra) {
		NivelPalavra = nivelPalavra;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	private ManipulaTXT objManipular = new ManipulaTXT();
	private JButton Inserir = new JButton("Inserir");
	private JButton Cancelar = new JButton("Cancelar");
	private JTextField CampoTexto = new JTextField();
	private JTextField CampoDica = new JTextField();
	private JComboBox CaixaDeSelecao = new JComboBox();
	private JLabel lLabel1 = new JLabel("Categoria");
	private JLabel lLabel2 = new JLabel("Palavra");
	private JLabel lLabel3 = new JLabel("Dica");

	public FormInserirValores() { //Parte gráfica para salvar novas palavras. 
		super("Novos Valores");
		setSize(300, 230);

		CaixaDeSelecao.removeAllItems();

		CaixaDeSelecao.addItem("Animal");
		CaixaDeSelecao.addItem("Objeto");
		CaixaDeSelecao.addItem("Pais");
		CaixaDeSelecao.addItem("Times");

		lLabel1.setBounds(10, 10, 100, 30);
		CaixaDeSelecao.setBounds(90, 10, 100, 30);

		lLabel2.setBounds(10, 50, 100, 30);
		CampoTexto.setBounds(90, 50, 100, 30);

		lLabel3.setBounds(10, 90, 100, 30);
		CampoDica.setBounds(90, 90, 180, 30);

		Inserir.setBounds(10, 130, 100, 30);
		Cancelar.setBounds(120, 130, 100, 30);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Inserir);
		this.getContentPane().add(Cancelar);
		this.getContentPane().add(CampoTexto);
		this.getContentPane().add(CaixaDeSelecao);
		this.getContentPane().add(lLabel1);
		this.getContentPane().add(lLabel2);
		this.getContentPane().add(lLabel3);
		this.getContentPane().add(CampoDica);
		Inserir.addActionListener(this);
		Cancelar.addActionListener(this);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {//Aqui é para adicionar os valores na janela Novos Valores
		if (e.getSource() == Inserir) {
			NovaPalavra = CampoTexto.getText();//Pegando a palavra.
			Dica = CampoDica.getText();//Pegando a dica.
			Categoria = CaixaDeSelecao.getSelectedItem().toString();//Pegar a categoria da caixa de seleção(checkbox).
			
			//Essa estrutura é de quando não tinha palavras no arquivo. Onde tinha que adicionar palavras para comçar a jogar.
			if (NovaPalavra.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite uma palavra");
			} else if (Dica.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite uma Dica");
			} else {
				objManipular.InserirNoTxt(NovaPalavra, Dica, Categoria);
				CampoTexto.setText("");
				CampoDica.setText("");
			}
			
		} else if (e.getSource() == Cancelar) {//Para cancelar a parte de salvar a nova palavra.
			setVisible(false);
		}
	}

}
