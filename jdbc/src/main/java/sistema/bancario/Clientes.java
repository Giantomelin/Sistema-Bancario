package sistema.bancario;

public record Clientes(long id, String nome, String cpf, String email) {
    public Clientes(String nome, String cpf, String email) {
        this(0, nome, cpf, email);
    }
}
