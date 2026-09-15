package sistema.bancario;

import java.sql.SQLException;
public class BancoDAO {

    public void cadastrar(Clientes cliente) throws SQLException {
        var sql = "INSERT INTO clientes (nome, cpf, email) VALUES (?, ?, ?)";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.nome());
            statement.setString(2, cliente.cpf());
            statement.setString(3, cliente.email());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar cliente: " + e.getMessage());
        } 
    }

    public void criarConta(Contas conta) throws SQLException{
        var sql = "INSERT INTO contas (tipo, saldo, idCliente) VALUES (?, ?, ?)";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, conta.tipo());
            statement.setDouble(2, conta.saldo());
            statement.setLong(3, conta.idCliente());
        } catch (SQLException e) {
            throw new SQLException("Erro ao criar conta: " + e.getMessage());
        }
    }


}
    

    

    

