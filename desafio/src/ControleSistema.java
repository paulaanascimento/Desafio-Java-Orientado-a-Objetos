import java.util.*;

public class ControleSistema {

    private static Scanner entrada = new Scanner(System.in);
    private static Map<String, Cliente> clientesCadastrados = new HashMap<>();
    private static Map<String, Vendedor> vendedoresCadastrados = new HashMap<>();
    private static List<Vendas> vendasCadastradas = new ArrayList<>();

    public static void cadastrarVenda() {
        System.out.println("~~~~~~Cadastrando Venda~~~~~~");
        System.out.print("Informe o CPF do cliente: ");
        String cpf = entrada.nextLine();

        if (!clientesCadastrados.containsKey(cpf)) {
            throw new UnsupportedOperationException("Cliente não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        System.out.print("Infome o e-mail do vendedor: ");
        String email = entrada.nextLine();

        if (!vendedoresCadastrados.containsKey(email)) {
            throw new UnsupportedOperationException("Vendedor não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        if (clientesCadastrados.get(cpf).getCpf().equals(vendedoresCadastrados.get(email).getCpf())) {
            throw new UnsupportedOperationException("Não é permitido vender para si mesmo!\n");
        }

        System.out.print("Infome o código da venda: ");
        String codigo = entrada.nextLine();

        List<Produto> listaProdutos = adicionarProdutos();

        Vendas venda = new Vendas(vendedoresCadastrados.get(email), clientesCadastrados.get(cpf), codigo, listaProdutos, calcularTotal(listaProdutos));

        clientesCadastrados.get(cpf).adicionarVenda(venda);
        vendedoresCadastrados.get(email).adicionarVenda(venda);
        vendasCadastradas.add(venda);

        System.out.println("\nVenda cadastrada com sucesso!");
    }

    private static List<Produto> adicionarProdutos(){
        List<Produto> listaProdutos = new ArrayList<>();

        boolean continuar = true;

        do{
            System.out.print("Digite o nome do produto: ");
            String nome = entrada.nextLine();

            System.out.print("Digite o preço por unidade: ");
            double preco = entrada.nextDouble();

            System.out.print("Informe a quantidade do produto: ");
            int quantidade = entrada.nextInt();

            listaProdutos.add(new Produto(nome, preco, quantidade));

            System.out.println("""

                    Deseja adicionar mais produtos à essa venda?\s
                    \t1 - Sim
                    \t2 - Não""");

            switch (entrada.nextInt()){
                case 1:
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    throw new IllegalArgumentException("Opção Inválida!");
            }

            entrada.nextLine();

        }while (continuar);

        return listaProdutos;
    }

    private static double calcularTotal(List<Produto> listaProdutos){
        double soma = 0;

        for (Produto produto : listaProdutos){
            soma = soma + produto.getPreco() * produto.getQuantidade();
        }

        return soma;
    }

    public static void listarVendas(){
        if(vendasCadastradas.isEmpty()){
            System.out.println("Nenhuma venda foi cadastrada!");
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
        }
    }

    private static void listarVendas(List<Vendas> listaVendas, String tipo){
        if(listaVendas.isEmpty()){
            System.out.println("Nenhuma venda foi cadastrada!");
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
                System.out.println("\nQuantidade Total de Vendas: " + listaVendas.size());
                System.out.println("Valor Total das Vendas: " + soma);
            }
        }
    }

    public static void listarVendedores(){
        if(vendedoresCadastrados.isEmpty()){
            System.out.println("Nenhum vendedor foi cadastrado!");
        } else {
            System.out.println("~~~~~~Listando todos os vendedores cadastrados~~~~~~");
            for (Map.Entry<String, Vendedor> vendedor: vendedoresCadastrados.entrySet()){
                System.out.println("Nome: " + vendedor.getValue().getNome() +
                        "\t| CPF: " + vendedor.getValue().getCpf() +
                        "\t| E-mail: " + vendedor.getKey() + "\n");
            }
        }
    }

    public static void listarClientes(){
        if(clientesCadastrados.isEmpty()){
            System.out.println("Nenhum cliente foi cadastrado!");
        } else {
            System.out.println("~~~~~~Listando todos os clientes cadastrados~~~~~~");
            for (Map.Entry<String, Cliente> cliente : clientesCadastrados.entrySet()){
                System.out.println("Nome: " + cliente.getValue().getNome() +
                        "\t| CPF: " + cliente.getKey() +
                        "\t| E-mail: " + cliente.getValue().getEmail());
            }
        }
    }

    public static void cadastrarCliente(){
        System.out.println("~~~~~~Cadastrando Cliente~~~~~~");
        System.out.print("Informe o nome: ");
        String nome = entrada.nextLine();

        System.out.print("Informe o CPF: ");
        String cpf = entrada.nextLine();

        if(clientesCadastrados.containsKey(cpf)){
            throw new UnsupportedOperationException("CPF já cadastrado!\n");
        }

        String email;
        do{
            System.out.print("Informe o e-mail: ");
            email = entrada.nextLine();

            if(!email.contains("@")){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!email.contains("@"));

        for (Map.Entry<String, Cliente> cliente : clientesCadastrados.entrySet()){
            if(Objects.equals(cliente.getValue().getEmail(), email)){
                throw new UnsupportedOperationException("E-mail já cadastrado!\n");
            }
        }

        Cliente cliente = new Cliente(nome,cpf, email);
        clientesCadastrados.put(cpf,cliente);

        System.out.println("\nCliente cadastrado com sucesso!");
    }

    public static void cadastrarVendedor(){
        System.out.println("~~~~~~Cadastrando Vendedor~~~~~~");
        System.out.print("Informe o nome: ");
        String nome = entrada.nextLine();

        System.out.print("Informe o CPF: ");
        String cpf = entrada.nextLine();

        for (Map.Entry<String, Vendedor> vendedor: vendedoresCadastrados.entrySet()){
            if(Objects.equals(vendedor.getValue().getCpf(), cpf)){
                throw new UnsupportedOperationException("CPF já cadastrado!\n");
            }
        }

        String email;
        do{
            System.out.print("Informe o e-mail: ");
            email = entrada.nextLine();

            if(!email.contains("@")){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!email.contains("@"));

        if(vendedoresCadastrados.containsKey(email)){
            throw new UnsupportedOperationException("E-mail já cadastrado!\n");
        }

        Vendedor vendedor = new Vendedor(nome,cpf, email);
        vendedoresCadastrados.put(email,vendedor);

        System.out.println("\nVendedor cadastrado com sucesso!");
    }

    public static void pesquisarComprasCliente(){
        System.out.println("Informe o CPF do cliente: ");
        String cpf = entrada.nextLine();

        if(!clientesCadastrados.containsKey(cpf)){
            throw new IllegalArgumentException("Cliente não encontrado!\n");
        } else {
            listarVendas(clientesCadastrados.get(cpf).getListaVendas(), "Cliente");
        }
    }

    public static void pesquisarComprasVendedor(){
        String email;
        do{
            System.out.print("Informe o e-mail do vendedor: ");
            email = entrada.nextLine();

            if(!email.contains("@")){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!email.contains("@"));

        if(!vendedoresCadastrados.containsKey(email)){
            throw new IllegalArgumentException("Vendedor não encontrado!\n");
        } else {
            listarVendas(vendedoresCadastrados.get(email).getListaVendas(), "Vendedor");
        }
    }
}
