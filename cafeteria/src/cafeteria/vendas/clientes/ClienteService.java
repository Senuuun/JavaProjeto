package cafeteria.vendas.clientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do serviço de clientes.
 */
public class ClienteService implements IClienteService {

    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        if (pesquisarCliente(cliente.getId()) != null) {
            throw new IllegalArgumentException("Cliente com o mesmo ID já existe.");
        }
        clientes.add(cliente);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        Cliente existente = pesquisarCliente(cliente.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para atualização.");
        }
        existente.setNome(cliente.getNome());
        existente.setTelefone(cliente.getTelefone());
    }

    @Override
    public void removerCliente(int id) {
        Cliente cliente = pesquisarCliente(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para remoção.");
        }
        clientes.remove(cliente);
    }

    @Override
    public Cliente pesquisarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista
    }
}
