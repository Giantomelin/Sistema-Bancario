package sistema.bancario;

<<<<<<< HEAD
public record Clientes(long id, String nome, String cpf, String email) {
    public Clientes(String nome, String cpf, String email) {
        this(0, nome, cpf, email);
=======
public class Clientes {
    private static int id;
    private static String nome;
    private static String cpf;
    private static String email;

    public Clientes( String nome, String cpf, String email) {
        this.id = null;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
>>>>>>> Clientes
    }
}
