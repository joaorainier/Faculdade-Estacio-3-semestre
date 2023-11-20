/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Joao Rainier
 */
public class PessoaFisica extends Pessoa {
    
    private String cpf;
    
    public PessoaFisica(){
        
    }
    
    public PessoaFisica(int id, String nome, String logradouro, String cidade, 
           String estado, String telefone, String email, String cpf){
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public String exibir(){
        return super.exibir()+ "\nCpf: " + getCpf();
    }
}
