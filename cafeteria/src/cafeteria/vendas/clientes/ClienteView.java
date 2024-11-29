package cafeteria.vendas.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class ClienteView extends JInternalFrame {

    private static final String TITULO = "Cadastro de Cliente";

    private static final int POSICAO_X_INICIAL = 30;
    private static final int POSICAO_Y_INICIAL = 30;

    private static final int LARGURA = 580;
    private static final int ALTURA = 210;

    private static final long serialVersionUID = 1L;

    private JTextField id;
    private JTextField nome;
    private JFormattedTextField telefone;

    private JButton btSalvar;
    private JButton btVoltar;
    private JButton btNovoCliente;
    private JButton btPesquisar;

    private IClienteService clienteService;

    /**
     * Cria a janela do CRUD do cliente
     */
    public ClienteView(IClienteService clienteService) {
        this.clienteService = clienteService;

        setClosable(true);
        setIconifiable(true);
        setSize(LARGURA, ALTURA);
        setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
        setTitle(TITULO);
        getContentPane().setLayout(null);

        JLabel lbId = new JLabel("ID:");
        lbId.setBounds(31, 40, 60, 17);
        getContentPane().add(lbId);

        id = new JTextField();
        id.setHorizontalAlignment(SwingConstants.CENTER);
        id.setBounds(109, 38, 114, 21);
        getContentPane().add(id);
        id.setColumns(10);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(31, 73, 60, 17);
        getContentPane().add(lbNome);

        nome = new JTextField();
        nome.setBounds(109, 71, 430, 21);
        getContentPane().add(nome);
        nome.setColumns(10);

        JLabel lbTelefone = new JLabel("Telefone:");
        lbTelefone.setBounds(31, 106, 60, 17);
        getContentPane().add(lbTelefone);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("(##) #####-####");
            maskFormatter.setPlaceholderCharacter('_');
            telefone = new JFormattedTextField(maskFormatter);
            telefone.setBounds(109, 104, 132, 21);
            getContentPane().add(telefone);
            telefone.setColumns(10);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickSalvar();
            }
        });
        btSalvar.setBounds(434, 126, 105, 27);
        getContentPane().add(btSalvar);

        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickVoltar();
            }
        });
        btVoltar.setBounds(317, 126, 105, 27);
        getContentPane().add(btVoltar);

        btNovoCliente = new JButton("Novo Cliente");
        btNovoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickIncluirNovoCliente();
            }
        });
        btNovoCliente.setBounds(400, 35, 139, 27);
        getContentPane().add(btNovoCliente);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickPesquisar();
            }
        });
        btPesquisar.setBounds(235, 35, 96, 27);
        getContentPane().add(btPesquisar);
    }

    /**
     * Prepara o frame para a ação de consultar
     */
    public void setupConsultar() {
        // configura os botões de ação
        btSalvar.setEnabled(false);
        btVoltar.setEnabled(false);
        btNovoCliente.setEnabled(true);
        btPesquisar.setEnabled(true);

        // configura o comportamento dos campos
        id.setEnabled(true);
        nome.setEnabled(false);
        telefone.setEnabled(false);
    }

    /**
     * Executa as tarefas para pesquisar um cliente
     */
    protected void onClickPesquisar() {
        try {
            int clienteId = Integer.parseInt(id.getText());
            Cliente cliente = clienteService.pesquisarCliente(clienteId);

            if (cliente != null) {
                nome.setText(cliente.getNome());
                telefone.setText(cliente.getTelefone());
                nome.setEnabled(true);
                telefone.setEnabled(true);
                btSalvar.setEnabled(true);
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido.");
        }
    }

    /**
     * Executa as tarefas para preparar a interface para a inclusão de um novo cliente
     */
    protected void onClickIncluirNovoCliente() {
        id.setText("");
        nome.setText("");
        telefone.setText("");

        nome.setEnabled(true);
        telefone.setEnabled(true);
        btSalvar.setEnabled(true);
    }

    /**
     * Executa as tarefas para voltar sem salvar
     */
    protected void onClickVoltar() {
        setupConsultar();
    }

    /**
     * Executa as tarefas para salvar a inclusão ou alteração de um cliente
     */
    protected void onClickSalvar() {
        try {
            int clienteId = id.getText().isEmpty() ? 0 : Integer.parseInt(id.getText()); // 0 para identificar novo cliente
            String clienteNome = nome.getText();
            String clienteTelefone = telefone.getText();

            if (clienteNome.isEmpty() || clienteTelefone.isEmpty()) {
                System.out.println("Nome e telefone são obrigatórios.");
                return;
            }

            Cliente cliente = new Cliente(clienteId, clienteNome, clienteTelefone);

            if (clienteId == 0) {
                clienteService.inserirCliente(cliente);
                System.out.println("Cliente cadastrado com sucesso.");
            } else {
                clienteService.atualizarCliente(cliente);
                System.out.println("Cliente atualizado com sucesso.");
            }

            setupConsultar();
        } catch (NumberFormatException ex) {
            System.out.println("Erro ao salvar cliente: ID inválido.");
        }
    }
}

