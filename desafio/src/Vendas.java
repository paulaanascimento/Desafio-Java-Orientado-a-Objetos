import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Vendas {
    private Vendedor vendedor;
    private Cliente cliente;
    private String codigo;
    private List<Produto> listaProdutos;
    private double totalCompra;
    private String dataRegistro;

    public Vendas(Vendedor vendedor, Cliente cliente, String codigo, List<Produto> listaprodutos, double totalCompra) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.codigo = codigo;
        this.listaProdutos = listaprodutos;
        this.totalCompra = totalCompra;
        setDataRegistro();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    private void setDataRegistro() {
        LocalDate data = LocalDate.now();
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyy");
        this.dataRegistro = data.format(dataFormatada);
    }
}
