package cafeteria.vendas;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private List<Venda> vendas;
    private int idAtual;

    public VendaDAO() {
        this.vendas = new ArrayList<>();
        this.idAtual = 1;
    }

    public void salvar(Venda venda) {
        if (venda.getId() == 0) {
            venda.setId(idAtual++);
        }
        vendas.add(venda);
    }

    public Venda buscarPorId(int id) {
        return vendas.stream()
                     .filter(v -> v.getId() == id)
                     .findFirst()
                     .orElse(null);
    }

    public List<Venda> listarTodas() {
        return new ArrayList<>(vendas);
    }

    public void remover(int id) {
        vendas.removeIf(v -> v.getId() == id);
    }
}
