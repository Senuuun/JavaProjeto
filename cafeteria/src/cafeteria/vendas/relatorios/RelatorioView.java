package cafeteria.vendas.relatorios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RelatorioView extends JInternalFrame implements RelatorioExportavelEmArquivoTexto {

    private static final int POSICAO_X_INICIAL = 90;
    private static final int POSICAO_Y_INICIAL = 90;
    private static final int LARGURA = 580;
    private static final int ALTURA = 190;

    private static final long serialVersionUID = 1L;

    private JTextField nomeRelatorio;
    private JTextField destinoCaminhoAbsoluto;
    private File destinoSelecionado = null;

    private JButton btExportar;
    private JButton btCancelar;
    private JButton btSelecionarDestino;

    private String nomeRelatorioString = "Relatório de Vendas"; // Nome fixo para o relatório de exemplo

    /**
     * Cria a janela para exportação do relatório
     */
    public RelatorioView() {

        setClosable(true);
        setIconifiable(true);
        setSize(LARGURA, ALTURA);
        setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
        setTitle("Exportador de Relatório");
        getContentPane().setLayout(null);

        JLabel lbId = new JLabel("Relatório:");
        lbId.setBounds(31, 40, 60, 17);
        getContentPane().add(lbId);

        nomeRelatorio = new JTextField();
        nomeRelatorio.setBounds(109, 38, 430, 21);
        getContentPane().add(nomeRelatorio);
        nomeRelatorio.setColumns(10);
        nomeRelatorio.setEditable(false);
        nomeRelatorio.setText(nomeRelatorioString);

        JLabel lbDestino = new JLabel("Destino:");
        lbDestino.setBounds(31, 73, 60, 17);
        getContentPane().add(lbDestino);

        destinoCaminhoAbsoluto = new JTextField();
        destinoCaminhoAbsoluto.setBounds(109, 71, 266, 21);
        getContentPane().add(destinoCaminhoAbsoluto);
        destinoCaminhoAbsoluto.setColumns(10);
        destinoCaminhoAbsoluto.setEditable(false);

        btExportar = new JButton("Exportar");
        btExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickExportar();
            }
        });
        btExportar.setBounds(434, 107, 105, 27);
        getContentPane().add(btExportar);
        btExportar.setEnabled(false);

        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCancelar();
            }
        });
        btCancelar.setBounds(316, 107, 105, 27);
        getContentPane().add(btCancelar);

        btSelecionarDestino = new JButton("Selecionar Destino");
        btSelecionarDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickSelecionarDestino();
            }
        });
        btSelecionarDestino.setBounds(387, 68, 152, 27);
        getContentPane().add(btSelecionarDestino);
    }

    /**
     * Executa as tarefas para selecionar o destino do relatório
     */
    protected void onClickSelecionarDestino() {
        // Criação do JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Obtendo o arquivo selecionado
            destinoSelecionado = fileChooser.getSelectedFile();
            destinoCaminhoAbsoluto.setText(destinoSelecionado.getAbsolutePath());
            btExportar.setEnabled(true);
        }
    }

    /**
     * Executa as tarefas para cancelar a geração do relatório
     */
    protected void onClickCancelar() {
        this.dispose();
    }

    /**
     * Executa as tarefas para exportar o relatório para o destino selecionado
     */
    protected void onClickExportar() {
        if (destinoSelecionado != null) {
            exportar(destinoSelecionado);
        } else {
            System.err.println("Nenhum destino selecionado.");
        }
    }

    /**
     * Método da interface RelatorioExportavelEmArquivoTexto para retornar o nome do relatório
     */
    @Override
    public String getNomeRelatorio() {
        return nomeRelatorioString;
    }

    /**
     * Método da interface RelatorioExportavelEmArquivoTexto para exportar o relatório para um arquivo
     */
    @Override
    public void exportar(File destino) {
        try (FileWriter writer = new FileWriter(destino)) {
            // Escrevendo o conteúdo do relatório no arquivo
            writer.write("Relatório: " + nomeRelatorioString + "\n");
            writer.write("Conteúdo do relatório gerado...\n");
            writer.write("Mais dados podem ser inseridos conforme a necessidade.\n");
            // Aqui você pode adicionar mais dados para o relatório conforme necessário
            System.out.println("Relatório exportado com sucesso para: " + destino.getAbsolutePath());
            this.dispose(); // Fecha a janela após a exportação
        } catch (IOException e) {
            System.err.println("Erro ao exportar relatório: " + e.getMessage());
        }
    }
}
