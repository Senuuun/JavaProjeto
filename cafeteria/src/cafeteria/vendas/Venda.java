package cafeteria.vendas;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private int clienteId;
    private double desconto;
    private List<ItemVenda> itens;
    private double total;

    public Venda() {
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void addItem(ItemVenda item) {
        this.itens.add(item);
        calcularTotal();
    }

    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    private void calcularTotal() {
        total = itens.stream()
                     .mapToDouble(ItemVenda::getTotalItem)
                     .sum() - desconto;
    }
}
