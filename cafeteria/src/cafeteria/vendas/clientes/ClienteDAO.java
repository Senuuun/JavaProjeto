package cafeteria.vendas.clientes;

import java.util.ArrayList;
import java.util.List;
import cafeteria.vendas.clientes.*;

public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();

    // Inicializando com alguns clientes fictícios
    public ClienteDAO() {
        clientes.add(new Cliente(1, "João", "123456789"));
        clientes.add(new Cliente(2, "Maria", "987654321"));
        clientes.add(new Cliente(3, "Ana", "456123789"));
    }


    public Cliente buscarPorId(int id) {
        return clientes.stream()
                       .filter(cliente -> cliente.getId() == id)
                       .findFirst()
                       .orElse(null); // Retorna null se não encontrar
    }
}
