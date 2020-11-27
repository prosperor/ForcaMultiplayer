package Servidor;
import java.io.IOException;

public class TaskThread extends Thread{

    public void run(){
        while(true){
            try {
                wait(5000);
                Servidor.mandarAtualizacao();
            }catch (InterruptedException e){
                System.out.println("cronometro interrompido antes do tempo");
                System.out.println(e.getMessage());
            }catch (IOException e){
                System.out.println("erro no server");
                System.out.println(e.getMessage());
            }
        }
    }
}
