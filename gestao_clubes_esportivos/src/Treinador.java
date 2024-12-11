public class Treinador {
    private String nome;
    private int experiencia;
    private int idTreinador;

    //Atributo que não faz sentido, por ser 1 para 1, apenas 1 das classes deve ter a referência (FK), nesse caso o ideal é a classe clube ter, então por isso abstrai esse atributo do código
    private Clube clube; //Abstraido do meu código

    public Treinador(String nome, int experiencia, int idTreinador) {
        this.nome = nome;
        this.experiencia = experiencia;
        this.idTreinador = idTreinador;
    }

    public Treinador(String nome, int experiencia) {
        this.nome = nome;
        this.experiencia = experiencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "nome='" + nome + '\'' +
                ", experiencia=" + experiencia +
                ", idTreinador=" + idTreinador +
                '}';
    }
}
