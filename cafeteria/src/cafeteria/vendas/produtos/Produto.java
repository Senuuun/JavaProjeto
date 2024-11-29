package cafeteria.vendas.produtos;

public class Produto {
    private int id;
    private String nome;
    private UnidadeMedida medida;
    private double preco;
    private int estoque;
    private boolean temEstoque;

    // Construtor
    public Produto(int id, String nome, UnidadeMedida medida, double preco, int estoque, boolean temEstoque) {
        this.id = id;
        this.nome = nome;
        this.medida = medida;
        this.preco = preco;
        this.estoque = estoque;
        this.temEstoque = temEstoque;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeMedida getMedida() {
        return medida;
    }

    public void setMedida(UnidadeMedida medida) {
        this.medida = medida;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isTemEstoque() {
        return temEstoque;
    }

    public void setTemEstoque(boolean temEstoque) {
        this.temEstoque = temEstoque;
    }
}
