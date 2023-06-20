package usercase;

import model.Cliente;
import model.Produto;
import model.Vendas;
import model.Vendedor;

import java.util.*;

public class ControleSistema {

//    private static Scanner entrada = new Scanner(System.in);
    private static Map<String, Cliente> clientesCadastrados = new HashMap<>();
    private static Map<String, Vendedor> vendedoresCadastrados = new HashMap<>();
    private static List<Vendas> vendasCadastradas = new ArrayList<>();

    public static String cadastrarVenda(String cpfCliente, String emailVendedor, String codigoVenda, String nomeProduto, double precoProduto, int quantidadeProduto) {

        if (!clientesCadastrados.containsKey(cpfCliente)) {
            throw new UnsupportedOperationException("Cliente não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        if (!vendedoresCadastrados.containsKey(emailVendedor)) {
            throw new UnsupportedOperationException("Vendedor não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        if (clientesCadastrados.get(cpfCliente).getCpf().equals(vendedoresCadastrados.get(emailVendedor).getCpf())) {
            throw new UnsupportedOperationException("Não é permitido vender para si mesmo!\n");
        }

        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto(nomeProduto, precoProduto, quantidadeProduto));

        Vendas venda = new Vendas(vendedoresCadastrados.get(emailVendedor), clientesCadastrados.get(cpfCliente), codigoVenda, listaProdutos, calcularTotal(listaProdutos));

        clientesCadastrados.get(cpfCliente).adicionarVenda(venda);

        vendedoresCadastrados.get(emailVendedor).adicionarVenda(venda);

        vendasCadastradas.add(venda);

        return "Venda cadastrada com sucesso!";
    }

//    private static List<Produto> adicionarProdutos(){
//        List<Produto> listaProdutos = new ArrayList<>();
//
//        boolean continuar = true;
//
//        do{
//            System.out.print("Digite o nome do produto: ");
//            String nome = entrada.nextLine();
//
//            System.out.print("Digite o preço por unidade: ");
//            double preco = entrada.nextDouble();
//
//            System.out.print("Informe a quantidade do produto: ");
//            int quantidade = entrada.nextInt();
//
//            listaProdutos.add(new Produto(nome, preco, quantidade));
//
//            System.out.println("""
//
//                    Deseja adicionar mais produtos à essa venda?\s
//                    \t1 - Sim
//                    \t2 - Não""");
//
//            switch (entrada.nextInt()){
//                case 1:
//                    break;
//                case 2:
//                    continuar = false;
//                    break;
//                default:
//                    throw new IllegalArgumentException("Opção Inválida!");
//            }
//
//            entrada.nextLine();
//
//        }while (continuar);
//
//        return listaProdutos;
//    }

    private static double calcularTotal(List<Produto> listaProdutos){
        double soma = 0;

        for (Produto produto : listaProdutos){
            soma = soma + produto.getPreco() * produto.getQuantidade();
        }

        return soma;
    }

    public static String listarVendas(){
        if(vendasCadastradas.isEmpty()){
            return "Nenhuma venda foi cadastrada!";
        } else {
            System.out.println("~~~~~~Listando todas as vendas cadastradas~~~~~~");
            for (Vendas vendas: vendasCadastradas) {
                System.out.print("Vendedor: " + vendas.getVendedor().getNome() +
                                 "\t| Cliente: " + vendas.getCliente().getNome() +
                                 "\t| Codigo: " + vendas.getCodigo());
                System.out.print("\t| Produtos: ");
                for(int i = 0; i < vendas.getListaProdutos().size(); i++){
                    System.out.print(vendas.getListaProdutos().get(i));{
                        if(i < vendas.getListaProdutos().size() - 1){
                            System.out.print(", ");
                        }
                    }
                }
                System.out.println("\t| Preço Total: " + vendas.getTotalCompra() +
                                   "\t| Data de Registro: " + vendas.getDataRegistro());

            }
            return null;
        }
    }

    private static String listarVendas(List<Vendas> listaVendas, String tipo){
        if(listaVendas.isEmpty()){
            return "Nenhuma venda foi cadastrada!";
        } else {
            System.out.println("~~~~~~Listando todas as vendas cadastradas~~~~~~");
            for (Vendas vendas: listaVendas) {
                System.out.print("Vendedor: " + vendas.getVendedor().getNome() +
                                 "\t| Cliente: " + vendas.getCliente().getNome() +
                                 "\t| Codigo: " + vendas.getCodigo());
                System.out.print("\t| Produtos: ");
                for(int i = 0; i < vendas.getListaProdutos().size(); i++){
                    System.out.print(vendas.getListaProdutos().get(i));{
                        if(i < vendas.getListaProdutos().size() - 1){
                            System.out.print(", ");
                        }
                    }
                }
                System.out.println("\t| Preço Total: " + vendas.getTotalCompra() +
                                   "\t| Data de Registro: " + vendas.getDataRegistro());
            }

            double soma = 0;

            for(Vendas venda : listaVendas){
                soma = soma + venda.getTotalCompra();
            }

            if(tipo.equals("Cliente")){
                System.out.println("\nQuantidade Total de Compras: " + listaVendas.size());
                System.out.println("Valor Total das Compras: " + soma);
            } else if(tipo.equals("Vendedor")) {
                System.out.println("\nQuantidade Total de model.Vendas: " + listaVendas.size());
                System.out.println("Valor Total das Vendas: " + soma);
            }

            return null;
        }
    }

    public static String listarVendedores(){
        if(vendedoresCadastrados.isEmpty()){
            return "Nenhum vendedor foi cadastrado!";
        } else {
            System.out.println("~~~~~~Listando todos os vendedores cadastrados~~~~~~");
            for (Map.Entry<String, Vendedor> vendedor: vendedoresCadastrados.entrySet()){
                System.out.println("Nome: " + vendedor.getValue().getNome() +
                        "\t| CPF: " + vendedor.getValue().getCpf() +
                        "\t| E-mail: " + vendedor.getKey() + "\n");
            }
            return null;
        }
    }

    public static String listarClientes(){
        if(clientesCadastrados.isEmpty()){
            return "Nenhum cliente foi cadastrado!";
        } else {
            System.out.println("~~~~~~Listando todos os clientes cadastrados~~~~~~");
            for (Map.Entry<String, Cliente> cliente : clientesCadastrados.entrySet()){
                System.out.println("Nome: " + cliente.getValue().getNome() +
                        "\t| CPF: " + cliente.getKey() +
                        "\t| E-mail: " + cliente.getValue().getEmail());
            }
            return null;
        }
    }

    public static String cadastrarCliente(String nome, String cpf, String email){

        if(clientesCadastrados.containsKey(cpf)){
            throw new UnsupportedOperationException("CPF já cadastrado!\n");
        }

        if(!email.contains("@")){
            return "E-mail inválido!";
        }

        for (Map.Entry<String, Cliente> cliente : clientesCadastrados.entrySet()){
            if(Objects.equals(cliente.getValue().getEmail(), email)){
                throw new UnsupportedOperationException("E-mail já cadastrado!\n");
            }
        }

        Cliente cliente = new Cliente(nome,cpf, email);
        clientesCadastrados.put(cpf,cliente);

        return "Cliente cadastrado com sucesso!";
    }

    public static String cadastrarVendedor(String nome, String cpf, String email){

        for (Map.Entry<String, Vendedor> vendedor: vendedoresCadastrados.entrySet()){
            if(Objects.equals(vendedor.getValue().getCpf(), cpf)){
                throw new UnsupportedOperationException("CPF já cadastrado!\n");
            }
        }

        if(!email.contains("@")){
            return "E-mail inválido!";
        }

        if(vendedoresCadastrados.containsKey(email)){
            throw new UnsupportedOperationException("E-mail já cadastrado!\n");
        }

        Vendedor vendedor = new Vendedor(nome,cpf, email);
        vendedoresCadastrados.put(email,vendedor);

        return "Vendedor cadastrado com sucesso!";
    }

    public static String pesquisarComprasCliente(String cpf){
        if(!clientesCadastrados.containsKey(cpf)){
            throw new IllegalArgumentException("Cliente não encontrado!\n");
        } else {
            listarVendas(clientesCadastrados.get(cpf).getListaVendas(), "Cliente");
            return null;
        }
    }

    public static String pesquisarComprasVendedor(String email){

        if(!email.contains("@")){
                return "E-mail inválido!";
        }

        if(!vendedoresCadastrados.containsKey(email)){
            throw new IllegalArgumentException("Vendedor não encontrado!\n");
        } else {
            listarVendas(vendedoresCadastrados.get(email).getListaVendas(), "Vendedor");
            return null;
        }
    }
}
