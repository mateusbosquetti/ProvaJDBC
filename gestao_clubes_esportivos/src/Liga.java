import java.util.ArrayList;
import java.util.List;

public class Liga {
    private String nome;
    private int anoFundacao;

    //Atributo que não faz sentido. Por ser N para N, as relações de ligas e clubes devem ser armazenadas em outra tabela. Não faz sentido armazenar como atributo aqui
    //Por que nao faz sentido na minha opnião, porque esse atributo só existe no java, no mysql é criado uma nova tabela, entao por nao salvar no banco, o valor do atributo reseta sempre que a aplicação se inicializa
    private List<Clube> clubes; //Abstraido do meu código
    private int idLiga;

    public Liga(String nome, int anoFundacao, int idLiga) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.clubes = new ArrayList<>();
        this.idLiga = idLiga;
    }

    public Liga(String nome, int anoFundacao) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.clubes = new ArrayList<>();
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public List<Clube> getClubes() {
        return clubes;
    }

    public void setClubes(List<Clube> clubes) {
        this.clubes = clubes;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "nome='" + nome + '\'' +
                ", anoFundacao=" + anoFundacao +
                ", idLiga=" + idLiga +
                '}';
    }
}
