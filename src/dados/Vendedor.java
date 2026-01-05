/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author bruno
 */
public class Vendedor extends Usuario {
    private String sobreVc;

    // Construtor
    public Vendedor(String nome, String email, String senha, String cpf, String sobreVc) {
        super(nome, email, senha, cpf);
        setSobreVc(sobreVc); // Usar o setter para validação
    }

    // Getter e Setter
    public String getSobreVc() {
        return sobreVc;
    }

    public void setSobreVc(String sobreVc) {
        if (sobreVc == null || sobreVc.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição sobre você não pode ser nula ou vazia.");
        }
        this.sobreVc = sobreVc;
    }

    // Método toString
    @Override
    public String toString() {
        return "Vendedor{" +
                "nome='" + getNome() + '\'' + // Supondo que getNome() é um método na classe Usuario
                ", email='" + getEmail() + '\'' + // Supondo que getEmail() é um método na classe Usuario
                ", cpf='" + getCpf() + '\'' + // Supondo que getCpf() é um método na classe Usuario
                ", sobreVc='" + sobreVc + '\'' +
                '}';
    }
}
