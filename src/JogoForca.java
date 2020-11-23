import java.util.List;

public class JogoForca {
    private String word;
    private String wordChute;
    private ManipulaTxt manipulador = new ManipulaTxt();

    public JogoForca(){
        startNewGame();
    }

    public String chute(Character letra){
        letra = letra.toString().toUpperCase().charAt(0);
        
        StringBuilder saida = new StringBuilder(wordChute);
        if (Character.isAlphabetic(letra)){
            if (word.contains(letra.toString())){
                if (!wordChute.contains(letra.toString())){
                    for (int x=0; x < word.length(); x++){
                        if (word.charAt(x) == letra){
                            saida.setCharAt(x,letra);
                        }
                    }
                }
            }
        }
        wordChute = saida.toString();
        return wordChute;
    }

    public String getWordChute(){
        return wordChute;
    }

    public boolean isEnd(){
        return (word.equals(wordChute));
    }

    public void startNewGame(){
        word = manipulador.getPalavra();
        wordChute = preparadorDePalavraMascarada(word);
    }

    private String preparadorDePalavraMascarada(String entrada){
        StringBuilder saida = new StringBuilder();
        String sinal = "_";
        saida.append(sinal.repeat(entrada.length()));
        return saida.toString();
    }

}
