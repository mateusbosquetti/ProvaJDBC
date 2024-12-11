public class Jogador {

    private Clube clube;
    private int idade;
    private String nome;
    private int idJogador;
    private String posicao;

    public Jogador(Clube clube, int idade, String nome, int idJogador, String posicao) {
        this.clube = clube;
        this.idade = idade;
        this.nome = nome;
        this.idJogador = idJogador;
        this.posicao = posicao;
    }

    public Jogador(Clube clube, int idade, String nome, String posicao) {
        this.clube = clube;
        this.idade = idade;
        this.nome = nome;
        this.posicao = posicao;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "clube=" + clube.getIdClube() +
                ", idade=" + idade +
                ", nome='" + nome + '\'' +
                ", idJogador=" + idJogador +
                ", posicao='" + posicao + '\'' +
                '}';
    }
}
