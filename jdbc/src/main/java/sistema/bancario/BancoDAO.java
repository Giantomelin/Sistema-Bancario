package sistema.bancario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            throw new SQLException(e.getMessage());
        } 
    }

    public Clientes consultarCliente(Long id) throws SQLException{
        var sql = "SELECT * FROM Clientes WHERE Id = ?";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)){
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()){
                    if (rs.next()) {
                        Clientes cliente = new Clientes(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("email")
                        );
                        return cliente;
                    } else {
                        System.out.println("Cliente não encontrado. Crie o cliente antes de criar a conta.");
                        return null;
                    }
                }
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
            throw new SQLException(e.getMessage());
        }
    }

    public Contas consultarConta(Long id) throws SQLException{
        var sql = "SELECT * FROM contas WHERE Id = ?";
        try (var connection = Conexao.getConnection();){
            var statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    Contas conta = new Contas(
                        rs.getLong("id"),
                        rs.getString("tipo"),
                        rs.getDouble("saldo"),
                        rs.getLong("Clientes_Id")
                    );
                    return conta;
                } else {
                    System.out.println("Conta não encontrada.");
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void depositar(Long id, double valor) throws SQLException{
        var sql = "UPDATE contas SET saldo = saldo + ? WHERE Id = ?";
        var sqlTransacao = "INSERT INTO transacoes (tipo, valor, id_conta, data) VALUES (?, ?, ?, NOW())";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql);
             var statementTransacao = connection.prepareStatement(sqlTransacao)) {
            statement.setDouble(1, valor);
            statement.setLong(2, id);
            statement.executeUpdate();
            statementTransacao.setString(1, "Depósito");
            statementTransacao.setDouble(2, valor);
            statementTransacao.setLong(3, id);
            statementTransacao.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void sacar(Long id, double valor) throws SQLException{
        var sql = "UPDATE contas SET saldo = saldo - ? WHERE Id = ?";
        var sqlTransacao = "INSERT INTO transacoes (tipo, valor, id_conta, data) VALUES (?, ?, ?, NOW())";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql);
             var statementTransacao = connection.prepareStatement(sqlTransacao)) {
            statement.setDouble(1, valor);
            statement.setLong(2, id);
            statement.executeUpdate();
            statementTransacao.setString(1, "Saque");
            statementTransacao.setDouble(2, valor);
            statementTransacao.setLong(3, id);
            statementTransacao.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void transferir(Long idOrigem, Long idDestino, double valor) throws SQLException{
        var sqlSaque = "UPDATE contas SET saldo = saldo - ? WHERE Id = ?";
        var sqlDeposito = "UPDATE contas SET saldo = saldo + ? WHERE Id = ?";
        var sqlTransferencia = "INSERT INTO transacoes (tipo, valor, id_conta, data) VALUES (?, ?, ?, NOW())";
        try (var connection = Conexao.getConnection()) {
            try (var statementSaque = connection.prepareStatement(sqlSaque);
                 var statementDeposito = connection.prepareStatement(sqlDeposito);
                 var statementTransferencia = connection.prepareStatement(sqlTransferencia)) {
                connection.setAutoCommit(false);
                statementSaque.setDouble(1, valor);
                statementSaque.setLong(2, idOrigem);

                statementSaque.executeUpdate();

                statementDeposito.setDouble(1, valor);
                statementDeposito.setLong(2, idDestino);

                statementDeposito.executeUpdate();

                statementTransferencia.setString(1, "Transferência");
                statementTransferencia.setDouble(2, valor);
                statementTransferencia.setLong(3, idOrigem);

                statementTransferencia.executeUpdate();
                
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e.getMessage());
            }
        }
    }

    public void historicoTransacoes(Long id) throws SQLException{
        var sql = "SELECT * FROM transacoes WHERE id_conta = ?";
        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long idTransacao = rs.getLong("id");
                    String tipo = rs.getString("tipo");
                    double valor = rs.getDouble("valor");
                    String data = rs.getString("data");
                    System.out.println("ID: " + idTransacao + ", Tipo: " + tipo + ", Valor: " + valor + ", Data: " + data);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar histórico de transações: " + e.getMessage());
        }
    }

    public List<Clientes> listarClientes() throws SQLException{
        var sql = "SELECT * FROM clientes";

        List<Clientes> clientes = new ArrayList<>();

        try (var connection = Conexao.getConnection();
             var statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                Clientes cliente = new Clientes(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

}
    

    
        
    

    

