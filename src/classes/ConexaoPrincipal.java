/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author cirol
 */
public class ConexaoPrincipal {
    public Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/projetointegrador",
                "root",
                "Ass4ssyn#"
            );
            return conn;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;        
        }
        
    }
}
