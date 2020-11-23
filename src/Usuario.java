public class Usuario {
    private int ID;
    private String nome;

    public Usuario(int _ID, String _nome){
        this.ID = _ID;
        this.nome = _nome;
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
}

