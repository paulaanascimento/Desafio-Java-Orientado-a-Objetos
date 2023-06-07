import usercase.ControleSistema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcao = 0;

        while (opcao!=9){
            System.out.println("\n----------- SISTEMA DE VENDAS -----------");
            System.out.println("""
                    \t1 - Cadastrar Venda
                    \t2 - Cadastrar Vendedor
                    \t3 - Cadastrar Cliente
                    \t4 - Listar Vendas Cadastradas
                    \t5 - Listar Vendedores Cadastrados
                    \t6 - Listar Clientes Cadastrados
                    \t7 - Pesquisar Compras de um Cliente
                    \t8 - Pesquisar Vendas de um Vendedor
                    \t9 - Sair do Sistema
                    """);

            opcao = entrada.nextInt();

            try {
                switch (opcao){
                    case 1:
                        ControleSistema.cadastrarVenda();
                        break;
                    case 2:
                        ControleSistema.cadastrarVendedor();
                        break;
                    case 3:
                        ControleSistema.cadastrarCliente();
                        break;
                    case 4:
                        ControleSistema.listarVendas();
                        break;
                    case 5:
                        ControleSistema.listarVendedores();
                        break;
                    case 6:
                        ControleSistema.listarClientes();
                        break;
                    case 7:
                        ControleSistema.pesquisarComprasCliente();
                        break;
                    case 8:
                        ControleSistema.pesquisarComprasVendedor();
                        break;
                    case 9:
                        System.out.println("...encerrando o sistema...");
                        System.exit(0);
                        break;
                    default:
                        throw new IllegalArgumentException("Opcão inválida!");
                }
            } catch (IllegalArgumentException erro){
                System.err.println(erro.getMessage());
            } catch (UnsupportedOperationException erro2){
                System.err.println(erro2.getMessage());
            }
        }
    }
}
