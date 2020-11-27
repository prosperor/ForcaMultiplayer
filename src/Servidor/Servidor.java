package Servidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import Comum.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private static List<Usuario> user = new ArrayList<Usuario>();
    public static Usuario[] userAr;
    public static int count = 0;
    public static final JogoForca game = new JogoForca();

    public static void main(String[] args) { //Inicia o Servidor
        Scanner tecla = new Scanner(System.in);
        System.out.println("Qual será o Socket");
        int socket = tecla.nextInt();
        int ID = 0;


        try {
            //Inicio do servidor
            ServerSocket servidor = new ServerSocket(socket);
            System.out.println("Servidor iniciado");

            while (true) {
                //Informações necessárias para conexão
                URL whatismyip = new URL("http://checkip.amazonaws.com");
                BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
                System.out.println("O IP é: " + in.readLine() + '\n' + "O Socket é " + socket);
                //Servidor espera que o cliente faça uma conexão
                System.out.println("Esperando" + '\n');


                Socket cliente = servidor.accept(); //Servidor aceita uma conexão
                System.out.println("Servidor aceitou o cliente numero " + ID++);
                ThreadClienteServidor t = new ThreadClienteServidor(cliente,ID);
                user.add(new Usuario(ID, "nome_exemplo"));
                userAr = new Usuario[user.size()];
                user.toArray(userAr);
                t.start();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Deu errado, reinicie e reconfigure seu socket");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro diferente do usual");
        }



    }



    public static String getPalavraMascarada(){
        return game.getWordChute();
    }

    public static synchronized String inputChute(char letra){
        contar();
        return game.chute(letra);
    }


    public static void contar(){
        count++;
        System.out.println(count);
        if(count == 7){
            count = 0;
            for(int i = 0; i<userAr.length; i++){
                userAr[i].rmvPts(5);
            }
        }
    }

    public synchronized static void atribuir5pts(){
        for(int i = 0; i<userAr.length; i++){
            userAr[i].addPts(5);
        }
    }
    
    public static boolean isEnd(){
        return game.isEnd();
    }

    public static void restart(){
        game.startNewGame();
    }

    public synchronized static String getAPts(){
        String ptsL = "";
        for(int i = 0; i < userAr.length; i++){
            ptsL = ptsL + userAr[i].getNome() + " - " + userAr[i].getPts() + " ";
        }
        return ptsL;
    }

    public static String getLetrasUsadas(){
        return game.getLetrasUsadas();
    }
}
