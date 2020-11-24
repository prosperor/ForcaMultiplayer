import javax.sound.midi.Soundbank;
import java.io.IOException;
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
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("não deu certo");
        }

        System.out.println(usuario.isConnected());

    }
}
