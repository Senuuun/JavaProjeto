package cafeteria.vendas.produtos;

public class ProdutoService implements IProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    @Override
    public void salvarProduto(Produto produto) {
        if (produto.getId() == 0) {
            // Se o ID é 0, significa que é um novo produto
            produtoDAO.inserir(produto);
        } else {
            // Se o ID não é 0, significa que é uma atualização
            produtoDAO.atualizar(produto);
        }
    }

    @Override
    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    @Override
    public void excluirProduto(int id) {
        produtoDAO.excluir(id);
    }
}
