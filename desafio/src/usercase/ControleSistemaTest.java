package usercase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ControleSistemaTest {

    @Test
    public void deveRetornarQueNenhumClienteFoiCadastrado(){
        String resultado = ControleSistema.listarClientes();
        Assertions.assertEquals("Nenhum cliente foi cadastrado!", resultado);
    }

    @Test
    public void deveRetornarQueNenhumVendedorFoiCadastrado(){
        String resultado = ControleSistema.listarVendedores();
        Assertions.assertEquals("Nenhum vendedor foi cadastrado!", resultado);
    }

    @Test
    public void deveRetornarQueNenhumaVendaFoiCadastrada(){
        String resultado = ControleSistema.listarVendas();
        Assertions.assertEquals("Nenhuma venda foi cadastrada!", resultado);
    }

    @Test
    public void deveCadastrarClienteNormalmente(){
        String resultado = ControleSistema.cadastrarCliente("Pamela", "30935613021", "pamela@gmail.com");
        Assertions.assertEquals("Cliente cadastrado com sucesso!", resultado);
    }

    @Test
    public void deveLancarExcecaoParaCpfCadastradoCliente(){
        ControleSistema.cadastrarCliente("Carla", "43451541033", "carla@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarCliente("Marcos", "43451541033", "marcos@gmail.com"));
    }

    @Test
    public void deveRetornarQueOEmailEInvalidoCliente(){
        String resultado = ControleSistema.cadastrarCliente("Diego", "90747903050", "diego.gmail.com");
        Assertions.assertEquals("E-mail inválido!", resultado);
    }

    @Test
    public void deveLancarExcecaoParaEmailCadastradoCliente(){
        ControleSistema.cadastrarCliente("José", "36973842011", "jose@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarCliente("Marcos", "18784566019", "jose@gmail.com"));
    }

    @Test
    public void deveCadastrarVendedorNormalmente(){
        String resultado = ControleSistema.cadastrarVendedor("Marcos", "73924509050", "marcos@gmail.com");
        Assertions.assertEquals("Vendedor cadastrado com sucesso!", resultado);
    }

    @Test
    public void deveLancarExcecaoParaCpfCadastradoVendedor(){
        ControleSistema.cadastrarVendedor("Jessica", "41614015074", "jessica@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarVendedor("Guilherme", "41614015074", "guilherme@gmail.com"));
    }

    @Test
    public void deveRetornarQueOEmailEInvalidoVendedor(){
        String resultado = ControleSistema.cadastrarVendedor("Guilherme", "09146154086", "guilherme.gmail.com");
        Assertions.assertEquals("E-mail inválido!", resultado);
    }

    @Test
    public void deveLancarExcecaoParaEmailCadastradoVendedor(){
        ControleSistema.cadastrarVendedor("Carla", "73083885067", "carla@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarVendedor("Pamela", "16129001002", "carla@gmail.com"));
    }

    @Test
    public void deveMostrarAListaDeCompraDeUmCliente() {
        ControleSistema.cadastrarCliente("Jimin", "07778398060", "jimin@gmail.com");
        String resultado = ControleSistema.pesquisarComprasCliente("07778398060");
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveMostrarAListaDeCompraDeUmCliente2() {
        ControleSistema.cadastrarVendedor("Taehyung", "17283516000", "taehyung@hotmail.com");
        ControleSistema.cadastrarCliente("Hoseok","13087473065", "hoseok@gmail.com");
        ControleSistema.cadastrarVenda("13087473065", "taehyung@hotmail.com", "7539", "oleo", 6, 4);
        String resultado = ControleSistema.pesquisarComprasCliente("13087473065");
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveLancarExcecaoParaClienteNaoEncontrado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ControleSistema.pesquisarComprasCliente("04503617052"));
    }

    @Test
    public void deveMostrarAListaDeCompraDeUmVendedor() {
        ControleSistema.cadastrarVendedor("Jimin", "07778398060", "jimin@gmail.com");
        String resultado = ControleSistema.pesquisarComprasVendedor("jimin@gmail.com");
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveMostrarAListaDeCompraDeUmVendedor2() {
        ControleSistema.cadastrarVendedor("Taehyung", "26168578048", "taehyung@gmail.com");
        ControleSistema.cadastrarCliente("Hoseok","83360277007", "hoseok@hotmail.com");
        ControleSistema.cadastrarVenda("83360277007", "taehyung@gmail.com", "9356", "macarrao", 5, 2);
        String resultado = ControleSistema.pesquisarComprasVendedor("taehyung@gmail.com");
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveLancarExcecaoParaVendedorNaoEncontrado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ControleSistema.pesquisarComprasVendedor("jimin@hotmail.com"));
    }

    @Test
    public void deveRetornarQueOEmailEInvalido(){
        String resultado = ControleSistema.pesquisarComprasVendedor("jimin.gmail.com");
        Assertions.assertEquals("E-mail inválido!", resultado);
    }

    @Test
    public void deveListarOsClientesCadastrados(){
        ControleSistema.cadastrarCliente("Jungkook", "10552896071", "jungkook@gmail.com");
        String resultado = ControleSistema.listarClientes();
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveListarOsVendedoresCadastrados(){
        ControleSistema.cadastrarVendedor("Jungkook", "10552896071", "jungkook@gmail.com");
        String resultado = ControleSistema.listarVendedores();
        Assertions.assertNull(resultado);
    }

    @Test
    public void deveCadastrarVendaComSucesso(){
        ControleSistema.cadastrarVendedor("Marcos", "12345678910", "marcos@hotmail.com");
        ControleSistema.cadastrarCliente("Mirela", "76246766056", "mirela@gmail.com");
        String resultado = ControleSistema.cadastrarVenda("76246766056", "marcos@hotmail.com", "1234", "barra de chocolate", 5, 10);
        Assertions.assertEquals("Venda cadastrada com sucesso!", resultado);
    }

    @Test
    public void deveLancarExcecaoParaClienteNaoCadastrado(){
        ControleSistema.cadastrarVendedor("Pamela", "30935613021", "pamela@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarVenda("41531153046", "marcos@hotmail.com", "4567", "geleia de morango", 10, 1));
    }

    @Test
    public void deveLancarExcecaoParaVendedorNaoCadastrado(){
        ControleSistema.cadastrarCliente("Jessica", "41614015074", "jessica@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarVenda("41614015074", "mar@hotmail.com", "8919", "bacon", 50, 2));
    }

    @Test
    public void deveLancarExcecaoDeProibicaoDeVendaParaSiMesmo(){
        ControleSistema.cadastrarCliente("Katia", "25148002000", "katia@gmail.com");
        ControleSistema.cadastrarVendedor("Katia", "25148002000", "katia@gmail.com");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ControleSistema.cadastrarVenda("25148002000", "katia@gmail.com", "4466", "doce de leite", 15, 1));
    }

    @Test
    public void deveListarAsVendasCadastradas(){
        ControleSistema.cadastrarVendedor("Namjoon", "91284139034", "namjoon@hotmail.com");
        ControleSistema.cadastrarCliente("Seokjin", "26168578048", "seokjin@gmail.com");
        ControleSistema.cadastrarVenda("26168578048", "namjoon@hotmail.com", "4936", "arroz", 20, 3);
        String resultado = ControleSistema.listarVendas();
        Assertions.assertNull(resultado);
    }

}
