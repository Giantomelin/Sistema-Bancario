package sistema.bancario;

import java.sql.ResultSet;
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

    public void consultarConta(Long id) throws SQLException{
        var sql = "SELECT * FROM contas WHERE Id = ?";
        try (var connection = Conexao.getConnection();){
            var statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    rs.getLong("id");
                    rs.getString("tipo");
                    rs.getDouble("saldo");
                    rs.getLong("idCliente");
                } else {
                    System.out.println("Conta não encontrada.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar conta: " + e.getMessage());
        }
    }

    public void depositar(Long id, double valor) throws SQLException{
        var sql = "UPDATE contas SET saldo = saldo + ? WHERE Id = ?";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, valor);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao depositar: " + e.getMessage());
        }
    }

    public void sacar(Long id, double valor) throws SQLException{
        var sql = "UPDATE contas SET saldo = saldo - ? WHERE Id = ?";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, valor);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao sacar: " + e.getMessage());
        }
    }

}
    

    

