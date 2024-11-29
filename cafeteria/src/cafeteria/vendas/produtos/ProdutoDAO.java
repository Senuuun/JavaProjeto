package cafeteria.vendas.produtos;

import java.util.HashMap;
import java.util.Map;

public class ProdutoDAO {

    private Map<Integer, Produto> produtos = new HashMap<>();
    private int idCounter = 1;

    public void inserir(Produto produto) {
        produto.setId(idCounter++);
        produtos.put(produto.getId(), produto);
        System.out.println("Produto inserido: " + produto.getNome());
    }

    public void atualizar(Produto produto) {
        if (produtos.containsKey(produto.getId())) {
            produtos.put(produto.getId(), produto);
            System.out.println("Produto atualizado: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public Produto buscarPorId(int id) {
        return produtos.get(id);
    }

    public void excluir(int id) {
        if (produtos.containsKey(id)) {
            produtos.remove(id);
            System.out.println("Produto excluído.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
