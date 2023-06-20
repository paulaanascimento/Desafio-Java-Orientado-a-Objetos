package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VendasTest {
    @Test
    public void deveRetornarOVendedor(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        Vendedor resultado = vendas.getVendedor();
        Assertions.assertEquals(vendedor, resultado);
    }

    @Test
    public void deveRetornarOCliente(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        Cliente resultado = vendas.getCliente();
        Assertions.assertEquals(cliente, resultado);
    }

    @Test
    public void deveRetornarOCodigo(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        String resultado = vendas.getCodigo();
        Assertions.assertEquals("8945", resultado);
    }

    @Test
    public void deveRetornarAListaDeProdutos(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        List<Produto> resultado = vendas.getListaProdutos();
        Assertions.assertEquals(produtos, resultado);
    }

    @Test
    public void deveRetornarOTotalDaCompra(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        double resultado = vendas.getTotalCompra();
        Assertions.assertEquals(50, resultado);
    }

    @Test
    public void deveRetornarADataDeRegistro(){
        Cliente cliente = new Cliente("Yoongi", "17329012017", "yoongi@gmail.com");
        Vendedor vendedor = new Vendedor("Suga", "35884118015", "suga@gmail.com");
        List<Produto> produtos = new ArrayList<>();
        Vendas vendas = new Vendas(vendedor, cliente, "8945", produtos, 50);
        String resultado = vendas.getDataRegistro();
        Assertions.assertEquals("20/06/2023", resultado);
    }
}
