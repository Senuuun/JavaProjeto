package cafeteria.vendas;

import java.util.List;

public interface IVendaService {
    void registrarVenda(Venda venda);
    Venda buscarVendaPorId(int id);
    List<Venda> listarVendas();
    void removerVenda(int id);
}
