/*
 * Classe que representa um usuário em um sistema.
 */
package dados;

/**
 * Classe que representa um usuário. Contém informações como nome, email, senha
 * e CPF do usuário.
 *
 * @author bruno.hgsilva3
 */
public class Usuario {

    // Atributos da classe Usuario
    private String nome; // Nome do usuário
    private String email; // Email do usuário
    private String senha; // Senha do usuário
    private String cpf; // CPF do usuário

    // Construtor da classe Usuario
    public Usuario(String nome, String email, String senha, String cpf) {
        // Inicializa os atributos do usuário com os valores fornecidos
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    // Getters e Setters para acessar e modificar os atributos do usuário
    public String getNome() {
        return nome; // Retorna o nome do usuário
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do usuário
    }

    public String getEmail() {
        return email; // Retorna o email do usuário
    }

    public void setEmail(String email) {
        this.email = email; // Define o email do usuário
    }

    public String getSenha() {
        return senha; // Retorna a senha do usuário
    }

    public void setSenha(String senha) {
        this.senha = senha; // Define a senha do usuário
    }

    public String getCpf() {
        return cpf; // Retorna o CPF do usuário
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; // Define o CPF do usuário
    }

    // Método toString para representar o objeto como uma String
    @Override
    public String toString() {
        // Retorna uma representação textual do objeto Usuario
        return "Usuario{"
                + "nome=" + nome
                + ", email=" + email
                + ", senha=" + senha
                + ", cpf=" + cpf
                + '}';
    }
}
