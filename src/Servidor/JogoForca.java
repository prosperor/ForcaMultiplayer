package Servidor;

import java.util.Arrays;

import Servidor.ManipulaTxt;

import javax.xml.transform.sax.SAXResult;

public class JogoForca {
    private String word;
    private String wordChute, letrasUsadas;
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
        letrasUsadas = "";
        word = manipulador.getPalavra();
        wordChute = preparadorDePalavraMascarada(word);
    }

    private String preparadorDePalavraMascarada(String entrada){
        StringBuilder saida = new StringBuilder();
        String sinal = "_";
        saida.append(sinal.repeat(entrada.length()));
        return saida.toString();
    }

    public String getLetrasUsadas(char letra){
        if (!letrasUsadas.contains(letra+"")) {
            letrasUsadas = (letrasUsadas + letra).toUpperCase().trim();
            letrasUsadas = letrasUsadas.replace(" ", "");

            char[] st = letrasUsadas.toCharArray();
            Arrays.sort(st);
            letrasUsadas = "";

            for (int i = 0; i < st.length; i++) {
                letrasUsadas = letrasUsadas + st[i] + " ";
            }
        }
        return ":Letras usadas - " + letrasUsadas;
    }

    public String getLetrasUsadas(){
        return ":Letras usadas - " + letrasUsadas;
    }

}
