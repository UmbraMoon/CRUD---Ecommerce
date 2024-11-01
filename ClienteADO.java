import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Código para CRUD na tabela Cliente (Guilherme Soares)
public class ClienteADO {

    // Método para conectar ao banco de dados
    private Connection conectar() {
        String url = "jdbc:mysql://localhost:3307/ecommerce";
        String usuario = "root";
        String senha = "catolica";
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    // Inserir um novo cliente no banco de dados
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, telefone, dataCadastro) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setDate(4, new java.sql.Date(cliente.getDataCadastro().getTime()));
            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Consultar todos os clientes no banco de dados
    public List<Cliente> consultarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getDate("dataCadastro"),
                        rs.getInt("id")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Atualizar informações de um cliente existente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, email=?, telefone=?, dataCadastro=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setDate(4, new java.sql.Date(cliente.getDataCadastro().getTime()));
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Excluir um cliente do banco de dados
    public void excluirCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente excluído com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    // Consultar cliente específico por ID
    public Cliente consultarClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getDate("dataCadastro"),
                            rs.getInt("id")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cliente por ID: " + e.getMessage());
        }
        return null;
    }
}
