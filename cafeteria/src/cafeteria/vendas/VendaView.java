package cafeteria.vendas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

import cafeteria.vendas.clientes.IClienteService;
import cafeteria.vendas.produtos.IProdutoService;
import cafeteria.vendas.produtos.Produto;

public class VendaView extends JInternalFrame {

    private static final String TITULO = "Registro de Venda";

    private static final int POSICAO_X_INICIAL = 120;
    private static final int POSICAO_Y_INICIAL = 120;

    private static final int LARGURA = 750;
    private static final int ALTURA = 675;

    private static final int COLUNA_SELECAO = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_VALOR_UNITARIO = 2;
    private static final int COLUNA_QUANTIDADE = 3;
    private static final int COLUNA_VALOR_TOTAL = 4;

    private static final long serialVersionUID = 1L;

    private JTextField id;
    private JTextField nomeCliente;
    private JComboBox<Produto> produto;
    private JFormattedTextField quantidade;
    private JFormattedTextField desconto;
    private JFormattedTextField totalVenda;
    private JTextField medida;

    private JTable table;
    private DefaultTableModel model;

    private JButton btConfirmar;
    private JButton btCancelar;
    private JButton btBuscarCliente;
    private JButton btAdicionarItem;
    private JButton btRemoverItensSelecionados;

    private IVendaService vendaService = null;
    private IClienteService clienteService = null;
    private IProdutoService produtoService = null;

    private List<ItemVenda> itens;

    public VendaView(IVendaService vendaService, IClienteService clienteService, IProdutoService produtoService) {
        this.vendaService = vendaService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.itens = new ArrayList<>();

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        setClosable(true);
        setIconifiable(true);
        setSize(LARGURA, ALTURA);
        setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
        setTitle(TITULO);
        getContentPane().setLayout(null);

        // Componentes da tela
        JLabel lbId = new JLabel("Cliente ID:");
        lbId.setBounds(31, 40, 72, 17);
        getContentPane().add(lbId);

        id = new JTextField();
        id.setHorizontalAlignment(SwingConstants.CENTER);
        id.setBounds(109, 38, 114, 21);
        getContentPane().add(id);
        id.setColumns(10);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(31, 73, 60, 17);
        getContentPane().add(lbNome);

        nomeCliente = new JTextField();
        nomeCliente.setBounds(109, 71, 600, 21);
        getContentPane().add(nomeCliente);
        nomeCliente.setColumns(10);
        nomeCliente.setEditable(false);

        JLabel lbProduto = new JLabel("Produto:");
        lbProduto.setBounds(31, 106, 60, 17);
        getContentPane().add(lbProduto);

        // Lista de produtos - Deve ser carregada do serviço de produtos
        List<Produto> produtos = new ArrayList<>(); // Substitua por produtosService.getProdutos()
        produto = new JComboBox<>(produtos.toArray(new Produto[0]));
        produto.setBounds(109, 104, 600, 21);
        getContentPane().add(produto);
        produto.addItemListener(new ItemListener() {
        	@Override
        	public void itemStateChanged(ItemEvent event) {
        	    if (event.getStateChange() == ItemEvent.SELECTED) {
        	        Produto produtoSelecionado = (Produto) event.getItem();
        	        // Atualiza a medida com o valor do produto, garantindo que seja uma string
        	        medida.setText(String.valueOf(produtoSelecionado.getMedida()));
        	    }
        	}});


        btConfirmar = new JButton("Registrar Venda");
        btConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickRegistrarVenda();
            }
        });
        btConfirmar.setBounds(558, 585, 151, 27);
        getContentPane().add(btConfirmar);

        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCancelar();
            }
        });
        btCancelar.setBounds(441, 585, 105, 27);
        getContentPane().add(btCancelar);

        btBuscarCliente = new JButton("Buscar Cliente");
        btBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBuscarCliente();
            }
        });
        btBuscarCliente.setBounds(235, 35, 120, 27);
        getContentPane().add(btBuscarCliente);

        JLabel lbMedida = new JLabel("Medida:");
        lbMedida.setBounds(31, 139, 60, 17);
        getContentPane().add(lbMedida);

        JLabel lbQuantidade = new JLabel("Quantidade:");
        lbQuantidade.setBounds(31, 172, 79, 17);
        getContentPane().add(lbQuantidade);

        medida = new JTextField();
        medida.setBounds(109, 137, 114, 21);
        getContentPane().add(medida);
        medida.setColumns(10);
        medida.setEditable(false);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        quantidade = new JFormattedTextField(integerFormat);
        quantidade.setHorizontalAlignment(SwingConstants.RIGHT);
        quantidade.setBounds(109, 170, 114, 21);
        getContentPane().add(quantidade);
        quantidade.setColumns(10);

        JLabel lbDesconto = new JLabel("Desconto:");
        lbDesconto.setBounds(31, 534, 60, 17);
        getContentPane().add(lbDesconto);

        desconto = new JFormattedTextField(numberFormat);
        desconto.setHorizontalAlignment(SwingConstants.RIGHT);
        desconto.setBounds(109, 529, 114, 21);
        getContentPane().add(desconto);
        desconto.setColumns(10);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(31, 563, 60, 17);
        getContentPane().add(lblTotal);

        totalVenda = new JFormattedTextField(numberFormat);
        totalVenda.setHorizontalAlignment(SwingConstants.RIGHT);
        totalVenda.setBounds(109, 561, 114, 21);
        getContentPane().add(totalVenda);
        totalVenda.setColumns(10);
        totalVenda.setEditable(false);

        btAdicionarItem = new JButton("Adicionar Produto");
        btAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                onClickAdicionarItemVenda();
            }
        });
        btAdicionarItem.setBounds(507, 203, 202, 27);
        getContentPane().add(btAdicionarItem);

        btRemoverItensSelecionados = new JButton("Remover Itens Selecionados");
        btRemoverItensSelecionados.setBounds(507, 516, 202, 27);
        btRemoverItensSelecionados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickRemoverItensSelecionados();
            }
        });
        getContentPane().add(btRemoverItensSelecionados);

        // Criação da tabela
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[] { "", "Produto", "Preço", "Quantidade", "Total" }, 0) {
            private static final long serialVersionUID = -213504134060145107L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == COLUNA_SELECAO) {
                    return Boolean.class;
                } else if (columnIndex == COLUNA_VALOR_UNITARIO || columnIndex == COLUNA_VALOR_TOTAL) {
                    return Double.class;
                } else if (columnIndex == COLUNA_QUANTIDADE) {
                    return Integer.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(COLUNA_NOME).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(COLUNA_VALOR_UNITARIO).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(COLUNA_QUANTIDADE).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(COLUNA_VALOR_TOTAL).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBounds(31, 209, 678, 290);
        getContentPane().add(panel);

        // Ajustar medidas de exibição
        model.addRow(new Object[] { Boolean.FALSE, "Produto Exemplo", 20.00, 2, 40.00 });
    }

    protected void onClickRegistrarVenda() {
        // Implementação para registrar a venda
    }

    protected void onClickCancelar() {
        dispose();
    }

    protected void onClickBuscarCliente() {
        // Implementação para buscar o cliente
    }

    protected void onClickAdicionarItemVenda() {
        Produto produtoSelecionado = (Produto) produto.getSelectedItem();

        if (produtoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto válido.");
            return;
        }

        Integer quantidadeSelecionada = (Integer) quantidade.getValue();
        if (quantidadeSelecionada == null || quantidadeSelecionada <= 0) {
            JOptionPane.showMessageDialog(this, "Informe uma quantidade válida.");
            return;
        }

        // Criando um novo item de venda
        String nomeProduto = produtoSelecionado.getNome();
        double precoUnitario = produtoSelecionado.getPreco();
        
        // Vamos gerar um ID fictício para o ItemVenda (poderia ser um contador ou outro identificador único)
        int itemVendaId = itens.size() + 1; // Exemplo de ID sequencial
        ItemVenda itemVenda = new ItemVenda(itemVendaId, nomeProduto, precoUnitario, quantidadeSelecionada);

        // Adiciona o item à lista de itens
        this.itens.add(itemVenda);

        // Calculando o valor total do item
        double valorTotal = itemVenda.getTotalItem();

        // Adiciona o item à tabela
        model.addRow(new Object[] {
            Boolean.FALSE,
            nomeProduto,
            precoUnitario,
            quantidadeSelecionada,
            valorTotal
        });

        // Limpa os campos de entrada
        quantidade.setValue(null);
        produto.setSelectedIndex(0);
    }

    protected void onClickRemoverItensSelecionados() {
        int[] selectedRows = table.getSelectedRows();
        List<ItemVenda> itensRemover = new ArrayList<>();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int row = selectedRows[i];
            itensRemover.add(itens.get(row));
            model.removeRow(row);
        }
        


        itens.removeAll(itensRemover);
    }
}
