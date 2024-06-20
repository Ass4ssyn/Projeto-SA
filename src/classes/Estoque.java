/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author cirol
 */
public class Estoque {

   
    public String getDescricaoEst() {
        return descricaoEst;
    }

   
    public void setDescricaoEst(String descricaoEst) {
        this.descricaoEst = descricaoEst;
    }

   
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

   
    public double getQuantidade() {
        return quantidade;
    }

   
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

   
    public String getObraEst() {
        return obraEst;
    }


    public void setObraEst(String obraEst) {
        this.obraEst = obraEst;
    }

    private int id;
    private String descricaoEst;
    private double quantidade;
    private String obraEst;
    
    public Estoque(int id, String descricaoEst, double quantidade, String obraEst) {
        this.id = id;
        this.descricaoEst = descricaoEst;
        this.quantidade = quantidade;
        this.obraEst = obraEst;
    }

    public Estoque() {
    }
    @Override
    public String toString(){
        return this.descricaoEst;
    }
}
