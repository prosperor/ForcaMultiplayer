package Game;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;
import java.net.*;


public class UserInstance {

    
    static DataInputStream inStream;
    static DataOutputStream outStream;
    static BufferedReader br;

    static int id, id1;
    static boolean x = false, y = false, z = true;
    static String nome, palavra, ip, stPalavra[];
    static Scanner sc1 = new Scanner(System.in);

    static private Socket client;
    
    


    public static void main(String[] args) {
        int op = 0;
        while(!y){
            System.out.println("Se o jogo estiver sendo hosteado na sua rede pressione 1 para se conectar, se não, pressione 2");
            op = sc1.nextInt();
            sc1.nextLine();

            switch(op){
                case 1:
                System.out.println("Escreva o valor do Socket");
                    iniciarClient(getLIp() + ":" + sc1.nextLine());
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

        System.out.println("Insira seu nome:");
        nome = sc1.nextLine();
        nome = sendMessage("cs:cadastrar:" + nome);
        System.out.println(nome);
        

        System.out.println("conectado ao jogo - aguardando palavra");
        palavra = sendMessage("cs:palavra");
        System.out.println("palavra: " + palavra + '\n');
       
        while(z){
            String otl = sc1.nextLine();
            String[] otlSt = otl.split(":");
            if(otlSt.length == 1){
                chutar(otl);
            }else if(otlSt.length == 2){

                switch(otlSt[1]){
                    case "sair":
                        sendMessage(otl);
                        z = false;
                        break;

                    case "help":
                        helper();
                        break;

                    default:
                        String rt = sendMessage(otl);
                        System.out.print(rt + '\n' + '\n');
                        break;
                }

            }else{
                System.out.println("Não é possivel enviar o valor");
            }
        }

    }

    public static void chutar(String _otl){

        palavra =sendMessage("ct:" + _otl.charAt(0));    
        stPalavra = palavra.split(":");
        System.out.println("pontuação: " + stPalavra[2] + '\n' + "palavra: " + stPalavra[0] + '\n' + stPalavra[1]);
        
    }

    public static char escreverChute() {
        char chute = sc1.next().charAt(0);
        sc1.nextLine();
        return chute;
    }

    

    private static void iniciarClient(String _ip){

        String stIp[] = _ip.split(":");

        try{
            
            client = new Socket(stIp[0], Integer.parseInt(stIp[1]));
            /*
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            */
            inStream=new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());
            br=new BufferedReader(new InputStreamReader(System.in));
        }catch(IOException ex){
            System.out.println("Não foi possivel se conectar");
        }
    }

    public static String sendMessage(String msg) {
        try{
            outStream.writeUTF(msg);
            outStream.flush();

            return inStream.readUTF();
        }catch(Exception e){
            return e.toString();
        }
    }

    public static String getLIp() {
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            return (inetAddress.getHostAddress());
        }catch(Exception e){
            return "error" ;
        }
        
    }

    public static String getEIp(){
        try{
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
    
            String ip = in.readLine(); //you get the IP as a String
        }catch(Exception e){
            ip = "error";
        }
        return ip;
    }

    private static void encerrarConexao(){
        try {
            inStream.close();
            outStream.close();
            client.close();
            br.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void helper(){
        System.out.println("Para enviar um chute, apenas escreva a letra que deseja tentar." + '\n' +
                            "Para usar um comando escreva cs:" + '\n' +
                            "Os comandos disponiveis são:" + '\n' +
                            "   cs:sair    - para se desconectar."  + '\n' +
                            "   cs:palavra - para exibir a palavra (esse comando é automatico apos realizar um chute)." + '\n' +
                            "   cs:help    - para ver os comandos.");
    }
}
