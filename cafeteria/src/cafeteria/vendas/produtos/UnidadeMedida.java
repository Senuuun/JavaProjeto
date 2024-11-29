package cafeteria.vendas.produtos;

public enum UnidadeMedida {
    UNIDADE("Unidade"),
    LATA("Lata"),
    LITRO("Litro"),
    PACOTE("Pacote"),
    FATIA("Fatia"),
    GARRAFA("Garrafa");

    private final String descricao;

    // Construtor para associar a descrição com cada tipo de unidade
    UnidadeMedida(String descricao) {
        this.descricao = descricao;
    }

    // Método que retorna a descrição de cada unidade de medida
    public String getDescricao() {
        return descricao;
    }

    // Método que retorna o nome amigável da unidade de medida
    @Override
    public String toString() {
        return descricao;
    }
}
