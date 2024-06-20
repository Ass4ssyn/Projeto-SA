/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import ativ5projint.Usuario;
import classes.ConexaoEntrega;
import classes.ConexaoEstoque;
import classes.Entrega;
import classes.Estoque;
import classes.ListaMateriais;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author cirol
 */
public class listarEstoque extends javax.swing.JFrame {

    private final List<Estoque> listaEst = ListaMateriais.listarEst();
    Estoque est = new Estoque();
    telaQuantidadeUsada enviaTexto;
    alteraEstoque altera;

    public String proprietario = null;

    public void Proprietario(String dono) {
        proprietario = dono;
        preencherTabelaEst(proprietario);

    }

    /**
     * Creates new form listarEstoque
     */
    public listarEstoque() {
        initComponents();
        preencherTabelaEst("");
        Excluir.setEnabled(false);
        Alterar.setEnabled(false);
        relatarUso.setEnabled(false);

        //JOptionPane.showMessageDialog(null, tabelaEst.getRowCount());
        //tabelaEst.removeColumn(tabelaEst.getColumnModel().getColumn(0));
    }

    private void preencherTabelaEst(String filtro) {

        ConexaoEstoque con = new ConexaoEstoque();
        boolean status = con.conectar();

        if (status == false) {
            JOptionPane.showMessageDialog(null, "Não foi possível fazer a conexão");
        } else {
            List<Estoque> listaEst = con.listagemEst(filtro);
            DefaultTableModel tabelaEstoque = (DefaultTableModel) tabelaEst.getModel();
            tabelaEst.setRowSorter(new TableRowSorter(tabelaEstoque));

            tabelaEstoque.setNumRows(0);
            for (Estoque f : listaEst) {
                Object[] obj = new Object[]{
                    f.getId(),
                    f.getDescricaoEst(),
                    f.getQuantidade(),
                    f.getObraEst()
                };
                tabelaEstoque.addRow(obj);

            }

            con.desconectar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        relatarUso = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEst = new javax.swing.JTable();
        Voltar = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setText("Estoque");

        relatarUso.setBackground(new java.awt.Color(0, 0, 102));
        relatarUso.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        relatarUso.setForeground(new java.awt.Color(255, 255, 255));
        relatarUso.setText("Relatar Uso");
        relatarUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatarUsoActionPerformed(evt);
            }
        });

        tabelaEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id do Produto", "Descrição do Produto", "Quantidade", "Obra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEstMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEst);

        Voltar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Voltar.setText("<");
        Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarActionPerformed(evt);
            }
        });

        Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Alterar.setText("Alterar");
        Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterarActionPerformed(evt);
            }
        });

        Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Voltar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(relatarUso, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relatarUso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void relatarUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatarUsoActionPerformed
        int linha = tabelaEst.getSelectedRow();
        String produto = String.valueOf(tabelaEst.getValueAt(linha, 1));
        String obra = String.valueOf(tabelaEst.getValueAt(linha, 3));
        String idStr = String.valueOf(tabelaEst.getValueAt(linha, 0));
        int id = Integer.parseInt(idStr);
        if (linha < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um produto válido");
        } else {
            telaQuantidadeUsada cad = new telaQuantidadeUsada();
            cad.recebendo(produto, obra, id);
            cad.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_relatarUsoActionPerformed

    // Excluir informação

    private void VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarActionPerformed
        menuObras menu = new menuObras();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_VoltarActionPerformed

    private void tabelaEstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEstMouseClicked
        Excluir.setEnabled(true);
        Alterar.setEnabled(true);
        relatarUso.setEnabled(true);
    }//GEN-LAST:event_tabelaEstMouseClicked

    private void AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterarActionPerformed
        int linha = tabelaEst.getSelectedRow();
        String produto = String.valueOf(tabelaEst.getValueAt(linha, 1));
        double quantidade = Double.parseDouble(String.valueOf(tabelaEst.getValueAt(linha, 2)));
        String obra = String.valueOf(tabelaEst.getValueAt(linha, 3));

        String idStr = String.valueOf(tabelaEst.getValueAt(linha, 0));
        int id = Integer.parseInt(idStr);

        if (linha < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um produto válido");
        } else {
            if (altera == null) {
                altera = new alteraEstoque();
                altera.setVisible(true);
                altera.recebendo(quantidade, produto, obra, id);
                dispose();
            } else {
                altera.setVisible(true);
                altera.setState(alteraEstoque.NORMAL);
                altera.recebendo(quantidade, produto, obra, id);
                dispose();
            }
        }
    }//GEN-LAST:event_AlterarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        ConexaoEstoque con = new ConexaoEstoque();
        boolean status = con.conectar();

        if (status == false) {
            JOptionPane.showMessageDialog(null, "Verifique sua conexão");
        } else {

            int linha = tabelaEst.getSelectedRow();
            String idStr = String.valueOf(tabelaEst.getValueAt(linha, 0));
            int id = Integer.parseInt(idStr);
            boolean operacao = con.excluir(id);
            if (operacao == true) {

                Excluir.setEnabled(false);
                Alterar.setEnabled(false);
                relatarUso.setEnabled(false);
                preencherTabelaEst(proprietario);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a exclusão");
            }
            con.desconectar();
        }
    }//GEN-LAST:event_ExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(listarEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listarEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listarEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listarEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listarEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton Voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton relatarUso;
    private javax.swing.JTable tabelaEst;
    // End of variables declaration//GEN-END:variables
}
