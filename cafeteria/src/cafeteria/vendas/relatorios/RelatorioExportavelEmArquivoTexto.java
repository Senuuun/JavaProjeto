package cafeteria.vendas.relatorios;

import java.io.File;

/**
 * Interface para objetos que podem gerar e exportar relatórios em arquivo de texto.
 * Qualquer classe que implementar esta interface deve fornecer a implementação dos métodos para obter o nome do relatório e exportar os dados para um arquivo.
 */
public interface RelatorioExportavelEmArquivoTexto {

    /**
     * Retorna o nome do relatório a ser gerado.
     * 
     * @return O nome do relatório.
     */
    String getNomeRelatorio();

    /**
     * Exporta o conteúdo do relatório para um arquivo de texto.
     * 
     * @param destino O arquivo onde o relatório será exportado.
     */
    void exportar(File destino);
}

