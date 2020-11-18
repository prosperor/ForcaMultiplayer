import java.util.Scanner;

public class JogoDaForca {

    private StringBuilder palavra;
    private ManipuladorTXT manipulador;

    public JogoDaForca(){
        ManipuladorTXT manipulador = new ManipuladorTXT();
        inserirArquivoTexto();
    }

    private void inserirArquivoTexto(){
        Scanner teclado;
        String caminho = "";
        String arquivo = "";
        int opc;
        boolean sair = false;

        while (!sair){

            teclado = new Scanner(System.in);
            System.out.println("1 - criar um arquivo de texto para colocar as palavras" + '\n' +
                    "2 - usar um arquivo de texto existente");
            opc = teclado.nextInt();

            switch (opc) {
                case 1:
                System.out.println("Digite apenas o caminho, com as devidas barras, da pasta onde ficara seu arquivo");
                System.out.println("Exemplo: C\\documents\\pastaExemplo");
                caminho = teclado.nextLine();
                System.out.println("Digite o nome do arquivo sem a extensão");
                System.out.println("Exemplo: arquivoTeste");
                arquivo = teclado.nextLine();
                if (manipulador.criarArquivo(caminho, arquivo)) {
                    sair = true;
                }
                break;

                case 2:
                System.out.println("Digite apenas o caminho, com as devidas barras, da pasta onde reside seu arquivo");
                System.out.println("Suponha que ele fique na pastaExemplo");
                System.out.println("Exemplo: C\\documents\\pastaExemplo");
                caminho = teclado.nextLine();
                System.out.println("Digite o nome do arquivo sem a extensão");
                System.out.println("Exemplo: arquivoTeste");
                arquivo = teclado.nextLine();
                if (manipulador.usarArquivo(caminho, arquivo)) {
                    sair = true;
                }
                break;
            }

        }
    }

    public void 

}
