

import java.util.ArrayList;
import java.util.List;

public class Clube {

    private int idClube;
    private String nome;

    //Atributo que não faz sentido, por ser N para N, é criado uma nova tabela e não salvo na propria tabela Clube
    private List<Liga> ligas; //Abstraido do meu código

    //Atributo que não faz sentido, por ser 1 para N, a classe jogador recebe o atributo clube, nao faz sentido o clube receber varios jogadores
    private List<Jogador> jogadores; //Abstraido do meu código
    private Treinador treinador;
    private String dataFundacao;

    public Clube(String nome, Treinador treinador, String dataFundacao) {
        this.nome = nome;
        this.ligas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.treinador = treinador;
        this.dataFundacao = dataFundacao;
    }

    public Clube(int idClube, String nome, Treinador treinador, String dataFundacao) {
        this.idClube = idClube;
        this.nome = nome;
        this.ligas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.treinador = treinador;
        this.dataFundacao = dataFundacao;
    }

    public int getIdClube() {
        return idClube;
    }

    public void setIdClube(int idClube) {
        this.idClube = idClube;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Clube{" +
                "idClube=" + idClube +
                ", nome='" + nome + '\'' +
                ", treinador=" + treinador +
                ", dataFundacao='" + dataFundacao + '\'' +
                '}';
    }
}
