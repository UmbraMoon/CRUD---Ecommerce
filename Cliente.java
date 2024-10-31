import java.util.Date;

//Código para definçõ de atributos dos clientes (Guilherme Soares);
public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private Date dataCadastro;

    public Cliente (String nome, String email, String telefone, Date dataCadastro, int id){
        this.nome = nome;
        this.email = email;
        this.telefone= telefone;
        this.dataCadastro = dataCadastro;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
