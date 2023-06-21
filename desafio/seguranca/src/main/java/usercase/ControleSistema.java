package usercase;

import br.com.caelum.stella.validation.CPFValidator;
import model.Cliente;
import model.Produto;
import model.Vendas;
import model.Vendedor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.*;

public class ControleSistema {

    private static Scanner entrada = new Scanner(System.in);
    private static Map<String, Cliente> clientesCadastrados = new HashMap<>();
    private static Map<String, Vendedor> vendedoresCadastrados = new HashMap<>();
    private static List<Vendas> vendasCadastradas = new ArrayList<>();
    private static final String senhaValida = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public static void cadastrarVenda() {
        System.out.println("~~~~~~Cadastrando Venda~~~~~~");

        String cpf;
        do{
            System.out.print("Informe o CPF do cliente: ");
            cpf = entrada.nextLine();

            if(!verificarCpf(cpf)){
                System.out.println("\nInforme um CPF válido!\n");
            }

        }while (!verificarCpf(cpf));


        if (!clientesCadastrados.containsKey(cpf)) {
            throw new UnsupportedOperationException("Cliente não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        String email;
        do{
            System.out.print("Informe o e-mail do vendedor: ");
            email = entrada.nextLine();

            if(!verificarEmail(email)){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!verificarEmail(email));

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
                System.out.println("\nQuantidade Total de model.Vendas: " + listaVendas.size());
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
                        "\t| E-mail: " + vendedor.getKey());
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

        String cpf;
        do{
            System.out.print("Informe o CPF: ");
            cpf = entrada.nextLine();

            if(!verificarCpf(cpf)){
                System.out.println("\nInforme um CPF válido!\n");
            }

        }while (!verificarCpf(cpf));

        if(clientesCadastrados.containsKey(cpf)){
            throw new UnsupportedOperationException("CPF já cadastrado!\n");
        }

        String email;
        do{
            System.out.print("Informe o e-mail: ");
            email = entrada.nextLine();

            if(!verificarEmail(email)){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!verificarEmail(email));

        for (Map.Entry<String, Cliente> cliente : clientesCadastrados.entrySet()){
            if(Objects.equals(cliente.getValue().getEmail(), email)){
                throw new UnsupportedOperationException("E-mail já cadastrado!\n");
            }
        }

        String senha;
        do{
            System.out.print("Crie uma senha: ");
            senha = entrada.nextLine();

            if(!senha.matches(senhaValida)){
                System.out.println("\nA senha deve conter de 8 a 20 caracteres, com numeros, caracteres especiais, letras maiúsculas e minúsculas\n");
            }

        }while (!senha.matches(senhaValida));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(senha);

        Cliente cliente = new Cliente(nome,cpf, email, senhaCriptografada);
        clientesCadastrados.put(cpf,cliente);

        System.out.println("\nCliente cadastrado com sucesso!");
    }

    public static void cadastrarVendedor(){
        System.out.println("~~~~~~Cadastrando Vendedor~~~~~~");
        System.out.print("Informe o nome: ");
        String nome = entrada.nextLine();

        String cpf;
        do{
            System.out.print("Informe o CPF: ");
            cpf = entrada.nextLine();

            if(!verificarCpf(cpf)){
                System.out.println("\nInforme um CPF válido!\n");
            }

        }while (!verificarCpf(cpf));

        for (Map.Entry<String, Vendedor> vendedor: vendedoresCadastrados.entrySet()){
            if(Objects.equals(vendedor.getValue().getCpf(), cpf)){
                throw new UnsupportedOperationException("CPF já cadastrado!\n");
            }
        }

        String email;
        do{
            System.out.print("Informe o e-mail: ");
            email = entrada.nextLine();

            if(!verificarEmail(email)){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!verificarEmail(email));

        if(vendedoresCadastrados.containsKey(email)){
            throw new UnsupportedOperationException("E-mail já cadastrado!\n");
        }

        String senha;
        do{
            System.out.print("Crie uma senha: ");
            senha = entrada.nextLine();

            if(!senha.matches(senhaValida)){
                System.out.println("\nA senha deve conter de 8 a 20 caracteres, com numeros, caracteres especial, letras maiúsculas e minúsculas\n");
            }

        }while (!senha.matches(senhaValida));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(senha);

        Vendedor vendedor = new Vendedor(nome,cpf, email, senhaCriptografada);
        vendedoresCadastrados.put(email,vendedor);

        System.out.println("\nVendedor cadastrado com sucesso!");
    }

    public static void pesquisarComprasCliente(){
        String cpf;
        do{
            System.out.print("Informe o CPF do cliente: ");
            cpf = entrada.nextLine();

            if(!verificarCpf(cpf)){
                System.out.println("\nInforme um CPF válido!\n");
            }

        }while (!verificarCpf(cpf));

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

            if(!verificarEmail(email)){
                System.out.println("\nInforme um e-mail válido!\n");
            }

        }while (!verificarEmail(email));

        if(!vendedoresCadastrados.containsKey(email)){
            throw new IllegalArgumentException("Vendedor não encontrado!\n");
        } else {
            listarVendas(vendedoresCadastrados.get(email).getListaVendas(), "Vendedor");
        }
    }

    public static void loginCliente(){
        if(clientesCadastrados.isEmpty()){
            System.out.println("Realize o cadastro antes de prosseguir");
        } else {
            String cpf;
            do{
                System.out.print("Informe o CPF: ");
                cpf = entrada.nextLine();

                if(!verificarCpf(cpf)){
                    System.out.println("\nInforme um CPF válido!\n");
                }

            }while (!verificarCpf(cpf));

            if(clientesCadastrados.containsKey(cpf)){
                System.out.print("Digite a senha: ");
                String senha = entrada.nextLine();

                String senhaCriptografada = clientesCadastrados.get(cpf).getSenha();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if(encoder.matches(senha, senhaCriptografada)){
                    usuarioAutenticado("Cliente");
                } else System.out.println("Senha incorreta");
            } else System.out.println("CPF não cadastrado");
        }
    }

    public static void loginVendedor(){
        if(vendedoresCadastrados.isEmpty()){
            System.out.println("Realize o cadastro antes de prosseguir");
        } else {

            String email;
            do{
                System.out.print("Informe o e-mail: ");
                email = entrada.nextLine();

                if(!verificarEmail(email)){
                    System.out.println("\nInforme um e-mail válido!\n");
                }

            }while (!verificarEmail(email));

            if (vendedoresCadastrados.containsKey(email)){
                System.out.print("Digite a senha: ");
                String senha = entrada.nextLine();

                String senhaCriptografada = vendedoresCadastrados.get(email).getSenha();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if(encoder.matches(senha, senhaCriptografada)){
                    usuarioAutenticado("Vendedor");
                } else System.out.println("Senha incorreta");
            } else System.out.println("E-mail não cadastrado");
        }
    }

    public static boolean verificarEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    private static boolean verificarCpf(String cpf) {
        try{
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private static void usuarioAutenticado(String tipo){
        while (true){
            System.out.println("\n----------- SISTEMA DE VENDAS -----------");
            System.out.println("""
                    \t1 - Cadastrar Venda
                    \t2 - Listar Vendas Cadastradas
                    \t3 - Listar Vendedores Cadastrados
                    \t4 - Listar Clientes Cadastrados
                    \t5 - Pesquisar Compras de um Cliente
                    \t6 - Pesquisar Vendas de um Vendedor
                    \t7 - Sair
                    """);

            int opcao = entrada.nextInt();
            entrada.nextLine();
            try {
                switch (opcao){
                    case 1:
                        if(tipo.equals("Vendedor")){
                            cadastrarVenda();
                        } else System.out.println("Você não possui autorização");
                        break;
                    case 2:
                        ControleSistema.listarVendas();
                        break;
                    case 3:
                        ControleSistema.listarVendedores();
                        break;
                    case 4:
                        ControleSistema.listarClientes();
                        break;
                    case 5:
                        ControleSistema.pesquisarComprasCliente();
                        break;
                    case 6:
                        ControleSistema.pesquisarComprasVendedor();
                        break;
                    case 7:
                        return;
                    default:
                        throw new IllegalArgumentException("Opcão inválida!");
                }
            } catch (IllegalArgumentException | UnsupportedOperationException erro){
                System.err.println(erro.getMessage());
            }
        }
    }
}
