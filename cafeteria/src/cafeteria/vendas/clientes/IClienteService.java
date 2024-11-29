package cafeteria.vendas.clientes;

import java.util.List;

/**
 * Interface para gerenciamento de clientes.
 */
public interface IClienteService {

    /**
     * Adiciona um cliente ao sistema.
     *
     * @param cliente o cliente a ser inserido
     */
    void inserirCliente(Cliente cliente);

    /**
     * Atualiza as informações de um cliente existente.
     *
     * @param cliente o cliente a ser atualizado
     */
    void atualizarCliente(Cliente cliente);

    /**
     * Remove um cliente pelo ID.
     *
     * @param id o ID do cliente a ser removido
     */
    void removerCliente(int id);

    /**
     * Busca um cliente pelo ID.
     *
     * @param id o ID do cliente a ser buscado
     * @return o cliente encontrado ou {@code null} se não existir
     */
    Cliente pesquisarCliente(int id);

    /**
     * Retorna a lista de todos os clientes cadastrados.
     *
     * @return lista de clientes
     */
    List<Cliente> listarClientes();
}
