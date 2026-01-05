/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import dados.AppDao;
import dados.ReservaPedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import telas.Menu;

/**
 *
 * @author bruno
 */
public class Loja extends javax.swing.JFrame {

    /**
     * Creates new form Loja
     */
    //comprador
    private int idComprador=0;
    //vendedor
    private String nomeLoja;
    private String localDaReserva;
    private String horario;
    private String nomeVendedor;
    private int idVendedor=0;
    private String data = null;

    // item
    private int idItem;
    private String nomeProduto;
    private int qtd;
    private double valor;

    public Loja() {
        initComponents();
    }

    public Loja(String nomeLoja, int idComprador) {
        initComponents();
        this.nomeLoja = nomeLoja;
        this.idComprador = idComprador;
        MostrarLoja();
    }

    public void MostrarLoja() {
        try {

            int qtdItem = TotalItens();
            ResultSet loja = new AppDao().buscarLoja(this.nomeLoja);
            // Itera sobre as lojas no ResultSet
            int i = 1; // Contador para o número de lojas
            while (loja.next() && i <= qtdItem) {

                // Informações gerais da loja
                NomeLoja.setText(this.nomeLoja);
                categoria.setText(loja.getString("categoria"));
                jLHorario.setText(loja.getString("horarioVenda"));
                NomeVendedor.setText(loja.getString("nome"));
                DescricaoDoVendedor.setText(loja.getString("sobreVC"));

                this.localDaReserva = loja.getString("localRetirada");
                this.horario = loja.getString("horarioVenda");
                this.nomeVendedor = loja.getString("nome");
                this.idVendedor = loja.getInt("fk_idVendedor");

                if (i == 1) {
                    jLbTitulo1.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao1.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade1.setText(loja.getString("validade"));
                    jlbValor1.setText(loja.getString("valor"));
                    jlItem1.setText(loja.getString("idItem"));
                    produto1.setVisible(true);
                } else if (i == 2) {
                    jLbTitulo2.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao2.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade2.setText(loja.getString("validade"));
                    jlbValor2.setText(loja.getString("valor"));
                    jlItem2.setText(loja.getString("idItem"));
                    produto2.setVisible(true);
                } else if (i == 3) {
                    jLbTitulo3.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao3.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade3.setText(loja.getString("validade"));
                    jlbValor3.setText(loja.getString("valor"));
                    jlItem3.setText(loja.getString("idItem"));
                    produto3.setVisible(true);
                } else if (i == 4) {
                    jLbTitulo4.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao4.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade4.setText(loja.getString("validade"));
                    jlbValor4.setText(loja.getString("valor"));
                    jlItem4.setText(loja.getString("idItem"));
                    produto4.setVisible(true);
                } else if (i == 5) {
                    jLbTitulo5.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao5.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade5.setText(loja.getString("validade"));
                    jlbValor5.setText(loja.getString("valor"));
                    jlItem5.setText(loja.getString("idItem"));
                    produto5.setVisible(true);
                } else if (i == 6) {
                    jLbTitulo6.setText(loja.getString("nomeProduto"));
                    jLbMiniDescricao6.setText(loja.getString("miniDescricaoProduto"));
                    jlbValidade6.setText(loja.getString("validade"));
                    jlbValor6.setText(loja.getString("valor"));
                    jlItem6.setText(loja.getString("idItem"));
                    produto6.setVisible(true);
                }
                i++;
            }

            // Se nenhuma loja foi encontrada
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Loja não encontradas");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }

    public int TotalItens() {
        int totalLojas = 0;
        try {
            ResultSet rs = new AppDao().qtdItens(this.nomeLoja);
            if (rs.next()) {
                totalLojas = rs.getInt("qtd_itens"); // total_lojas é do tipo INT
                System.out.println("Total de itens: " + totalLojas);
            } else {// se nao encontrou
                JOptionPane.showMessageDialog(null, "Loja não encontrado");
            }
            return totalLojas;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
        }

        return totalLojas;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imagemDestaqueDaLoja = new javax.swing.JLabel();
        panelVendedor = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        NomeVendedor = new javax.swing.JLabel();
        DescricaoDoVendedor = new javax.swing.JLabel();
        NomeLoja = new javax.swing.JLabel();
        Avaliacao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        categoria = new javax.swing.JLabel();
        horarioDaVenda = new javax.swing.JLabel();
        jLHorario = new javax.swing.JLabel();
        btnVolta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelProdutos = new javax.swing.JPanel();
        produto1 = new javax.swing.JPanel();
        jlbImagem1 = new javax.swing.JLabel();
        jLbMiniDescricao1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jlbValor1 = new javax.swing.JLabel();
        jLbTitulo1 = new javax.swing.JLabel();
        jlbValidade1 = new javax.swing.JLabel();
        jlItem1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        produto2 = new javax.swing.JPanel();
        jlbImagem2 = new javax.swing.JLabel();
        jLbMiniDescricao2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jlbValor2 = new javax.swing.JLabel();
        jLbTitulo2 = new javax.swing.JLabel();
        jlbValidade2 = new javax.swing.JLabel();
        jlItem2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        produto3 = new javax.swing.JPanel();
        jlbImagem3 = new javax.swing.JLabel();
        jLbMiniDescricao3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jlbValor3 = new javax.swing.JLabel();
        jLbTitulo3 = new javax.swing.JLabel();
        jlbValidade3 = new javax.swing.JLabel();
        jlItem3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        produto4 = new javax.swing.JPanel();
        jlbImagem4 = new javax.swing.JLabel();
        jLbMiniDescricao4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jlbValor4 = new javax.swing.JLabel();
        jLbTitulo4 = new javax.swing.JLabel();
        jlbValidade4 = new javax.swing.JLabel();
        jlItem4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        produto5 = new javax.swing.JPanel();
        jlbImagem5 = new javax.swing.JLabel();
        jLbMiniDescricao5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jlbValor5 = new javax.swing.JLabel();
        jLbTitulo5 = new javax.swing.JLabel();
        jlbValidade5 = new javax.swing.JLabel();
        jlItem5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        produto6 = new javax.swing.JPanel();
        jlbImagem6 = new javax.swing.JLabel();
        jLbMiniDescricao6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jlbValor6 = new javax.swing.JLabel();
        jLbTitulo6 = new javax.swing.JLabel();
        jlbValidade6 = new javax.swing.JLabel();
        jlItem6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Produtos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        imagemDestaqueDaLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/LOGO.png"))); // NOI18N

        panelVendedor.setBackground(new java.awt.Color(255, 153, 51));
        panelVendedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/pessoas (1)_1.png"))); // NOI18N

        NomeVendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        NomeVendedor.setText("Nome Vendedor");

        DescricaoDoVendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DescricaoDoVendedor.setText("Fale um pouco sobre Voçe");

        javax.swing.GroupLayout panelVendedorLayout = new javax.swing.GroupLayout(panelVendedor);
        panelVendedor.setLayout(panelVendedorLayout);
        panelVendedorLayout.setHorizontalGroup(
            panelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescricaoDoVendedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NomeVendedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        panelVendedorLayout.setVerticalGroup(
            panelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVendedorLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(NomeVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DescricaoDoVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(panelVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        NomeLoja.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        NomeLoja.setText("nome da loja");

        Avaliacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/avaliacao (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setText("Categoria Loja:");

        categoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        categoria.setText("Categoria");

        horarioDaVenda.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        horarioDaVenda.setText("Horario De Venda:");

        jLHorario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLHorario.setText("horario");

        btnVolta.setBackground(new java.awt.Color(255, 153, 0));
        btnVolta.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVolta.setForeground(new java.awt.Color(255, 255, 255));
        btnVolta.setText("Volta");
        btnVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltaActionPerformed(evt);
            }
        });

        panelProdutos.setBackground(new java.awt.Color(255, 255, 255));
        panelProdutos.setMinimumSize(new java.awt.Dimension(800, 400));

        produto1.setVisible(false);
        produto1.setBackground(new java.awt.Color(255, 255, 255));
        produto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao1.setText("Mini Descricao do produto");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setText("Reserva");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlbValor1.setText("Valor");

        jLbTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo1.setText("Titulo");

        jlbValidade1.setText("Validade");

        jlItem1.setVisible(false);

        jLabel3.setText("R$");

        javax.swing.GroupLayout produto1Layout = new javax.swing.GroupLayout(produto1);
        produto1.setLayout(produto1Layout);
        produto1Layout.setHorizontalGroup(
            produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlbImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto1Layout.createSequentialGroup()
                        .addComponent(jlbValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton1)
                        .addGap(21, 21, 21))
                    .addGroup(produto1Layout.createSequentialGroup()
                        .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbValidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbMiniDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto1Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto1Layout.setVerticalGroup(
            produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(produto1Layout.createSequentialGroup()
                        .addComponent(jLbTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbMiniDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jlbValidade1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jlbValor1)
                            .addComponent(jLabel3)))
                    .addGroup(produto1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlbImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(produto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto1Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jlItem1)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        produto2.setVisible(false);
        produto2.setBackground(new java.awt.Color(255, 255, 255));
        produto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao2.setText("Mini Descricao do produto");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setText("Reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jlbValor2.setText("Valor");

        jLbTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo2.setText("Titulo");

        jlbValidade2.setText("Validade");

        jlItem2.setVisible(false);

        jLabel5.setText("R$");

        javax.swing.GroupLayout produto2Layout = new javax.swing.GroupLayout(produto2);
        produto2.setLayout(produto2Layout);
        produto2Layout.setHorizontalGroup(
            produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlbImagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto2Layout.createSequentialGroup()
                        .addComponent(jlbValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton2)
                        .addGap(21, 21, 21))
                    .addGroup(produto2Layout.createSequentialGroup()
                        .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbMiniDescricao2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbValidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto2Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto2Layout.setVerticalGroup(
            produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(produto2Layout.createSequentialGroup()
                        .addComponent(jLbTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbMiniDescricao2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbValidade2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jlbValor2)
                            .addComponent(jLabel5)))
                    .addGroup(produto2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlbImagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(produto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto2Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jlItem2)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        produto3.setVisible(false);
        produto3.setBackground(new java.awt.Color(255, 255, 255));
        produto3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao3.setText("Mini Descricao do produto");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 102));
        jButton3.setText("Reserva");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jlbValor3.setText("Valor");

        jLbTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo3.setText("Titulo");

        jlbValidade3.setText("Validade");

        jlItem3.setVisible(false);

        jLabel6.setText("R$");

        javax.swing.GroupLayout produto3Layout = new javax.swing.GroupLayout(produto3);
        produto3.setLayout(produto3Layout);
        produto3Layout.setHorizontalGroup(
            produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlbImagem3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto3Layout.createSequentialGroup()
                        .addComponent(jlbValor3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton3)
                        .addGap(21, 21, 21))
                    .addGroup(produto3Layout.createSequentialGroup()
                        .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbMiniDescricao3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbValidade3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto3Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto3Layout.setVerticalGroup(
            produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(produto3Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addComponent(jlbImagem3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(produto3Layout.createSequentialGroup()
                        .addComponent(jLbTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbMiniDescricao3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbValidade3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jlbValor3)
                            .addComponent(jLabel6))))
                .addContainerGap())
            .addGroup(produto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto3Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jlItem3)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        produto4.setVisible(false);
        produto4.setBackground(new java.awt.Color(255, 255, 255));
        produto4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao4.setText("Mini Descricao do produto");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 102));
        jButton4.setText("Reserva");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jlbValor4.setText("Valor");

        jLbTitulo4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo4.setText("Titulo");

        jlbValidade4.setText("Validade");

        jlItem4.setVisible(false);

        jLabel7.setText("R$");

        javax.swing.GroupLayout produto4Layout = new javax.swing.GroupLayout(produto4);
        produto4.setLayout(produto4Layout);
        produto4Layout.setHorizontalGroup(
            produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlbImagem4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto4Layout.createSequentialGroup()
                        .addComponent(jlbValor4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton4)
                        .addGap(21, 21, 21))
                    .addGroup(produto4Layout.createSequentialGroup()
                        .addGroup(produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTitulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbMiniDescricao4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbValidade4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto4Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto4Layout.setVerticalGroup(
            produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLbTitulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbMiniDescricao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbValidade4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jlbValor4)
                    .addComponent(jLabel7))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jlbImagem4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(produto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto4Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jlItem4)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        produto5.setVisible(false);
        produto5.setBackground(new java.awt.Color(255, 255, 255));
        produto5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao5.setText("Mini Descricao do produto");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 102));
        jButton5.setText("Reserva");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jlbValor5.setText("Valor");

        jLbTitulo5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo5.setText("Titulo");

        jlbValidade5.setText("Validade");

        jlItem5.setVisible(false);

        jLabel8.setText("R$");

        javax.swing.GroupLayout produto5Layout = new javax.swing.GroupLayout(produto5);
        produto5.setLayout(produto5Layout);
        produto5Layout.setHorizontalGroup(
            produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jlbImagem5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produto5Layout.createSequentialGroup()
                        .addComponent(jlbValor5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton5)
                        .addGap(21, 21, 21))
                    .addGroup(produto5Layout.createSequentialGroup()
                        .addGroup(produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTitulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbMiniDescricao5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbValidade5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto5Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto5Layout.setVerticalGroup(
            produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLbTitulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbMiniDescricao5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbValidade5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jlbValor5)
                    .addComponent(jLabel8))
                .addContainerGap())
            .addGroup(produto5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlbImagem5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(produto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto5Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jlItem5)
                    .addContainerGap(60, Short.MAX_VALUE)))
        );

        produto6.setVisible(false);
        produto6.setBackground(new java.awt.Color(255, 255, 255));
        produto6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jlbImagem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/adicionar-imagem.png"))); // NOI18N

        jLbMiniDescricao6.setText("Mini Descricao do produto");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 51, 102));
        jButton6.setText("Reserva");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jlbValor6.setText("Valor");

        jLbTitulo6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTitulo6.setText("Titulo");

        jlbValidade6.setText("Validade");

        jlItem6.setVisible(false);

        jLabel9.setText("R$");

        javax.swing.GroupLayout produto6Layout = new javax.swing.GroupLayout(produto6);
        produto6.setLayout(produto6Layout);
        produto6Layout.setHorizontalGroup(
            produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jlbImagem6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(produto6Layout.createSequentialGroup()
                        .addComponent(jlbValor6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton6))
                    .addComponent(jLbTitulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbMiniDescricao6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbValidade6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto6Layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jlItem6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        produto6Layout.setVerticalGroup(
            produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produto6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(produto6Layout.createSequentialGroup()
                        .addComponent(jLbTitulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbMiniDescricao6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbValidade6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jlbValor6)
                            .addComponent(jLabel9)))
                    .addGroup(produto6Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jlbImagem6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(produto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produto6Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jlItem6)
                    .addContainerGap(57, Short.MAX_VALUE)))
        );

        Produtos.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        Produtos.setText("Produtos");

        javax.swing.GroupLayout panelProdutosLayout = new javax.swing.GroupLayout(panelProdutos);
        panelProdutos.setLayout(panelProdutosLayout);
        panelProdutosLayout.setHorizontalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(produto6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(produto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(produto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(produto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(produto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProdutosLayout.setVerticalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Produtos)
                .addGap(36, 36, 36)
                .addComponent(produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(produto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(produto4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(produto5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(produto6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(927, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelProdutos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVolta)
                                .addGap(105, 105, 105)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NomeLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Avaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(imagemDestaqueDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(horarioDaVenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagemDestaqueDaLoja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomeLoja))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(horarioDaVenda)
                                .addComponent(jLHorario))
                            .addComponent(Avaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnVolta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(912, 507));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (idComprador != idVendedor) {
            this.qtd = 1;
            this.idItem = Integer.parseInt(jlItem1.getText());
            this.nomeProduto = jLbTitulo1.getText();
            this.valor = Double.parseDouble(jlbValor1.getText());
            ReservaPedido reserva = new ReservaPedido(idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador);
            dispose();
            new FinalizarCompra(reserva).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não pode reserva seu Produto!!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (idComprador != idVendedor) {
            this.qtd = 1;
            this.idItem = Integer.parseInt(jlItem3.getText());
            this.nomeProduto = jLbTitulo3.getText();
            this.valor = Double.parseDouble(jlbValor3.getText());
            ReservaPedido reserva = new ReservaPedido(idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador);
            dispose();
            new FinalizarCompra(reserva).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não pode reserva seu Produto!!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (idComprador != idVendedor) {
            this.qtd = 1;
            this.idItem = Integer.parseInt(jlItem4.getText());
            this.nomeProduto = jLbTitulo4.getText();
            this.valor = Double.parseDouble(jlbValor4.getText());
            ReservaPedido reserva = new ReservaPedido(idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador);
            dispose();
            new FinalizarCompra(reserva).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não pode reserva seu Produto!!");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (idComprador != idVendedor) {
            this.qtd = 1;
            this.idItem = Integer.parseInt(jlItem5.getText());
            this.nomeProduto = jLbTitulo5.getText();
            this.valor = Double.parseDouble(jlbValor5.getText());
            ReservaPedido reserva = new ReservaPedido(idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador);
            dispose();
            new FinalizarCompra(reserva).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não pode reserva seu Produto!!");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (idComprador != idVendedor) {
            this.qtd = 1;
            this.idItem = Integer.parseInt(jlItem6.getText());
            this.nomeProduto = jLbTitulo6.getText();
            this.valor = Double.parseDouble(jlbValor6.getText());
            ReservaPedido reserva = new ReservaPedido(idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador);
            dispose();
            new FinalizarCompra(reserva).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não pode reserva seu Produto!!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltaActionPerformed
        
        dispose();
        new Menu(this.idComprador).setVisible(true);
    }//GEN-LAST:event_btnVoltaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Avaliacao;
    private javax.swing.JLabel DescricaoDoVendedor;
    private javax.swing.JLabel NomeLoja;
    private javax.swing.JLabel NomeVendedor;
    private javax.swing.JLabel Produtos;
    private javax.swing.JButton btnVolta;
    private javax.swing.JLabel categoria;
    private javax.swing.JLabel horarioDaVenda;
    private javax.swing.JLabel imagemDestaqueDaLoja;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbMiniDescricao1;
    private javax.swing.JLabel jLbMiniDescricao2;
    private javax.swing.JLabel jLbMiniDescricao3;
    private javax.swing.JLabel jLbMiniDescricao4;
    private javax.swing.JLabel jLbMiniDescricao5;
    private javax.swing.JLabel jLbMiniDescricao6;
    private javax.swing.JLabel jLbTitulo1;
    private javax.swing.JLabel jLbTitulo2;
    private javax.swing.JLabel jLbTitulo3;
    private javax.swing.JLabel jLbTitulo4;
    private javax.swing.JLabel jLbTitulo5;
    private javax.swing.JLabel jLbTitulo6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlItem1;
    private javax.swing.JLabel jlItem2;
    private javax.swing.JLabel jlItem3;
    private javax.swing.JLabel jlItem4;
    private javax.swing.JLabel jlItem5;
    private javax.swing.JLabel jlItem6;
    private javax.swing.JLabel jlbImagem1;
    private javax.swing.JLabel jlbImagem2;
    private javax.swing.JLabel jlbImagem3;
    private javax.swing.JLabel jlbImagem4;
    private javax.swing.JLabel jlbImagem5;
    private javax.swing.JLabel jlbImagem6;
    private javax.swing.JLabel jlbValidade1;
    private javax.swing.JLabel jlbValidade2;
    private javax.swing.JLabel jlbValidade3;
    private javax.swing.JLabel jlbValidade4;
    private javax.swing.JLabel jlbValidade5;
    private javax.swing.JLabel jlbValidade6;
    private javax.swing.JLabel jlbValor1;
    private javax.swing.JLabel jlbValor2;
    private javax.swing.JLabel jlbValor3;
    private javax.swing.JLabel jlbValor4;
    private javax.swing.JLabel jlbValor5;
    private javax.swing.JLabel jlbValor6;
    private javax.swing.JPanel panelProdutos;
    private javax.swing.JPanel panelVendedor;
    private javax.swing.JPanel produto1;
    private javax.swing.JPanel produto2;
    private javax.swing.JPanel produto3;
    private javax.swing.JPanel produto4;
    private javax.swing.JPanel produto5;
    private javax.swing.JPanel produto6;
    // End of variables declaration//GEN-END:variables
}
