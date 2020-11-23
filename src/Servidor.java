import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private List<Usuario> jogadores;
    private JogoForca jogo;

    public Servidor(){
        jogadores = new ArrayList<>();
        jogo = new JogoForca();
    }

    public String chutar(Character letra){
        return jogo.chute(letra);
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

    public void reiniciar(){
        jogo.startNewGame();
    }
}
