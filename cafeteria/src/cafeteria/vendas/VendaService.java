package cafeteria.vendas;

import java.util.ArrayList;
import java.util.List;

public class VendaService implements IVendaService {
    private VendaDAO vendaDAO;

    public VendaService(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    @Override
    public void registrarVenda(Venda venda) {
        vendaDAO.salvar(venda);
    }

    @Override
    public Venda buscarVendaPorId(int id) {
        return vendaDAO.buscarPorId(id);
    }

    @Override
    public List<Venda> listarVendas() {
        return vendaDAO.listarTodas();
    }

    @Override
    public void removerVenda(int id) {
        vendaDAO.remover(id);
    }
}
