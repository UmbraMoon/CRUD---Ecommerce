import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Escolha uma operação: ");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.println();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        String dataCadastro = java.time.LocalDate.now().toString();

                        Cliente cliente = new Cliente(0, nome, email, telefone, dataCadastro);
                        clienteDAO.cadastrarCliente(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                        break;

                    case 2:
                        List<Cliente> clientes = clienteDAO.listarClientes();

                        // Cabeçalho da tabela
                        System.out.println("");
                        System.out.printf("%-10s %-20s %-30s %-15s %n", "ID", "Nome", "Email", "Telefone");
                        System.out.println("----------------------------------------------------------------------------");

                        // Dados de cada cliente
                        for (Cliente c : clientes) {
                            System.out.printf("%-10d %-20s %-30s %-15s %n",
                                    c.getClienteId(), c.getNome(), c.getEmail(), c.getTelefone());
                        }
                        break;

                    case 3:
                        System.out.println();
                        System.out.print("ID do cliente para atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo nome: ");
                        nome = scanner.nextLine();
                        System.out.print("Novo email: ");
                        email = scanner.nextLine();
                        System.out.print("Novo telefone: ");
                        telefone = scanner.nextLine();

                        Cliente clienteAtualizado = new Cliente(idAtualizar, nome, email, telefone, null);
                        clienteDAO.atualizarCliente(idAtualizar, clienteAtualizado);
                        System.out.println("Cliente atualizado com sucesso!");
                        break;

                    case 4:
                        System.out.println();
                        System.out.print("ID do cliente para excluir: ");
                        int idExcluir = scanner.nextInt();
                        clienteDAO.excluirCliente(idExcluir);
                        System.out.println("Cliente excluído com sucesso!");
                        break;

                    case 5:
                        System.out.println();
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println();
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
