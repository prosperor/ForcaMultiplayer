package Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import Comum.Usuario;

public class ThreadClienteServidor extends Thread {
    private static Socket cliente;
    private int ID, count;
    private static DataInputStream entradaDeDados;
    private static DataOutputStream saidaDeDados;
    private static Usuario user;

    public ThreadClienteServidor(Socket _cliente, int _ID) {
        cliente = _cliente;
        ID = _ID;
        ligarConexao();
    }

    private void ligarConexao() {
        try {
            entradaDeDados = new DataInputStream(cliente.getInputStream());
            saidaDeDados = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        String mensagemServidor = "", extra[] = new String[2], extra2[] = new String[2];
        String[] mensagemCliente;
        int opc = 0;

        try {
            while (opc != -1) {
                mensagemCliente = entradaDeDados.readUTF().split(":");
                System.out.println("Cliente " + ID + " enviou o seguinte comando " + mensagemCliente[0] + " " + mensagemCliente[1]);
                switch (mensagemCliente[0]) {
                    case "cs":
                        switch (mensagemCliente[1]) {
                            case "sair":
                                opc = -1;
                                break;
                            case "palavra":
                            mensagemServidor = Servidor.getPalavraMascarada();
                            System.out.println("Mandando palavra mascarada: " + mensagemServidor);
                            saidaDeDados.writeUTF(mensagemServidor);
                            saidaDeDados.flush();
                                break;
                                case "cadastrar":
                                Servidor.userAr[ID-1].setNome(mensagemCliente[2]);
                                saidaDeDados.writeUTF("cadastrado");
                                break;
                            default:
                                System.out.println("Comando ainda não implementado");
                                break;
                        }
                        break;

                    case "ct":
                        extra[0] = Servidor.getPalavraMascarada().trim();
                        extra[1] = Servidor.getLetrasUsadas().trim();

                        mensagemServidor = Servidor.inputChute(mensagemCliente[1].charAt(0));

                        extra2[0] = Servidor.getPalavraMascarada().trim();
                        extra2[1] = Servidor.getLetrasUsadas().trim();
                        System.out.println(extra[0].equals(extra2[0])) ;
                        System.out.println(extra[0] + " , "+  extra2[0]);
                        if(extra[0].equals(extra2[0])){
                            if(extra[1].contains(Character.toString(extra2[1].charAt(0)))){
                                System.out.println("-1pt");
                                Servidor.userAr[ID-1].rmvPts(1);
                            }else{
                                Servidor.userAr[ID-1].rmvPts(3);
                            }
                        }else if((!mensagemServidor.split(":")[0].contains("_"))){
                            System.out.println("5pt");
                            //Servidor.atribuir5pts();
                        }else{
                            System.out.println("1pt");
                            Servidor.userAr[ID-1].addPts(1);
                        }

                        if(Servidor.isEnd()){
                            Servidor.atribuir5pts();;
                            Servidor.restart();
                        }
                        mensagemServidor = mensagemServidor + ":" + getAllPts();

                            //System.out.println("Retornando resultado do chute: " + mensagemServidor);

                        saidaDeDados.writeUTF(mensagemServidor);
                        saidaDeDados.flush();
                        break;

                    default:
                        System.out.println("Não encontrado");
                        break;
                }
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        encerrarConexao();
    }

    public String getAllPts(){
        return Servidor.getAPts();
    }


    private void encerrarConexao() {
        try {
            entradaDeDados.close();
            saidaDeDados.close();
            cliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
