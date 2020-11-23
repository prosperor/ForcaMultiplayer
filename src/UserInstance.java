import java.util.Scanner;

public class UserInstance {
    int id, x, id1;
    String nome, palavra;
    Scanner sc1 = new Scanner(System.in);
    Servidor sv = new Servidor();

    public UserInstance(){

        while(x != 0){
            System.out.println("1 - entrar como novo jogador" + '\n' + "2 - entrar como usuario ja existente");
            switch(x){
                case 1:
                    System.out.println("Insira seu nome: ");
    
                    nome = sc1.nextLine();
                    palavra = sv.getPalavra();
                    if(!sv.cadastrado(nome)){
                        id = sv.cadastrarUser(nome);
                        System.out.println("Usuario cadastrado como: " + nome + '\n');
                        x = 0;
                    }else{
                        System.out.println("Usuario ja cadastrado");
                    }
                    break;
    
                case 2:
                    System.out.println("Insira seu ID: ");
                    id1 = sc1.nextInt();
                    if(sv.cadastrado(id1)){
                        //necessario uma forma de obter o id para autenticar e reconectar ao jogo
                        x = 0;
                    }else{
                        System.out.println("ID não corresponde");
                    }
                    break;
            }
        }
        
        
        System.out.println("palavra: " + palavra + '\n');
       
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
