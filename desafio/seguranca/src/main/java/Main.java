import usercase.ControleSistema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        while (true){
            System.out.println("\n----------- SISTEMA DE VENDAS -----------");
            System.out.println("""
                    \t1 - Entrar como Cliente
                    \t2 - Entrar como Vendedor
                    \t3 - Cadastrar Cliente
                    \t4 - Cadastrar Vendedor
                    \t5 - Fechar Sistema
                    """);

            try {
                switch (entrada.nextInt()){
                    case 1:
                        ControleSistema.loginCliente();
                        break;
                    case 2:
                        ControleSistema.loginVendedor();
                        break;
                    case 3:
                        ControleSistema.cadastrarCliente();
                        break;
                    case 4:
                        ControleSistema.cadastrarVendedor();
                        break;
                    case 5:
                        System.out.println("...encerrando o sistema...");
                        System.exit(0);
                        break;
                    default:
                        throw new IllegalArgumentException("Opcão inválida!");
                }
            } catch (UnsupportedOperationException erro){
                System.err.println(erro.getMessage());
            }
        }
    }
}
