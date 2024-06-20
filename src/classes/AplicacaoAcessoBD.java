/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classes;

import classes.ConexaoEntrega;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cirol
 */
public class AplicacaoAcessoBD {

    public static void main(String[] args) {

        ConexaoEntrega conector = new ConexaoEntrega();
        conector.conectar();
        // TODO code application logic here
        try {
            Connection conn; // criando um objeto do tipo connection chamado conn 
            Statement st; //criando um objeto do tipo Statement chamado st para execução de comando SQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetointegrador", "root", "Ass4ssyn#");
            st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("driver não está disponível para acesso ou não existe");
        } catch (SQLException ex) {
            Logger.getLogger(AplicacaoAcessoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * @param args the command line arguments
         */
        // TODO code application logic here
        /**
         * @param args the command line arguments
         */
        // TODO code application logic here
    }

}
