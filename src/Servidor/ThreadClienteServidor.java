package Servidor;

import java.io.BufferedReader;
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
    private static BufferedReader leitorDeDados;
    private static Usuario user;

    public ThreadClienteServidor(Socket _cliente, int _ID){
        user = new Usuario(_ID, "nomeExemplo");
        cliente = _cliente;
        ID = _ID;
        ligarConexao();
    }

    private void ligarConexao(){
        try {
            entradaDeDados = new DataInputStream(cliente.getInputStream());
            saidaDeDados = new DataOutputStream(cliente.getOutputStream());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void run(){
        String mensagemServidor = "", extra;
        String[] mensagemCliente;
        int opc = 0;
        try {
            while (opc != -1) {
                mensagemCliente = entradaDeDados.readUTF().split(":");
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
                            default:
                                System.out.println("Comando ainda não implementado");
                                break;
                        }
                        break;
                    case "ct":
                        extra = mensagemServidor;
                        mensagemServidor = Servidor.inputChute(mensagemCliente[1].charAt(0));
                        System.out.println("Retornando resultado do chute: " + mensagemServidor);
                        
                        if(extra != mensagemServidor){
                            user.addPts(mensagemServidor);
                        }else{
                            user.rmvPts(Servidor.count);
                        }

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
        }

    }

    private String[] dividirEntrada(String entrada) {
        return entrada.split(":");
    }

}
