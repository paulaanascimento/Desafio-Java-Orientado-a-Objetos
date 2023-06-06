import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String email;

    private List<Vendas> listaVendas = new ArrayList<>();

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Vendas> getListaVendas() {
        return listaVendas;
    }

    public void adicionarVenda(Vendas venda) {
        listaVendas.add(venda);
    }
}
