/*
 * Classe que representa uma loja associada a um vendedor.
 */
package dados;

import java.util.ArrayList; // Importa a classe ArrayList para uso de listas dinâmicas
import java.util.List; // Importa a interface List

/**
 * Classe que representa uma loja de um vendedor.
 * Herda da classe Vendedor e contém informações específicas da loja.
 * 
 * @author bruno
 */
public class LojaVendedor extends Vendedor {
    // Atributos da classe LojaVendedor
    private String nomeDaLoja; // Nome da loja
    private String miniDescricaoLoja; // Mini descrição da loja
    private String categoria; // Categoria da loja
    private String localRetirada; // Local de retirada dos produtos
    private String horarioVenda; // Horário de funcionamento da loja
    private List<Item> itens; // Lista de itens disponíveis na loja (composição)

    // Construtor da classe LojaVendedor
    public LojaVendedor(String nomeDaLoja, String miniDescricaoLoja, String categoria, String localRetirada, String horarioVenda, Vendedor v) {
        // Chama o construtor da classe pai (Vendedor) para inicializar os atributos do vendedor
        super(v.getNome(), v.getEmail(), v.getSenha(), v.getCpf(), v.getSobreVc());
        
        // Inicializa os atributos da loja
        this.nomeDaLoja = nomeDaLoja;
        this.miniDescricaoLoja = miniDescricaoLoja;
        this.categoria = categoria;
        this.localRetirada = localRetirada;
        this.horarioVenda = horarioVenda;
        
        // Inicializa a lista de itens como um ArrayList
        this.itens = new ArrayList<>();
    }

    // Getters e Setters para acessar e modificar os atributos da loja

    public String getNomeDaLoja() {
        return nomeDaLoja; // Retorna o nome da loja
    }

    public void setNomeDaLoja(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja; // Define o nome da loja
    }

    public String getMiniDescricaoLoja() {
        return miniDescricaoLoja; // Retorna a mini descrição da loja
    }

    public void setMiniDescricaoLoja(String miniDescricaoLoja) {
        this.miniDescricaoLoja = miniDescricaoLoja; // Define a mini descrição da loja
    }

    public String getCategoria() {
        return categoria; // Retorna a categoria da loja
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria; // Define a categoria da loja
    }

    public String getLocalRetirada() {
        return localRetirada; // Retorna o local de retirada
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada; // Define o local de retirada
    }

    public String getHorarioVenda() {
        return horarioVenda; // Retorna o horário de funcionamento da loja
    }

    public void setHorarioVenda(String horarioVenda) {
        this.horarioVenda = horarioVenda; // Define o horário de funcionamento da loja
    }

    // Método para adicionar um item à lista de itens da loja
    public void adicionarItem(Item item) {
        this.itens.add(item); // Adiciona o item à lista de itens
    }

    // Método para obter a lista de itens da loja
    public List<Item> getItens() {
        return itens; // Retorna a lista de itens
    }

    // Método toString para representar o objeto como uma String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Cria um StringBuilder para construir a representação textual
        sb.append("LojaVendedor{");
        sb.append("nomeDaLoja=").append(nomeDaLoja); // Adiciona o nome da loja
        sb.append(", miniDescricaoLoja=").append(miniDescricaoLoja); // Adiciona a mini descrição da loja
        sb.append(", categoria=").append(categoria); // Adiciona a categoria da loja
        sb.append(", localRetirada=").append(localRetirada); // Adiciona o local de retirada
        sb.append(", horarioVenda=").append(horarioVenda); // Adiciona o horário de funcionamento
        sb.append(", itens=").append(itens); // Adiciona a lista de itens
        sb.append('}');
        return sb.toString(); // Retorna a representação textual do objeto LojaVendedor
    }
}