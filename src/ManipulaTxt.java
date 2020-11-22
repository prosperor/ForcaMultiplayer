import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManipulaTxt {
    private String palavra;
    private int iLinhas = -1;

    public ManipulaTxt(){
        try {
            FileReader dicio = new FileReader("src/dicio.txt");
            BufferedReader lerArq = new BufferedReader(dicio);
            palavra = lerArq.readLine();
            while (palavra != null){
                palavra = lerArq.readLine();
                iLinhas++;
            }
            dicio.close();
            setPalavra();
        }catch (IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }

    public void setPalavra(){
        try {
            palavra = (Files.readAllLines(Paths.get("src/dicio.txt")).get((int)(Math.random() *  iLinhas))).toLowerCase();
        }catch (IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public String getPalavra(){
        return palavra;
    }

    public String getPalavra(boolean bin){
        setPalavra();
        return palavra;
    }
}
