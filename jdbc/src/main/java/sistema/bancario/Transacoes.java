package sistema.bancario;

public record Transacoes(long id, String tipo, double valor, long id_conta, String data) {
    
    public Transacoes(String tipo, double valor, long id_conta, String data) {
        this(0, tipo, valor, id_conta, data);
    }
    
}
