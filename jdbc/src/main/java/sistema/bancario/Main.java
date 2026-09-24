package sistema.bancario;
import java.io.Console;
import java.util.List;

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
        Long idCliente = Long.parseLong(console.readLine("Digite o ID do cliente: "));
        String tipo = console.readLine("Digite o tipo da conta (corrente/poupança): ");
        Contas conta = new Contas(tipo, 0.0, idCliente);
        BancoDAO bancoDAO = new BancoDAO();
        try {
            bancoDAO.criarConta(conta);
            System.out.println("Conta criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    public static void consultarConta() {
        Long id = Long.parseLong(console.readLine("Digite o ID da conta: "));
        BancoDAO bancoDAO = new BancoDAO();
        try {
            Contas conta = bancoDAO.consultarConta(id);
            if (conta != null) {
                System.out.println("Conta encontrada: ");
                System.out.println("ID: " + conta.id());
                System.out.println("Tipo: " + conta.tipo());
                System.out.println("Saldo: " + conta.saldo());
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar conta: " + e.getMessage());
        }
    }

    public static void depositar() {
        Long id = Long.parseLong(console.readLine("Digite o ID da conta: "));
        double valor = Double.parseDouble(console.readLine("Digite o valor a ser depositado: "));
        BancoDAO bancoDAO = new BancoDAO();
        try {
            bancoDAO.depositar(id, valor);
            System.out.println("Depósito realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    public static void sacar() {
        Long id = Long.parseLong(console.readLine("Digite o ID da conta: "));
        BancoDAO bancoDAO = new BancoDAO();

        try {
            Contas conta = bancoDAO.consultarConta(id);

            if (conta == null) {
                System.out.println("Conta não encontrada.");
                return;
            }
            double valor = Double.parseDouble(console.readLine("Digite o valor que deseja sacar: "));
            if (valor <= 0) {
                System.out.println("O valor do saque deve ser maior que zero.");
                return;
            }
            if (valor > conta.saldo()) {
                System.out.println("Saldo insuficiente.");
                return;
            }
            bancoDAO.sacar(id, valor);
            System.out.println("Saque realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    public static void transferir() {
        Long IdOrigem = Long.parseLong(console.readLine("Digite o ID da conta de origem: "));
        Long IdDestino = Long.parseLong(console.readLine("Digite o ID da conta de destino: "));
        double valor = Double.parseDouble(console.readLine("Digite o valor a ser transferido: "));
        BancoDAO bancoDAO = new BancoDAO();
        try {
            bancoDAO.transferir(IdOrigem, IdDestino, valor);
            System.out.println("Transferência realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar transferência: " + e.getMessage());
        }
    }

    public static void consultarSaldo() {
        Long Id = Long.parseLong(console.readLine("Digite o ID da conta: "));
        BancoDAO bancoDAO = new BancoDAO();
        try {
            Contas conta = bancoDAO.consultarConta(Id);
            if (conta != null) {
                System.out.println("Saldo da conta: " + conta.saldo());
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar saldo: " + e.getMessage());
        }
    }

    public static void historicoTransacoes() {
        Long Id = Long.parseLong(console.readLine("Digite o ID da conta: "));
        BancoDAO bancoDAO = new BancoDAO();
        try {
            Contas conta = bancoDAO.consultarConta(Id);
            if (conta != null) {
                System.out.println("=== Histórico de Transações ===");
                bancoDAO.historicoTransacoes(Id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar histórico de transações: " + e.getMessage());
        }
    }

    public static void listarClientes() {
        BancoDAO bancoDAO = new BancoDAO();
        try {
            List<Clientes> clientes = bancoDAO.listarClientes();
            if (clientes != null) {
                System.out.println("=== Lista de Clientes ===");
                for (Clientes cliente : clientes) {
                    System.out.println("ID: " + cliente.id() + ", Nome: " + cliente.nome() + ", CPF: " + cliente.cpf() + ", Email: " + cliente.email());
                }
            } else {
                System.out.println("Nenhum cliente encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }
    


}