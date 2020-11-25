import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Socket usuario = new Socket();
        System.out.println("qual o endereço?");
        Scanner teclas = new Scanner(System.in);
        String end = teclas.nextLine();
        try {
            usuario = new Socket(end,3322);
            PrintWriter out = new PrintWriter(usuario.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(usuario.getInputStream()));
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("não deu certo");
        }




    }
}
