import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor extends Thread {
    private List<Usuario> jogadores = new ArrayList<>();;
    private static JogoForca jogo = new JogoForca();
    private static ServerSocket server;
    private Socket usuario;


    public Servidor() {
        try {
            server = new ServerSocket(3322);
            System.out.println(server.getInetAddress());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public String chutar(){
        Scanner letra;
        char in = ' ';
        try {
            letra = new Scanner(usuario.getInputStream());
            in = letra.nextLine().charAt(0);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return jogo.chute(in);
    }

    public String getPalavra(){
        return jogo.getWordChute();
    }

    public int cadastrarUser(String nome){
        int ID = jogadores.size() + 10;
        jogadores.add(new Usuario(ID, nome));
        return ID;
    }

    public boolean cadastrado(int ID){
        for (int x=0; x < jogadores.size(); x++){
            if (jogadores.get(x).getID() == ID){
                return true;
            }
        }
        return false;
    }

    public boolean cadastrado(String nome){
        for (int x=0; x < jogadores.size(); x++){
            if (jogadores.get(x).getNome() == nome){
                return true;
            }
        }
        return false;
    }

    public void reiniciar(){
        jogo.startNewGame();
    }

    public static void main(String[] args) {
        Thread t = new Servidor();
        t.start();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Esperando conexão na porta " + server.getLocalPort() + "...");
                Socket client = server.accept();

                System.out.println("Conectado com o cliente " + client.getRemoteSocketAddress());
                client.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
