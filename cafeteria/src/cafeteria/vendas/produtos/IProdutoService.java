package cafeteria.vendas.produtos;

public interface IProdutoService {
    void salvarProduto(Produto produto);
    Produto buscarProdutoPorId(int id);
    void excluirProduto(int id);
}
