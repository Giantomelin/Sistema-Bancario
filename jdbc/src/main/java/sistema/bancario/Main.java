package sistema.bancario;
import java.io.Console;

public class Main {
    private final Console console = System.console();

    public static void main(String[] args) {
        Main app = new Main();
        int opcao;

        do {
            app.exibirMenu();
            String entrada = app.console.readLine();
            opcao = Integer.parseInt(entrada);

            switch (opcao) {
                case 1 -> app.criarConta();
                case 2 -> app.consultarConta();
                case 3 -> app.depositar();
                case 4 -> app.sacar();
                case 5 -> app.transferir();
                case 6 -> app.consultarSaldo();
                case 7 -> app.historicoTransacoes();
                case 8 -> app.listarClientes();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("=== Sistema Bancário ===");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Consultar conta");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Consultar saldo");
        System.out.println("7 - Histórico de transações");
        System.out.println("8 - Listar clientes");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void criarConta() {
        // Implementação para criar conta
    }

    public void consultarConta() {
        // Implementação para consultar conta
    }

    public void depositar() {
        // Implementação para depositar
    }

    public void sacar() {
        // Implementação para sacar
    }

    public void transferir() {
        // Implementação para transferir
    }

    public void consultarSaldo() {
        // Implementação para consultar saldo
    }

    public void historicoTransacoes() {
        // Implementação para histórico de transações
    }

    public void listarClientes() {
        // Implementação para listar clientes
    }
}