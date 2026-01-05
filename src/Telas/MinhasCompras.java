/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import dados.AppDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class MinhasCompras extends javax.swing.JFrame {

    /**
     * Creates new form MinhasCompras
     */
    private int idUsuario;

    public MinhasCompras() {
        initComponents();
    }

    public MinhasCompras(int id) {
        initComponents();
        this.idUsuario = id;
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

                if (statusComprador.equals("Concluido")) {

                    if (statusComprador.equals(statusVendedor)) {
                        status = "Concluido";
                    } else {
                        status = "Pendente";
                    }
                } else {
                    if (statusVendedor.equals("Concluido")) {
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
            new AppDao().alterarStatus(idReserva, "Concluido", "statusComprador");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Entre em contato com o suporte e informe o erro: " + ex.getMessage());
        }

    }

    public void carregarDados() {
        try {
            ResultSet historico = new AppDao().buscarHistoricoCompras(this.idUsuario);
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
                    historico.getString("nomeVendedor"),
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
                        JOptionPane.showMessageDialog(null, "Vendedor Precisa confirma para Concluir a Compra");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pedido Concluido Vendedor ja confirmou");
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

        jPanel2 = new javax.swing.JPanel();
        btnVolta = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnVolta.setBackground(new java.awt.Color(255, 153, 0));
        btnVolta.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVolta.setForeground(new java.awt.Color(255, 255, 255));
        btnVolta.setText("Volta");
        btnVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltaActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/LOGO.png"))); // NOI18N

        lblUsuario.setBackground(new java.awt.Color(0, 0, 0));
        lblUsuario.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 153, 0));
        lblUsuario.setText("Reservas");

        tblHistorico.setBackground(new java.awt.Color(255, 204, 102));
        tblHistorico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tblHistorico.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tblHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Reserva", "Nome Produto", "Quantidade", "Nome Loja", "Nome Vendedor", "Local Retirada", "Horario Retirada", "Data", "Valor", "Status", "Confirma"
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

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                        .addComponent(lblUsuario)
                        .addGap(0, 395, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolta)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(914, 507));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltaActionPerformed
        dispose();
        new telas.Menu(this.idUsuario).setVisible(true);
    }//GEN-LAST:event_btnVoltaActionPerformed

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
            java.util.logging.Logger.getLogger(MinhasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinhasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinhasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinhasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinhasCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolta;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel logo;
    private javax.swing.JTable tblHistorico;
    // End of variables declaration//GEN-END:variables
}
