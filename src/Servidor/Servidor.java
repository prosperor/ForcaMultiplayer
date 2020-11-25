package Servidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Servidor extends Thread {
    private static ServerSocket servidor;
    private static Socket cliente;
    private static DataInputStream entradaDeDados;
    private static DataOutputStream saidaDeDados;
    private static BufferedReader leitorDeDados;
    private static JogoForca game = new JogoForca();

    public static void main(String[] args) { //Inicia o Servidor
        Scanner tecla = new Scanner(System.in);
        System.out.println("Qual será o Socket");
        Integer socket = tecla.nextInt();;

        try {
            //Inicio do servidor
            servidor = new ServerSocket(socket);
            //Informações necessárias para conexão
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            System.out.println("O IP é: " + in.readLine() + '\n' + "O Socket é " + socket);
            //Servidor espera que o cliente faça uma conexão
            System.out.println("Esperando");
            cliente = servidor.accept();
            System.out.println("Cliente do IP " + cliente.getLocalAddress().getHostName() + " conectado");

            //Ligação com a saida e entrada do cliente
            entradaDeDados = new DataInputStream(cliente.getInputStream());
            saidaDeDados = new DataOutputStream(cliente.getOutputStream());
            leitorDeDados = new BufferedReader(new InputStreamReader(System.in));

            //Variaveis para manipulação
            String mensagemServidor = "";
            String[] mensagemCliente;
            int opc = 0;

            while(opc != -1){
                mensagemCliente = entradaDeDados.readUTF().split(":");
                switch (mensagemCliente[0]){
                    case "cs":
                        switch (mensagemCliente[1]){
                            case "sair":
                                encerrarConexao();
                                opc = -1;
                                break;
                            case "palavra":
                                mensagemServidor = getPalavraMascarada();
                                System.out.println("Mandando palavra mascarada: " + mensagemServidor);
                                saidaDeDados.writeUTF(mensagemServidor);
                                saidaDeDados.flush();
                                break;
                            default:
                                System.out.println("Comando ainda não implementado");
                                break;
                        }
                        break;
                    case "ct":
                        mensagemServidor = inputChute(mensagemCliente[1].charAt(0));
                        System.out.println("Retornando resultado do chute: " + mensagemServidor);
                        saidaDeDados.writeUTF(mensagemServidor);
                        saidaDeDados.flush();
                        break;
                    default:
                        System.out.println("Não encontrado");
                        break;
                }
            }




        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Deu errado, reinicie e reconfigure seu socket");
        }



    }

    private String[] dividirEntrada(String entrada){
        return entrada.split(":");
    }

    private static void encerrarConexao(){
        try {
            entradaDeDados.close();
            saidaDeDados.close();
            cliente.close();
            servidor.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String getPalavraMascarada(){
        return game.getWordChute();
    }

    private static String inputChute(char letra){
        return game.chute(letra);
    }
}
