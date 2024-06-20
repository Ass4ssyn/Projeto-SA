/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cirol
 */
public class ListaMateriais {
           
    private static final List<Entrega> listaEnt = new ArrayList<>();
    
    public static List<Entrega> listarEnt(){
        return listaEnt;
    }
     public static void adicionarEnt(Entrega ent){
        listaEnt.add(ent);
    }
     public static void atualizar(int id, Entrega ent){
        listaEnt.set(id,ent);
     }
    
    private static final List<Estoque> listaEst = new ArrayList<>();
    
    public static List<Estoque> listarEst(){
        return listaEst;
    }
    public static void adicionarEst(Estoque est){
        listaEst.add(est);
    }
    public static void atualizar(int id, Estoque est){
        listaEst.set(id,est);}
    
  
}
