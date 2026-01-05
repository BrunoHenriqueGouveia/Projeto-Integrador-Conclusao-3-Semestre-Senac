/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author bruno
 */
public class ReservaPedido {

    private int idItem;
    private String nomeProduto;
    private int qtd;
    private double valor;
    private String localDaReserva;
    private String horario;
    private String nomeLoja;
    private String nomeVendedor;
    private String data;
    private int idVendedor;
    private int idComprador;
    private String statusComprador="Pendente";
    private String statusVendedor = "Pendente";

    public ReservaPedido(int idItem, String nomeProduto, int qtd, double valor, String localDaReserva, String horario, String nomeLoja, String nomeVendedor, String data, int idVendedor, int idComprador) {
        this.idItem = idItem;
        this.nomeProduto = nomeProduto;
        this.qtd = qtd;
        this.valor = valor;
        this.localDaReserva = localDaReserva;
        this.horario = horario;
        this.nomeLoja = nomeLoja;
        this.nomeVendedor = nomeVendedor;
        this.data = data;
        this.idVendedor = idVendedor;
        this.idComprador = idComprador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getLocalDaReserva() {
        return localDaReserva;
    }

    public void setLocalDaReserva(String localDaReserva) {
        this.localDaReserva = localDaReserva;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getStatusComprador() {
        return statusComprador;
    }

    public void setStatusComprador(String statusComprador) {
        this.statusComprador = statusComprador;
    }

    public String getStatusVendedor() {
        return statusVendedor;
    }

    public void setStatusVendedor(String statusVendedor) {
        this.statusVendedor = statusVendedor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReservaPedido{\n");
        sb.append("idItem=").append(idItem);
        sb.append(",\n nomeProduto=").append(nomeProduto);
        sb.append(",\n qtd=").append(qtd);
        sb.append(",\n valor=").append(valor);
        sb.append(",\n localDaReserva=").append(localDaReserva);
        sb.append(",\n horario=").append(horario);
        sb.append(",\n nomeLoja=").append(nomeLoja);
        sb.append(",\n nomeVendedor=").append(nomeVendedor);
        sb.append(",\n data=").append(data);
        sb.append(",\n idVendedor=").append(idVendedor);
        sb.append(",\n idComprador=").append(idComprador);
        sb.append('}');
        return sb.toString();
    }

}
