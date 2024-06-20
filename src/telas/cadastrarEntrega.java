/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.ConexaoEntrega;
import classes.ConexaoEstoque;
import classes.Entrega;
import classes.Estoque;
import classes.ListaMateriais;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author cirol
 */
public class cadastrarEntrega extends javax.swing.JFrame {

    private final List<Entrega> listaEnt = ListaMateriais.listarEnt();
    ArrayList<Integer> numeros = new ArrayList<>();
    
    /**
     * Creates new form cadastrarEntrega
     */
    public cadastrarEntrega() {
        
        initComponents();
        preencherCombo();
        limpaTela();
        int i = 0;
        while(tabelaCad.getRowCount()>i){
        String idStr = String.valueOf(tabelaCad.getValueAt(i, 0));
        int id = Integer.parseInt(idStr);
        numeros.add(id);
        i++;
        }
        tabelaCad.removeColumn(tabelaCad.getColumnModel().getColumn(0));
    }

    private void preencherTabelaCad(String filtro) {
        
        
        ConexaoEntrega con = new ConexaoEntrega();
        boolean status = con.conectar();

        if (status == false) {
            JOptionPane.showMessageDialog(null, "Não foi possível fazer a conexão");
        } else {
            List<Entrega> listaEnt = con.listagemEnt(filtro);
            DefaultTableModel tabelaCadastro = (DefaultTableModel) tabelaCad.getModel();
            tabelaCad.setRowSorter(new TableRowSorter(tabelaCadastro));

            tabelaCadastro.setNumRows(0);
            for (Entrega f : listaEnt) {
                Object[] obj = new Object[]{
                    f.getId(),
                    f.getDescricaoEnt(),
                    f.getQuantidade(),
                    new SimpleDateFormat("dd/MM/yyyy").format(f.getRecebimento().getTime()),
                    f.getObra()
                };
                tabelaCadastro.addRow(obj);

            }
            con.desconectar();
        }
    }
    private void preencherCombo() {
        Estoque est = new Estoque();
        ConexaoEstoque conEst = new ConexaoEstoque();
        boolean status = conEst.conectar();
        if (status == false) {  
            JOptionPane.showMessageDialog(rootPane, "puts");
        } else {

            List<Estoque> lista = conEst.getEstoqueDesc();
            //Percorrer essa lista e cada empresa que ele achar, colocar dentro da caixa de combinação
            for (Estoque c : lista) {
                comboDesc.addItem(c);//devido ao ajuste que fizemos na caixa de combinação, podemos adicionar a ela objetos, e não mais somente String
            }
            conEst.desconectar();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Cadastrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCad = new javax.swing.JTable();
        preencheQuant = new javax.swing.JTextField();
        preencheData = new javax.swing.JTextField();
        Voltar = new javax.swing.JButton();
        comboDesc = new javax.swing.JComboBox();
        comboObra = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setText("Cadastrar Entrega");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Local(Obra) da Entrega:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Descrição do Produto:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Data de Entrega:");

        Cadastrar.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        tabelaCad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id do Prduto", "Descrição do Prduto", "Quantidade", "Recebimento", "Obra"
            }
        ));
        jScrollPane1.setViewportView(tabelaCad);

        Voltar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Voltar.setText("<");
        Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarActionPerformed(evt);
            }
        });

        comboObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Obras", "Paulo", "Julia", "Mateus", "João", "Francine" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 4, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addGap(116, 116, 116))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboObra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(preencheQuant)
                                    .addComponent(preencheData, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Voltar)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preencheQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preencheData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        Entrega ent = new Entrega();
        ConexaoEntrega con = new ConexaoEntrega();
        boolean status;
        int resposta;
        String materiais = String.valueOf(comboDesc.getSelectedItem());
        if(comboObra.getSelectedItem().equals("Obras") || materiais.equals("Materiais")){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
        }else{
        ent.setDescricaoEnt(String.valueOf(comboDesc.getSelectedItem()));
        ent.setQuantidade(Double.parseDouble(preencheQuant.getText()));
        String dataRecebida = preencheData.getText();
        ent.setObra((String) comboObra.getSelectedItem());
        SimpleDateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataConvertida = conversor.parse(dataRecebida);
            java.sql.Date sqlDate = new java.sql.Date(dataConvertida.getTime());
            ent.setRecebimento(sqlDate);
        } catch (ParseException pe) {
            System.out.println("Erro" + pe.getMessage());
        }

        status = con.conectar();
        
        if (status == false) {
            System.out.println("ERRO");
        } else {
            resposta = con.salvar(ent);
            if (resposta == 1) {
                numeros.add(numeros.size()+1);
                limpaTela();
            } else if (resposta == 1062) {
                JOptionPane.showMessageDialog(null, "O filme já foi cadastrado");
            } else {
                JOptionPane.showMessageDialog(null, "Erro " + resposta);
            }
            
        }

        con.desconectar();}
    }//GEN-LAST:event_CadastrarActionPerformed

    public void limpaTela() {
        
        Cadastrar.setEnabled(true);
        //comboDesc.setSelectedItem("Obras");
        preencheQuant.setText("");
        preencheData.setText("");
        comboObra.setSelectedItem("Obras");
        comboDesc.requestFocus();
        this.preencherTabelaCad("");
        //this.preencherTabelaCategoria("");
    }

    public String formatarData(String data) {
        String dia = data.substring(8);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        return dia + "/" + mes + "/" + ano;
    }
    private void VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarActionPerformed
        menuPrincipal menu = new menuPrincipal();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_VoltarActionPerformed

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
            java.util.logging.Logger.getLogger(cadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cadastrarEntrega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Voltar;
    private javax.swing.JComboBox comboDesc;
    private javax.swing.JComboBox<String> comboObra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField preencheData;
    private javax.swing.JTextField preencheQuant;
    private javax.swing.JTable tabelaCad;
    // End of variables declaration//GEN-END:variables
}
