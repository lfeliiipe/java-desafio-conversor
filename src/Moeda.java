public class Moeda {
    private String sigla;
    private String nome;

    public Moeda(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome + " [" + this.sigla + "]";
    }
}
