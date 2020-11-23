import java.util.Scanner;

public class UserInstance {
    int id;
    String nome, palavra;
    Scanner sc1 = new Scanner(System.in);
    Servidor sv = new Servidor();

    public UserInstance(){
        System.out.println("Insira seu nome: ");

        nome = sc1.nextLine();
        palavra = sv.getPalavra();
        if(!sv.cadastrado(nome)){
            id = sv.cadastrarUser(nome);
            System.out.println("Usuario cadastrado como: " + nome + '\n');
            System.out.println("palavra: " + palavra);
        }else{
            //esperando Servidor.java
        }
        

       

        while(true){
            chutar();
        }
    }

    public void chutar(){
        while(true){
            enviarChute(escreverChute());
            System.out.println("palavra: " + palavra + '\n');
        }
    }

    public char escreverChute() {
        char chute = sc1.next().charAt(0);
        return chute;
    }

    public void enviarChute(char _chute){
        sv.chutar(_chute);
        palavra = sv.getPalavra();
    }
}
