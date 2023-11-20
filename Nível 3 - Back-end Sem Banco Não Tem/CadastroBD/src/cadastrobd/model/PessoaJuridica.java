/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Joao Rainier
 */
public class PessoaJuridica extends Pessoa {
    
    private String cnpj;
    
    public PessoaJuridica(){
    }
    
    public PessoaJuridica(int id, String nome, String logradouro, String cidade, 
           String estado, String telefone, String email, String cnpj){
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public String exibir(){
        return super.exibir()+ "\nCnpj: " + cnpj;
    }
            
}
