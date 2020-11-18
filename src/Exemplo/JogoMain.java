package Exemplo;//Classe para aparecer a interface gráfica
import Exemplo.FormPrincipal;

import javax.swing.JFrame;

public class JogoMain {

	public static void main(String args[]) {
		FormPrincipal obj = new FormPrincipal();
		obj.setVisible(true);
		obj.setLocationRelativeTo(null);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}