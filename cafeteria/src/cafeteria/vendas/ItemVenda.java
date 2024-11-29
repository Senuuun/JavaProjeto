package cafeteria.vendas;

public class ItemVenda {
    private int ItemVendaId;
    private String nomeProduto;
    private double precoUnitario;
    private int quantidade;
    private double totalItem;

    public ItemVenda(int ItemVendaId, String nomeProduto, double precoUnitario, int quantidade) {
        this.ItemVendaId = ItemVendaId;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        calcularTotalItem();
    }

    public int getItemVendaId() {
        return ItemVendaId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularTotalItem();
    }

    public double getTotalItem() {
        return totalItem;
    }

    private void calcularTotalItem() {
        this.totalItem = precoUnitario * quantidade;
    }
}

