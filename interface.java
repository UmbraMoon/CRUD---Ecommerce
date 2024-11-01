import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Interface {
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenu();
    }

    private static void exibirMenu() {
        boolean executar = true;

        while (executar) {
            System.out.println("\n====== Menu Principal ======");
            System.out.println("1. Visualizar todos os clientes");
            System.out.println("2. Inserir um novo cliente");
            System.out.println("3. Atualizar dados de um cliente");
            System.out.println("4. Deletar um cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> visualizarClientes();
                case 2 -> inserirCliente();
                case 3 -> atualizarCliente();
                case 4 -> deletarCliente();
                case 5 -> {
                    System.out.println("Saindo do sistema...");
                    executar = false;
                }
                default -> System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 5.");
            }
        }
        scanner.close();
    }

    private static void visualizarClientes() {
        System.out.println("\n==== Lista de Clientes ====");
        List<Cliente> clientes = clienteDAO.consultarClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.printf("ID: %d | Nome: %s | Email: %s | Telefone: %s | Data de Cadastro: %s%n",
                    cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getDataCadastro());
            }
        }
    }

    private static void inserirCliente() {
        System.out.println("\n==== Inserir Novo Cliente ====");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Data de Cadastro (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date dataCadastro = parseData(dataStr);
        
        if (dataCadastro != null) {
            Cliente novoCliente = new Cliente(nome, email, telefone, dataCadastro, 0);
            clienteDAO.inserirCliente(novoCliente);
            System.out.println("Cliente inserido com sucesso.");
        } else {
            System.out.println("Data inválida. Cliente não inserido.");
        }
    }

    private static void atualizarCliente() {
        System.out.println("\n==== Atualizar Cliente ====");
        
        System.out.print("Digite o ID do cliente que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        Cliente cliente = clienteDAO.consultarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        System.out.printf("Novo Nome (atual: %s): ", cliente.getNome());
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) cliente.setNome(nome);
        
        System.out.printf("Novo Email (atual: %s): ", cliente.getEmail());
        String email = scanner.nextLine();
        if (!email.isEmpty()) cliente.setEmail(email);
        
        System.out.printf("Novo Telefone (atual: %s): ", cliente.getTelefone());
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) cliente.setTelefone(telefone);
        
        System.out.printf("Nova Data de Cadastro (dd/MM/yyyy) (atual: %s): ", cliente.getDataCadastro());
        String dataStr = scanner.nextLine();
        if (!dataStr.isEmpty()) {
            Date dataCadastro = parseData(dataStr);
            if (dataCadastro != null) cliente.setDataCadastro(dataCadastro);
            else System.out.println("Data inválida. Data de cadastro não alterada.");
        }
        
        clienteDAO.atualizarCliente(cliente);
        System.out.println("Cliente atualizado com sucesso.");
    }

    private static void deletarCliente() {
        System.out.println("\n==== Deletar Cliente ====");
        
        System.out.print("Digite o ID do cliente que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        Cliente cliente = clienteDAO.consultarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        clienteDAO.excluirCliente(id);
        System.out.println("Cliente excluído com sucesso.");
    }

    private static Date parseData(String dataStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Erro ao formatar a data.");
            return null;
        }
    }
}
