package Comum;

public class PtsManipulator {
    
    public static int add(int _pts, String _palavra){
        if(_palavra.contains("_")){
            return _pts++;
        }else{
            //metodo para adicionar 5 pts a todos os jogadores
            return _pts++;
        }
    }

    public static int rmv(int _pts, int _count){
        if(_count == 7){
            return _pts - 3;
            //metodo para tirar 5 pontos de todos os jogadores
        }else{
            return _pts - 3;
        }
    }

}
