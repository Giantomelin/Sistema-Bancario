package sistema.bancario;
import java.io.Console;

public class Main {
    static Console console = System.console();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(console.readLine());

            switch (opcao) {
                case 0 -> cadastro();
                case 1 -> criarConta();
                case 2 -> consultarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> transferir();
                case 6 -> consultarSaldo();
                case 7 -> historicoTransacoes();
                case 8 -> listarClientes();
                case 9 -> {
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            } 

        } while (opcao != 9);
    }
    private static void exibirMenu() {
        System.out.println("=== Sistema Bancário ===");
        System.out.println("0 - Cadastro");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Consultar conta");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Consultar saldo");
        System.out.println("7 - Histórico de transações");
        System.out.println("8 - Listar clientes");
        System.out.println("9 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    public static void cadastro() {
        String nome = console.readLine("Digite o nome do cliente: ");
        String cpf = console.readLine("Digite o CPF do cliente: ");
        String email = console.readLine("Digite o email do cliente: ");
        Clientes cliente = new Clientes(nome, cpf, email);
        BancoDAO bancoDAO = new BancoDAO();
        try {
            bancoDAO.cadastrar(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    
    public static void criarConta() {
        // Implementação para criar conta
    }

    public static void consultarConta() {
        // Implementação para consultar conta
    }

    public static void depositar() {
        // Implementação para depositar
    }

    public static void sacar() {
        // Implementação para sacar
    }

    public static void transferir() {
        // Implementação para transferir
    }

    public static void consultarSaldo() {
        // Implementação para consultar saldo
    }

    public static void historicoTransacoes() {
        // Implementação para histórico de transações
    }

    public static void listarClientes() {
        // Implementação para listar clientes
    }
    


}