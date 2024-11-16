import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final String url = "jdbc:mysql://localhost:3306/ecommerce";
    private final String user = "root";
    private final String password = "user";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // CREATE
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email, telefone, data_cadastro) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getDataCadastro());
            stmt.executeUpdate();
        }
    }

    // READ
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("data_cadastro")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // UPDATE
    public void atualizarCliente(int clienteId, Cliente clienteAtualizado) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE cliente_id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteAtualizado.getNome());
            stmt.setString(2, clienteAtualizado.getEmail());
            stmt.setString(3, clienteAtualizado.getTelefone());
            stmt.setInt(4, clienteId);
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void excluirCliente(int clienteId) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cliente_id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.executeUpdate();
        }
    }
}
