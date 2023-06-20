package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PessoaTest {

    @Test
    public void deveRetornarONome(){
        Pessoa pessoa = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        String resultado = pessoa.getNome();
        Assertions.assertEquals("Yoongi", resultado);
    }

    @Test
    public void deveRetornarOCpf(){
        Pessoa pessoa = new Vendedor("Yoongi", "17329012017", "yoongi@gmail.com");
        String resultado = pessoa.getCpf();
        Assertions.assertEquals("17329012017", resultado);
    }

    @Test
    public void deveRetornarOEmail(){
        Pessoa pessoa = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        String resultado = pessoa.getEmail();
        Assertions.assertEquals("yoongi@gmail.com", resultado);
    }

    @Test
    public void deveAdicionarVendaALista(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        cliente.adicionarVenda(new Vendas(vendedor, cliente, "8945", produtos, 50));
        List<Vendas> vendas = cliente.getListaVendas();
        Assertions.assertEquals(1, vendas.size());
    }
}
