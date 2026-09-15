package sistema.bancario;

public record Contas(long id, String tipo, double saldo, long idCliente) {
    
    public Contas(String tipo, double saldo, long idCliente) {
        this(0, tipo, saldo, idCliente);
    }
}
