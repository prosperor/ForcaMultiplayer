package Comum;

public class Usuario {
    private int ID;
    private String nome;
    public int pontos;

    public Usuario(int _ID, String _nome){
        this.ID = _ID;
        this.nome = _nome;
        pontos = 0;
    }

    public int getID(){
        return this.ID;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String _nome){
        this.nome = _nome;
    }

    public void addPts(int tp){
        if(tp == 1){
            pontos = PtsManipulator.add(pontos);
        }else if(tp == 5){
            pontos = PtsManipulator.win(pontos);
        }
    }

    public void rmvPts(int tp){
        if(tp == 1){
            pontos = PtsManipulator.rmv1(pontos);
        }else if(tp == 3){
            pontos = PtsManipulator.rmv(pontos);
        }else if(tp == 5){
            pontos = PtsManipulator.rmv5(pontos);
        }
    }

    public int getPts(){
        return pontos;
    }
}

