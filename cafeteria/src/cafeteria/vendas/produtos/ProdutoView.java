package cafeteria.vendas.produtos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ProdutoView extends JInternalFrame {

    private static final String TITULO = "Cadastro de Produto";

    private static final int POSICAO_X_INICIAL = 60;
    private static final int POSICAO_Y_INICIAL = 60;

    private static final int LARGURA = 580;
    private static final int ALTURA = 300;

    private static final long serialVersionUID = 1L;

    private JTextField id;
    private JTextField nome;
    private JComboBox<UnidadeMedida> medida;
    private JFormattedTextField preco;
    private JTextField estoque;
    private JCheckBox temEstoque;

    private JButton btSalvar;
    private JButton btVoltar;
    private JButton btNovoProduto;
    private JButton btPesquisar;

    private IProdutoService service;

    public ProdutoView(IProdutoService service) {
        this.service = service;

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

        JLabel lbMedida = new JLabel("Medida:");
        lbMedida.setBounds(31, 106, 60, 17);
        getContentPane().add(lbMedida);

        medida = new JComboBox<>(UnidadeMedida.values());
        medida.setBounds(109, 104, 114, 21);
        getContentPane().add(medida);

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickSalvar();
            }
        });
        btSalvar.setBounds(434, 229, 105, 27);
        getContentPane().add(btSalvar);

        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickVoltar();
            }
        });
        btVoltar.setBounds(322, 229, 105, 27);
        getContentPane().add(btVoltar);

        btNovoProduto = new JButton("Novo Produto");
        btNovoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickIncluirNovoProduto();
            }
        });
        btNovoProduto.setBounds(400, 35, 139, 27);
        getContentPane().add(btNovoProduto);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickPesquisar();
            }
        });
        btPesquisar.setBounds(235, 35, 96, 27);
        getContentPane().add(btPesquisar);

        JLabel lbPreco = new JLabel("Preço:");
        lbPreco.setBounds(31, 136, 60, 17);
        getContentPane().add(lbPreco);

        JLabel lbEstoque = new JLabel("Estoque:");
        lbEstoque.setBounds(31, 205, 60, 17);
        getContentPane().add(lbEstoque);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        preco = new JFormattedTextField(numberFormat);
        preco.setHorizontalAlignment(SwingConstants.RIGHT);
        preco.setBounds(109, 137, 114, 21);
        getContentPane().add(preco);
        preco.setColumns(10);

        estoque = new JTextField();
        estoque.setHorizontalAlignment(SwingConstants.RIGHT);
        estoque.setBounds(109, 203, 114, 21);
        getContentPane().add(estoque);
        estoque.setColumns(10);

        temEstoque = new JCheckBox("Tem estoque?");
        temEstoque.setBounds(26, 166, 114, 25);
        getContentPane().add(temEstoque);
    }

    public void setupConsultar() {
        btSalvar.setEnabled(false);
        btVoltar.setEnabled(false);
        btNovoProduto.setEnabled(true);
        btPesquisar.setEnabled(true);

        id.setEnabled(true);
        nome.setEnabled(false);
        medida.setEnabled(false);
        preco.setEnabled(false);
        temEstoque.setEnabled(false);
        estoque.setEnabled(false);
    }

    protected void onClickPesquisar() {
        try {
            int produtoId = Integer.parseInt(id.getText());
            Produto produto = service.buscarProdutoPorId(produtoId);
            if (produto != null) {
                nome.setText(produto.getNome());
                medida.setSelectedItem(produto.getMedida());
                preco.setValue(produto.getPreco());
                estoque.setText(String.valueOf(produto.getEstoque()));
                temEstoque.setSelected(produto.isTemEstoque());
                setupConsultar();
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido.");
        }
    }

    protected void onClickIncluirNovoProduto() {
        nome.setText("");
        preco.setValue(0);
        estoque.setText("");
        temEstoque.setSelected(false);
        medida.setSelectedIndex(0);

        id.setEnabled(false);
        nome.setEnabled(true);
        medida.setEnabled(true);
        preco.setEnabled(true);
        temEstoque.setEnabled(true);
        estoque.setEnabled(true);

        btSalvar.setEnabled(true);
        btPesquisar.setEnabled(false);
        btVoltar.setEnabled(true);
    }

    protected void onClickVoltar() {
        setupConsultar();
    }

    protected void onClickSalvar() {
        String nomeProduto = nome.getText();
        UnidadeMedida unidadeMedida = (UnidadeMedida) medida.getSelectedItem();
        double precoProduto = ((Number) preco.getValue()).doubleValue();
        int estoqueProduto = Integer.parseInt(estoque.getText());
        boolean temEstoqueProduto = temEstoque.isSelected();

        Produto produto = new Produto(0, nomeProduto, unidadeMedida, precoProduto, estoqueProduto, temEstoqueProduto);
        service.salvarProduto(produto);

        JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
        setupConsultar();
    }
}
