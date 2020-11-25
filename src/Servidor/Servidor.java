package Servidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Servidor {
    private static ServerSocket servidor;
    private static JogoForca game = new JogoForca();

    public static void main(String[] args) { //Inicia o Servidor
        Scanner tecla = new Scanner(System.in);
        System.out.println("Qual será o Socket");
        Integer socket = tecla.nextInt();
        Integer ID = 0;

        try {
            //Inicio do servidor
            servidor = new ServerSocket(socket);
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

    public static String inputChute(char letra){
        return game.chute(letra);
    }

   /* private static void encerrarConexao(){
        try {
            entradaDeDados.close();
            saidaDeDados.close();
            cliente.close();
            servidor.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    */
}
