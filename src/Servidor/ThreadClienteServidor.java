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
        String mensagemServidor = "", extra[];
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
                        extra = mensagemServidor.split(":");

                        mensagemServidor = Servidor.inputChute(mensagemCliente[1].charAt(0));

                        if(!extra[0].equals(mensagemServidor.split(":")[0].trim())){
                            if((!mensagemServidor.split(":")[0].contains("_"))){
                                Servidor.atribuir5pts();
                            }else{
                                Servidor.userAr[ID-1].addPts(1);
                            }
                        }else if(extra[1].contains(Character.toString(mensagemCliente[1].charAt(0)))){
                            Servidor.userAr[ID-1].rmvPts(1);
                        }else{
                            Servidor.userAr[ID-1].rmvPts(3);
                        }

                        if(Servidor.isEnd()){
                            Servidor.restart();
                        }
                        mensagemServidor = mensagemServidor + ":" + getAllPts();

                            System.out.println("Retornando resultado do chute: " + mensagemServidor);

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
