/*
 * Classe que representa um item de produto em um sistema de vendas.
 */
package dados;

/**
 * Classe que representa um item de produto.
 */
public class Item {
    // Atributos da classe Item
    private String nomeProduto; // Nome do produto
    private String miniDescricaoProduto; // Mini descrição do produto
    private String descricaoProduto; // Descrição completa do produto
    private String validade; // Validade do produto (pode ser uma String ou uma data)
    private int qtdProduto; // Quantidade disponível do produto
    private double preco; // Preço do produto

    // Construtor da classe Item
    public Item(String nomeProduto, String miniDescricaoProduto, String descricaoProduto, String validade, int qtdProduto, double preco) {
        this.nomeProduto = nomeProduto; // Inicializa o nome do produto
        this.miniDescricaoProduto = miniDescricaoProduto; // Inicializa a mini descrição do produto
        this.descricaoProduto = descricaoProduto; // Inicializa a descrição do produto
        this.validade = validade; // Inicializa a validade do produto
        setQtdProduto(qtdProduto); // Usar o setter para validação da quantidade
        this.preco = preco; // Inicializa o preço do produto
    }

    // Getters e Setters para acessar e modificar os atributos

    public String getNomeProduto() {
        return nomeProduto; // Retorna o nome do produto
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto; // Define o nome do produto
    }

    public String getMiniDescricaoProduto() {
        return miniDescricaoProduto; // Retorna a mini descrição do produto
    }

    public void setMiniDescricaoProduto(String miniDescricaoProduto) {
        this.miniDescricaoProduto = miniDescricaoProduto; // Define a mini descrição do produto
    }

    public String getDescricaoProduto() {
        return descricaoProduto; // Retorna a descrição do produto
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto; // Define a descrição do produto
    }

    public String getValidade() {
        return validade; // Retorna a validade do produto
    }

    public void setValidade(String validade) {
        this.validade = validade; // Define a validade do produto
    }

    public int getQtdProduto() {
        return this.qtdProduto; // Retorna a quantidade do produto
    }

    public void setQtdProduto(int qtdProduto) {
        // Validação: a quantidade do produto não pode ser negativa
        if (qtdProduto < 0) {
            throw new IllegalArgumentException("A quantidade do produto não pode ser negativa.");
        }
        this.qtdProduto = qtdProduto; // Define a quantidade do produto
    }

    public double getPreco() {
        return preco; // Retorna o preço do produto
    }

    public void setPreco(double preco) {
        // Validação: o preço do produto não pode ser negativo
        if (preco < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
        }
        this.preco = preco; // Define o preço do produto
    }

    // Método toString para representar o objeto como uma String
    @Override
    public String toString() {
        return "Item{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", miniDescricaoProduto='" + miniDescricaoProduto + '\'' +
                ", descricaoProduto='" + descricaoProduto + '\'' +
                ", validade='" + validade + '\'' +
                ", qtdProduto=" + qtdProduto +
                ", preco=" + preco +
                '}'; // Retorna uma representação textual do objeto Item
    }
}