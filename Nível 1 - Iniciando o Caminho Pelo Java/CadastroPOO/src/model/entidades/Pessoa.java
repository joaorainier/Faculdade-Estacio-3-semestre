/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joao Rainier
 */


package model.entidades;

import java.io.Serializable;

public class Pessoa implements Serializable {
    
    private int id;
    private String nome;
    
    public Pessoa (int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
     public int getId(){
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String exibir() {
        return "Id: "
                + id
                + "\nNome: "
                + nome;
    }
  
    
}
