package Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ThreadRecebimento extends Thread {

    private static DataInputStream entrada;


    public ThreadRecebimento(DataInputStream _entrada){
        entrada = _entrada;
    }

    public void run(){
        String mensagem;
        while (true){
            try {
                wait(5000);
                mensagem = entrada.readUTF();
                atualizarGame(mensagem);
            }catch (IOException | InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void atualizarGame(String game){
        String palavra = game.split(":")[0];
        String chutes = game.split(":")[1];
        if (!UserInstance.stPalavra[0].equals(palavra) || !UserInstance.stPalavra[1].equals(chutes)){
            System.out.println();
            System.out.println(palavra);
            System.out.println(chutes);
            System.out.println();
        }
    }


}
