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

    public void addPts(String _palavra){
        pontos = PtsManipulator.add(pontos, _palavra);
    }

    public void rmvPts(int _count){
        pontos = PtsManipulator.rmv(pontos, _count);
    }
}

