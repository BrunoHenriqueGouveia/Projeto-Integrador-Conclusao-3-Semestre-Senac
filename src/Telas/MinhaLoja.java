/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import dados.AppDao;
import dados.Item;
import dados.Utilitarios;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import telas.Menu;

/**
 *
 * @author bruno.hgsilva3
 */
public class MinhaLoja extends javax.swing.JFrame {

    /**
     * Creates new form MinhaLoja
     */
    private int idVendedor;
    private String item;

    public MinhaLoja() {
        initComponents();
    }

    public MinhaLoja(int idVendedor) {
        initComponents();
        this.idVendedor = idVendedor;
        carregarDados();
    }

    public String RetornaStatus(int idReserva) {
        String status = null;
        try {

            ResultSet reserva = new AppDao().buscarReserva(idReserva);
            // Adicionar dados na tabela
            while (reserva.next()) {
                String statusComprador = reserva.getString("statusComprador");
                String statusVendedor = reserva.getString("statusVendedor");

                if (statusVendedor.equals("Concluido")) {

                    if (statusComprador.equals(statusVendedor)) {
                        status = "Concluido";
                    } else {
                        status = "Pendente";
                    }
                } else {
                    if (statusComprador.equals("Concluido")) {
                        status = "Concluido";
                    } else {
                        status = "Pendente";
                    }
                }
            }
            return status;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Entre em contato com o suporte e informe o erro: " + ex.getMessage());
        }
        return status;
    }

    public void TrocarStatus(int idReserva) {
        try {
            new AppDao().alterarStatus(idReserva, "Concluido", "statusVendedor");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Entre em contato com o suporte e informe o erro: " + ex.getMessage());
        }

    }

    public void carregarDados() {
        try {
            ResultSet historico = new AppDao().buscarHistoricoVendas(this.idVendedor);
            DefaultTableModel tblModelo = (DefaultTableModel) tblHistorico.getModel();

            // Limpar a tabela antes de carregar os novos dados
            tblModelo.setRowCount(0);

            // Adicionar dados na tabela
            while (historico.next()) {
                String statusComprador = historico.getString("statusComprador");
                String statusVendedor = historico.getString("statusVendedor");
                String status = null;

                if (statusComprador.equals(statusVendedor)) {
                    if (statusComprador.equals("Concluido")) {
                        status = "Concluido";
                    } else {
                        status = "Pendente";
                    }
                } else {
                    status = "Pendente";
                }

                Object dados[] = {
                    historico.getInt("idReserva"),
                    historico.getString("nomeProduto"),
                    historico.getInt("qtd"),
                    historico.getString("nomeLoja"),
                    historico.getString("nomeComprador"),
                    historico.getString("localDaReserva"),
                    historico.getString("horario"),
                    historico.getString("data"),
                    historico.getDouble("valor"),
                    status,
                    "Confirma"
                };
                tblModelo.addRow(dados);
            }

            // Configurar renderer e editor para a coluna do botão
            tblHistorico.getColumn("Confirma").setCellRenderer(new ButtonRenderer());
            tblHistorico.getColumn("Confirma").setCellEditor(new ButtonEditor(new JCheckBox()));

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Entre em contato com o suporte e informe o erro: " + ex.getMessage());
        }
    }

// Renderer personalizado
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

// Editor personalizado
    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            // Adicionar evento ao botão
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            // Verifica se o botão foi clicado
            if (clicked) {
                // Lógica ao clicar no botão
                int linha = tblHistorico.getSelectedRow(); // Obtém o índice da linha selecionada (0 para a primeira linha)
                int coluna = 9; // Índice da coluna "botão"
                int colunaId = 0; // Índice da coluna que contém o ID da reserva

                // Recupera o valor na célula da colunaId
                Object valueAtColunaId = tblHistorico.getValueAt(linha, colunaId);

                // Verifica se o valor é um Integer ou String e trata de acordo
                int idReserva;
                if (valueAtColunaId instanceof Integer) {
                    // Se o valor for um Integer, faz o cast diretamente
                    idReserva = (Integer) valueAtColunaId;
                } else if (valueAtColunaId instanceof String) {
                    // Se o valor for uma String, converte para Integer
                    idReserva = Integer.parseInt((String) valueAtColunaId);
                } else {
                    // Se o valor não for nem Integer nem String, lança uma exceção
                    throw new IllegalArgumentException("Tipo de dado inesperado: " + valueAtColunaId.getClass().getName());
                }

                // Recupera o status da reserva na coluna correspondente
                String status = (String) tblHistorico.getValueAt(linha, coluna);

                // Verifica se o status é "Pendente"
                if (status.equals("Pendente")) {
                    // Chama o método para trocar o status da reserva
                    TrocarStatus(idReserva);

                    // Atualiza o valor da célula com o novo status
                    String retornaStatus = RetornaStatus(idReserva);
                    tblHistorico.setValueAt(retornaStatus, linha, coluna);

                    if (retornaStatus.equals("Pendente")) {
                        JOptionPane.showMessageDialog(null, "Comprador Precisa confirma para Concluir a Compra");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pedido Concluido Comprador ja confirmou");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pedido ja foi Concluido!!");
                }
            }
            // Reseta a variável clicked para false
            clicked = false;
            // Retorna o valor do label (presumivelmente um componente visual)
            return label;
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCads = new javax.swing.JPanel();
        jPanelPedidos = new javax.swing.JPanel();
        jlTitulo1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorico = new javax.swing.JTable();
        jPanelAterarDadosLoja = new javax.swing.JPanel();
        jlTitulo3 = new javax.swing.JLabel();
        jlNomeLoja1 = new javax.swing.JLabel();
        jTxtNomeLoja = new javax.swing.JTextField();
        jlMiniDescricaoLoja = new javax.swing.JLabel();
        jTxtMiniDescricao = new javax.swing.JTextField();
        jlCategoria = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jlCapaLoja1 = new javax.swing.JLabel();
        jComboBoxLocalRetirada = new javax.swing.JComboBox<>();
        jlHrVenda = new javax.swing.JLabel();
        jComboBoxHorarioVenda = new javax.swing.JComboBox<>();
        btnAlterar = new javax.swing.JButton();
        jPanelAlterarItem = new javax.swing.JPanel();
        jPanelItem1 = new javax.swing.JPanel();
        jlNomeItem1 = new javax.swing.JLabel();
        jTextNomeItem1 = new javax.swing.JTextField();
        jlMiniDescricao1 = new javax.swing.JLabel();
        jTextMiniDescricao1 = new javax.swing.JTextField();
        jlDescricao1 = new javax.swing.JLabel();
        jTextDescricao1 = new javax.swing.JTextField();
        jlValidade1 = new javax.swing.JLabel();
        jTextValidade1 = new javax.swing.JTextField();
        jlQtd1 = new javax.swing.JLabel();
        jTextQtd1 = new javax.swing.JTextField();
        jlPreco1 = new javax.swing.JLabel();
        jTextPreco1 = new javax.swing.JTextField();
        btnAlterarItem = new javax.swing.JButton();
        jlTitulo4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMLoja = new javax.swing.JMenu();
        jMAlterarLoja = new javax.swing.JMenuItem();
        jMExcluirLoja = new javax.swing.JMenuItem();
        jMSairLoja = new javax.swing.JMenuItem();
        jMPedidos = new javax.swing.JMenu();
        jMIPedidos = new javax.swing.JMenuItem();
        jMItens = new javax.swing.JMenu();
        jMAlterarItem = new javax.swing.JMenuItem();
        jMExcluirItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCads.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCads.setLayout(new java.awt.CardLayout());

        jPanelPedidos.setBackground(new java.awt.Color(255, 255, 255));

        jlTitulo1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jlTitulo1.setForeground(new java.awt.Color(255, 153, 51));
        jlTitulo1.setText("Pedidos");

        tblHistorico.setBackground(new java.awt.Color(255, 204, 102));
        tblHistorico.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        tblHistorico.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tblHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Reserva", "Nome Produto", "Quantidade", "Nome Loja", "Nome Comprador", "Local Retirada", "Horario Retirada", "Data", "Valor", "Status", "Confirma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistorico.setGridColor(new java.awt.Color(0, 0, 0));
        tblHistorico.setMaximumSize(new java.awt.Dimension(2147483647, 5000));
        tblHistorico.setRowHeight(50);
        tblHistorico.setRowMargin(10);
        jScrollPane1.setViewportView(tblHistorico);

        javax.swing.GroupLayout jPanelPedidosLayout = new javax.swing.GroupLayout(jPanelPedidos);
        jPanelPedidos.setLayout(jPanelPedidosLayout);
        jPanelPedidosLayout.setHorizontalGroup(
            jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPedidosLayout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addComponent(jlTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
            .addGroup(jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
        );
        jPanelPedidosLayout.setVerticalGroup(
            jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPedidosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jlTitulo1)
                .addGap(402, 402, 402))
            .addGroup(jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPedidosLayout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)))
        );

        jPanelCads.add(jPanelPedidos, "Pedidos");

        jPanelAterarDadosLoja.setBackground(new java.awt.Color(255, 255, 255));

        jlTitulo3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jlTitulo3.setForeground(new java.awt.Color(255, 153, 51));
        jlTitulo3.setText("        Alterar Dados Loja");

        jlNomeLoja1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlNomeLoja1.setForeground(new java.awt.Color(0, 51, 102));
        jlNomeLoja1.setText("  Nome Da Loja:");

        jlMiniDescricaoLoja.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlMiniDescricaoLoja.setForeground(new java.awt.Color(0, 51, 102));
        jlMiniDescricaoLoja.setText("Mini Descrição Da Loja:");

        jlCategoria.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlCategoria.setForeground(new java.awt.Color(0, 51, 102));
        jlCategoria.setText("         Categoria:");

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alimentação e Bebidas", "Moda e Vestuário", "Eletrônicos e Tecnologia", "Casa e Decoração", "Saúde e Beleza", "Esportes e Lazer", "Livros, Música e Papelaria", "Automotivo", "Infantil" }));

        jlCapaLoja1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlCapaLoja1.setForeground(new java.awt.Color(0, 51, 102));
        jlCapaLoja1.setText("Ponto De Encontro:");

        jComboBoxLocalRetirada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Portaria 1", "Portaria 2", "Biblioteca", "Praca Alimentacao 1", "Praca Alimentacao 2", "Praca Alimentacao 3", "Auditorio " }));

        jlHrVenda.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlHrVenda.setForeground(new java.awt.Color(0, 51, 102));
        jlHrVenda.setText("Horario de venda:");

        jComboBoxHorarioVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manha", "Tarde", "Noite" }));

        btnAlterar.setBackground(new java.awt.Color(0, 153, 0));
        btnAlterar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Salvar Alteração");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAterarDadosLojaLayout = new javax.swing.GroupLayout(jPanelAterarDadosLoja);
        jPanelAterarDadosLoja.setLayout(jPanelAterarDadosLojaLayout);
        jPanelAterarDadosLojaLayout.setHorizontalGroup(
            jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAterarDadosLojaLayout.createSequentialGroup()
                .addContainerGap(253, Short.MAX_VALUE)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAterarDadosLojaLayout.createSequentialGroup()
                        .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlMiniDescricaoLoja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNomeLoja1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlCapaLoja1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlHrVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTxtMiniDescricao)
                            .addComponent(jComboBoxLocalRetirada, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, 192, Short.MAX_VALUE)
                            .addComponent(jComboBoxHorarioVenda, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtNomeLoja))))
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanelAterarDadosLojaLayout.setVerticalGroup(
            jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAterarDadosLojaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlTitulo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNomeLoja1)
                    .addComponent(jTxtNomeLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMiniDescricaoLoja)
                    .addComponent(jTxtMiniDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCapaLoja1)
                    .addComponent(jComboBoxLocalRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCategoria)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAterarDadosLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlHrVenda)
                    .addComponent(jComboBoxHorarioVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnAlterar)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jPanelCads.add(jPanelAterarDadosLoja, "AlterarDadosLoja");

        jPanelAlterarItem.setBackground(new java.awt.Color(255, 255, 255));

        jlNomeItem1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlNomeItem1.setForeground(new java.awt.Color(0, 51, 102));
        jlNomeItem1.setText("  Nome Produto:");

        jlMiniDescricao1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlMiniDescricao1.setForeground(new java.awt.Color(0, 51, 102));
        jlMiniDescricao1.setText("Mini Descrição:");

        jlDescricao1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlDescricao1.setForeground(new java.awt.Color(0, 51, 102));
        jlDescricao1.setText("        Descrição:");

        jlValidade1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlValidade1.setForeground(new java.awt.Color(0, 51, 102));
        jlValidade1.setText("          Validade:");

        jlQtd1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlQtd1.setForeground(new java.awt.Color(0, 51, 102));
        jlQtd1.setText("      Quantidade:");

        jlPreco1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jlPreco1.setForeground(new java.awt.Color(0, 51, 102));
        jlPreco1.setText("                Preço:");

        jTextPreco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPreco1ActionPerformed(evt);
            }
        });

        btnAlterarItem.setBackground(new java.awt.Color(0, 153, 0));
        btnAlterarItem.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnAlterarItem.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarItem.setText("Salvar Alteração");
        btnAlterarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarItemActionPerformed(evt);
            }
        });

        jlTitulo4.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jlTitulo4.setForeground(new java.awt.Color(255, 153, 51));
        jlTitulo4.setText("Alterar Item");

        javax.swing.GroupLayout jPanelItem1Layout = new javax.swing.GroupLayout(jPanelItem1);
        jPanelItem1.setLayout(jPanelItem1Layout);
        jPanelItem1Layout.setHorizontalGroup(
            jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItem1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAlterarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelItem1Layout.createSequentialGroup()
                            .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jlNomeItem1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(jlMiniDescricao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlDescricao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlPreco1)
                                .addComponent(jlQtd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlValidade1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelItem1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextValidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextQtd1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextPreco1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelItem1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextMiniDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelItem1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextNomeItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jlTitulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelItem1Layout.setVerticalGroup(
            jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItem1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlTitulo4)
                .addGap(42, 42, 42)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNomeItem1)
                    .addComponent(jTextNomeItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMiniDescricao1)
                    .addComponent(jTextMiniDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDescricao1)
                    .addComponent(jTextDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlValidade1)
                    .addComponent(jTextValidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlQtd1)
                    .addComponent(jTextQtd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPreco1)
                    .addComponent(jTextPreco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAlterarItem)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanelAlterarItemLayout = new javax.swing.GroupLayout(jPanelAlterarItem);
        jPanelAlterarItem.setLayout(jPanelAlterarItemLayout);
        jPanelAlterarItemLayout.setHorizontalGroup(
            jPanelAlterarItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlterarItemLayout.createSequentialGroup()
                .addContainerGap(208, Short.MAX_VALUE)
                .addComponent(jPanelItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanelAlterarItemLayout.setVerticalGroup(
            jPanelAlterarItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlterarItemLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jPanelItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanelCads.add(jPanelAlterarItem, "AlterarItem");

        jMLoja.setText("Loja");

        jMAlterarLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/alterar.png"))); // NOI18N
        jMAlterarLoja.setText("Alterar Dados");
        jMAlterarLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAlterarLojaActionPerformed(evt);
            }
        });
        jMLoja.add(jMAlterarLoja);

        jMExcluirLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/excluir.png"))); // NOI18N
        jMExcluirLoja.setText("Excluir Loja");
        jMExcluirLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMExcluirLojaActionPerformed(evt);
            }
        });
        jMLoja.add(jMExcluirLoja);

        jMSairLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/sair.png"))); // NOI18N
        jMSairLoja.setText("Sair");
        jMSairLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMSairLojaActionPerformed(evt);
            }
        });
        jMLoja.add(jMSairLoja);

        jMenuBar1.add(jMLoja);

        jMPedidos.setText("Pedidos");

        jMIPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/sacola-de-compras.png"))); // NOI18N
        jMIPedidos.setText("Pedidos");
        jMIPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIPedidosActionPerformed(evt);
            }
        });
        jMPedidos.add(jMIPedidos);

        jMenuBar1.add(jMPedidos);

        jMItens.setText("Itens");

        jMAlterarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/alterar.png"))); // NOI18N
        jMAlterarItem.setText("Alterar Item");
        jMAlterarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAlterarItemActionPerformed(evt);
            }
        });
        jMItens.add(jMAlterarItem);

        jMExcluirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/excluir.png"))); // NOI18N
        jMExcluirItem.setText("Excluir Item");
        jMExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMExcluirItemActionPerformed(evt);
            }
        });
        jMItens.add(jMExcluirItem);

        jMenuBar1.add(jMItens);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCads, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCads, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(914, 507));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMIPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIPedidosActionPerformed

        CardLayout cl = (CardLayout) jPanelCads.getLayout();
        cl.show(jPanelCads, "Pedidos");

    }//GEN-LAST:event_jMIPedidosActionPerformed

    private void jMAlterarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAlterarItemActionPerformed
        CardLayout cl = (CardLayout) jPanelCads.getLayout();
        cl.show(jPanelCads, "AlterarItem");

        //1 perguntar o usuario a alterar
        this.item = JOptionPane.showInputDialog(rootPane, "item a alterar:");

        try {
            ResultSet usuario = new AppDao().buscarItem(this.idVendedor, item);

            // verificar se encontrou usuario informado
            if (usuario.next()) {// se encontrou

                jTextNomeItem1.setText(usuario.getString("nomeProduto"));
                jTextMiniDescricao1.setText(usuario.getString("miniDescricaoProduto"));
                jTextDescricao1.setText(usuario.getString("descricaoProduto"));
                jTextValidade1.setText(usuario.getString("validade"));
                jTextQtd1.setText(usuario.getString("qtdProduto"));
                jTextPreco1.setText(usuario.getString("valor"));

            } else {// se nao encontrou
                JOptionPane.showMessageDialog(null, "Item não encontrado");
                dispose();
                new MinhaLoja(this.idVendedor).setVisible(true);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
        }
    }//GEN-LAST:event_jMAlterarItemActionPerformed

    private void jMAlterarLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAlterarLojaActionPerformed
        CardLayout cl = (CardLayout) jPanelCads.getLayout();
        cl.show(jPanelCads, "AlterarDadosLoja");

        try {
            ResultSet usuario = new AppDao().buscarLoja(this.idVendedor);

            // verificar se encontrou usuario informado
            if (usuario.next()) {// se encontrou

                jTxtMiniDescricao.setText(usuario.getString("miniDescricao"));
                jTxtNomeLoja.setText(usuario.getString("nomeLoja"));
                jComboBoxCategoria.setSelectedItem(usuario.getString("categoria"));
                jComboBoxLocalRetirada.setSelectedItem(usuario.getString("localRetirada"));
                jComboBoxHorarioVenda.setSelectedItem(usuario.getString("horarioVenda"));

            } else {// se nao encontrou
                JOptionPane.showMessageDialog(null, "Loja não encontrado");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
        }
    }//GEN-LAST:event_jMAlterarLojaActionPerformed

    private void jMSairLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMSairLojaActionPerformed

        dispose();
        new Menu(this.idVendedor).setVisible(true);
    }//GEN-LAST:event_jMSairLojaActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        try {
            String miniDescricao = jTxtMiniDescricao.getText();
            String nomeLoja = jTxtNomeLoja.getText();
            String categoria = jComboBoxCategoria.getSelectedItem().toString();
            String pontoDeEncontro = jComboBoxLocalRetirada.getSelectedItem().toString();
            String horarioVenda = jComboBoxHorarioVenda.getSelectedItem().toString();

            if (miniDescricao.length() > 0 && nomeLoja.length() > 0) {

                new AppDao().alterarDadosLoja(nomeLoja, miniDescricao,
                        categoria, pontoDeEncontro,
                        horarioVenda, idVendedor);

                // avisar usuario se os dados foram salvos ou não
                JOptionPane.showMessageDialog(null, "Loja Alterada com sucesso");

                dispose();
                new MinhaLoja(this.idVendedor).setVisible(true);

                jTxtNomeLoja.setText("");// apagar cambo txt
                jTxtMiniDescricao.setText("");
                jComboBoxCategoria.requestFocus();// apagar o combo box
                jComboBoxLocalRetirada.requestFocus();// apagar o combo box

            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!!");
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar localizar Driver JDBC");
        } catch (SQLException ex) {

            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Nome da loja ja existe");
            } else {
                JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void jMExcluirLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMExcluirLojaActionPerformed

        try {
            if (JOptionPane.showConfirmDialog(null, "Certeza que deseja excluir sua loja!!") == 0) {

                int retornoItens = new AppDao().excluirTodosItens(this.idVendedor);
                int retornoLoja = new AppDao().excluirLoja(this.idVendedor);
                int retornoVendedor = new AppDao().excluirVendedor(this.idVendedor);

                System.out.println(retornoItens + retornoLoja + retornoVendedor);
                if (retornoItens == 1 || retornoLoja == 1 || retornoVendedor == 1) {
                    JOptionPane.showMessageDialog(null, "Loja excluido com sucesso");
                    dispose();
                    new Menu(this.idVendedor).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Loja não existe");
                    dispose();
                    new Menu(this.idVendedor).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Loja não excluida!!");
            }

        } // quando a mensagem for a mesma use catch multiplo // quando a mensagem for a mesma use catch multiplo // quando a mensagem for a mesma use catch multiplo // quando a mensagem for a mesma use catch multiplo
        catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro:" + ex.getMessage());
        }
    }//GEN-LAST:event_jMExcluirLojaActionPerformed

    private void jTextPreco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPreco1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPreco1ActionPerformed

    private void btnAlterarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarItemActionPerformed

        try {
            String nomeProduto1 = jTextNomeItem1.getText();
            String miniDescricaoProduto1 = jTextMiniDescricao1.getText();
            String descricaoProduto1 = jTextDescricao1.getText();
            String validade1 = jTextValidade1.getText();
            int qtdProduto1 = 0;
            double preco1 = 0;
            Item item = null;
            if (nomeProduto1.length() > 0 && miniDescricaoProduto1.length() > 0 && descricaoProduto1.length() > 0) {

                if (Utilitarios.validarDataValidade(validade1)) {

                    if (Utilitarios.ehNumeroValido(jTextQtd1.getText())) {

                        if (Utilitarios.ehDoubleValido(jTextPreco1.getText())) {

                            qtdProduto1 = Integer.parseInt(jTextQtd1.getText());
                            preco1 = Double.parseDouble(jTextPreco1.getText());

                            item = new Item(nomeProduto1, miniDescricaoProduto1, descricaoProduto1, validade1, qtdProduto1, preco1);

                            new AppDao().alterarItem(item, this.idVendedor, this.item);
                            JOptionPane.showMessageDialog(null, "Item Alterada com sucesso");

                            dispose();
                            new MinhaLoja(this.idVendedor).setVisible(true);
                            jTextNomeItem1.setText("");// apagar cambo txt
                            jTextMiniDescricao1.setText("");
                            jTextDescricao1.setText("");
                            jTextValidade1.setText("");
                            jTextQtd1.setText("");
                            jTextPreco1.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "Valor Invalido!!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Quantidade Invalida!!");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Validade invalida ex: 12/11/2025 ");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos do produto 1!!");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
        }
    }//GEN-LAST:event_btnAlterarItemActionPerformed

    private void jMExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMExcluirItemActionPerformed

        try {
            String nomeItem = JOptionPane.showInputDialog("Informe o nome do item a ser excluido:");

            if (JOptionPane.showConfirmDialog(null, "Certeza que deseja excluir item: " + nomeItem) == 0) {

                int retornoItens = new AppDao().excluirItem(this.idVendedor, nomeItem);

                System.out.println(retornoItens);
                if (retornoItens == 1) {
                    JOptionPane.showMessageDialog(null, "item excluido com sucesso");
                    dispose();
                    new MinhaLoja(this.idVendedor).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "item não existe");
                    dispose();
                    new MinhaLoja(this.idVendedor).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "item não excluida!!");
            }

        } // quando a mensagem for a mesma use catch multiplo
        catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro:" + ex.getMessage());
        }
    }//GEN-LAST:event_jMExcluirItemActionPerformed

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
            java.util.logging.Logger.getLogger(MinhaLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinhaLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinhaLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinhaLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinhaLoja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterarItem;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JComboBox<String> jComboBoxHorarioVenda;
    private javax.swing.JComboBox<String> jComboBoxLocalRetirada;
    private javax.swing.JMenuItem jMAlterarItem;
    private javax.swing.JMenuItem jMAlterarLoja;
    private javax.swing.JMenuItem jMExcluirItem;
    private javax.swing.JMenuItem jMExcluirLoja;
    private javax.swing.JMenuItem jMIPedidos;
    private javax.swing.JMenu jMItens;
    private javax.swing.JMenu jMLoja;
    private javax.swing.JMenu jMPedidos;
    private javax.swing.JMenuItem jMSairLoja;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanelAlterarItem;
    private javax.swing.JPanel jPanelAterarDadosLoja;
    private javax.swing.JPanel jPanelCads;
    private javax.swing.JPanel jPanelItem1;
    private javax.swing.JPanel jPanelPedidos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextDescricao1;
    private javax.swing.JTextField jTextMiniDescricao1;
    private javax.swing.JTextField jTextNomeItem1;
    private javax.swing.JTextField jTextPreco1;
    private javax.swing.JTextField jTextQtd1;
    private javax.swing.JTextField jTextValidade1;
    private javax.swing.JTextField jTxtMiniDescricao;
    private javax.swing.JTextField jTxtNomeLoja;
    private javax.swing.JLabel jlCapaLoja1;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlDescricao1;
    private javax.swing.JLabel jlHrVenda;
    private javax.swing.JLabel jlMiniDescricao1;
    private javax.swing.JLabel jlMiniDescricaoLoja;
    private javax.swing.JLabel jlNomeItem1;
    private javax.swing.JLabel jlNomeLoja1;
    private javax.swing.JLabel jlPreco1;
    private javax.swing.JLabel jlQtd1;
    private javax.swing.JLabel jlTitulo1;
    private javax.swing.JLabel jlTitulo3;
    private javax.swing.JLabel jlTitulo4;
    private javax.swing.JLabel jlValidade1;
    private javax.swing.JTable tblHistorico;
    // End of variables declaration//GEN-END:variables
}
