package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    @Test
    public void deveRetornarOPreco(){
        Produto produto = new Produto("Maçã", 2, 1);
        double resultado = produto.getPreco();
        Assertions.assertEquals(2, resultado);
    }

    @Test
    public void deveRetornarAQuantidade(){
        Produto produto = new Produto("Barra de Chocolate", 5, 3);
        int resultado = produto.getQuantidade();
        Assertions.assertEquals(3, resultado);
    }

    @Test
    public void deveRetornarOProdutoEmString(){
        Produto produto = new Produto("Nutella", 20, 1);
        String resultado = produto.toString();
        Assertions.assertEquals("{nome='Nutella', preco=20.0, quantidade=1}", resultado);
    }
}
