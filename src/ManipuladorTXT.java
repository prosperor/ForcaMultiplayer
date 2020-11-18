import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorTXT{
    private FileReader texto;
    private boolean permissao;
    private List<StringBuilder> palavras;
    private int apontador;

    public ManipuladorTXT(){
        permissao = true;
        apontador = -1;
    }

    public boolean criarArquivo(String caminho, String _arquivo){
        if (permissao){
            File diretorio = new File(caminho);
            if (diretorio.exists()){
                File arquivo = new File(caminho, _arquivo.concat(".txt"));
                boolean out = false;
                try {
                    out = arquivo.createNewFile();
                }catch (IOException e){
                    System.out.println(e.getCause());
                }
                if (out){
                    arquivo.setReadable(true);
                    arquivo.setWritable(true);
                    try {
                        texto = new FileReader(arquivo);
                    }catch (IOException e){
                        System.out.println(e.getCause());
                        return false;
                    }
                    alterarPermissao();
                    return true;
                }else {
                    System.out.println("Arquivo já existente");
                    return false;
                }
            }else {
                System.out.println("Caminho não encontrado");
                return false;
            }
        }else {
            System.out.println("Operação bloqueada");
            return false;
        }
    }

    public boolean usarArquivo(String caminho, String _arquivo){
        if (permissao){
            File diretorio = new File(caminho);
            if (diretorio.exists()){
                File arquivo = new File(caminho, _arquivo.concat(".txt"));
                if (arquivo.exists()){
                    if (arquivo.canRead()){
                        if (arquivo.canWrite()){
                            try {
                                texto = new FileReader(arquivo);
                            }catch (IOException e){
                                System.out.println(e.getCause());
                                return false;
                            }
                            alterarPermissao();
                            return true;
                        }else {
                            System.out.println("Não podemos escrever aqui");
                            return false;
                        }
                    }else {
                        System.out.println("Não pode ser lido");
                        return false;
                    }
                }else {
                    System.out.println("O arquivo é inextistente");
                    return false;
                }
            }else {
                System.out.println("Caminho não encontrado");
                return false;
            }
        }else {
            System.out.println("Operação bloqueada");
            return false;
        }
    }

    private void alterarPermissao(){
        if (permissao){
            permissao = false;
        }else {
            permissao = true;
        }
    }

    public void getPalavras(){
        if (!permissao) {
            BufferedReader saida = new BufferedReader(texto);
            palavras = new ArrayList<>();
            apontador = -1;
            try {
                while (saida.ready()) {
                    StringBuilder item = new StringBuilder(saida.readLine());
                    palavras.add(item);
                }
            } catch (IOException e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                System.out.println("Deu erro");
            }
        }
    }


    public StringBuilder getPalavra(){
        if (palavras.size() > 0 && !((apontador+1) == palavras.size())){
            return palavras.get(++apontador);
        }else if (palavras.size() == 0){
            return new StringBuilder(" ");
        } else {
            apontador = -1;
            return getPalavra();
        }
    }


}
