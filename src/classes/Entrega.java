/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Date;

/**
 *
 * @author cirol
 */
public class Entrega {


    public String getDescricaoEnt() {
        return descricaoEnt;
    }

    
    public void setDescricaoEnt(String descricaoEnt) {
        this.descricaoEnt = descricaoEnt;
    }

   
    public String getObra() {
        return Obra;
    }

    
    public void setObra(String Obra) {
        this.Obra = Obra;
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

    
    public Date getRecebimento() {
        return recebimento;
    }

    
    public void setRecebimento(Date recebimento) {
        this.recebimento = recebimento;
    }

    private int id;
    private String descricaoEnt;
    private double quantidade;
    private Date recebimento;
    private String Obra;

    public Entrega(int id, String descricaoEnt, double quantidade, Date recebimento, String Obra) {
        this.id = id;
        this.descricaoEnt = descricaoEnt;
        this.quantidade = quantidade;
        this.recebimento = recebimento;
        this.Obra = Obra;
    }
    
    public Entrega() {
    }
}

