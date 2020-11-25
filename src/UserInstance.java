import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;


public class UserInstance {

    static private PrintWriter out;
    static private BufferedReader in;

    static int id, id1;
    static boolean x = false, y = false, isClient;
    static String nome, palavra, ip;
    static Scanner sc1 = new Scanner(System.in);
    static Servidor sv;

    static private Socket client;
    //static DataOutputStream dOut = new DataOutputStream(client.getOutputStream());
    
    


    public static void main(String[] args) {
        int op = 0;
        while(!y){
            System.out.println("1 - ser Host" + '\n' + "2 - conectar a Host");
            isClient = false;
            op = sc1.nextInt();
            sc1.nextLine();

            switch(op){
                case 1:
                    sv = new Servidor();
                    y = true;
                    break;
                case 2:
                    System.out.println("para se conectar, coloque o ip e a porta separados por dois pontos" + '\n' + "exemplo: 0.0.0.0:0000");
                    iniciarClient(sc1.nextLine());
                    y = true;
                    break;
                
                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }


        while(!x){
            
            System.out.println("1 - entrar como novo jogador" + '\n' + "2 - entrar como usuario ja existente");
            op = sc1.nextInt();
            sc1.nextLine();
            switch(op){
                case 1:
                    System.out.println("Insira seu nome: ");
                    nome = sc1.nextLine();
                    
                    palavra = sv.getPalavra();
                    if(!sv.cadastrado(nome)){
                        id = sv.cadastrarUser(nome);
                        System.out.println(sendMessage("ca:" + nome));
                        x = true;
                    }else{
                        System.out.println("Usuario ja cadastrado");
                    }
                    break;
    
                case 2:
                    System.out.println("Insira seu ID: ");
                    id1 = sc1.nextInt();
                    sc1.nextLine();
                    if(sv.cadastrado(id1)){
                        //necessario uma forma de obter o id para autenticar e reconectar ao jogo
                        x = true;
                    }else{
                        System.out.println("ID não corresponde");
                    }
                    break;

                default:
                    System.out.println("opção invalida");
                    break;
            }
        }
        
        
        System.out.println("palavra: " + palavra + '\n');
       
        while(true){
            
            chutar();
        }

    }

    public static void chutar(){
        while(true){
            //enviarChute(escreverChute());

            /*
            try{
                PrintStream saida = new PrintStream(client.getOutputStream());
                saida.println(escreverChute());
            }catch(IOException ex){
                System.out.println("Não foi possivel enviar");
            }

            */
            
            System.out.println("palavra: " + palavra + '\n');
        }
    }

    public static char escreverChute() {
        char chute = sc1.next().charAt(0);
        sc1.nextLine();
        return chute;
    }

    /*
    public static void enviarChute(char _chute){
        sv.chutar(_chute);
        palavra = sv.getPalavra();
    }
    */
    

    private static void iniciarClient(String _ip){

        String stIp[] = _ip.split(":");

        try{
            client = new Socket(stIp[0], Integer.parseInt(stIp[1]));
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }catch(IOException ex){
            System.out.println("Não foi possivel se conectar");
        }
    }

    public static String sendMessage(String msg) {
        out.println(msg);
        try{
            String resp = in.readLine();
            return resp;
        }catch(IOException ex){
            return "error";
        }
    }


}
