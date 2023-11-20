/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.dao.PessoaFisicaDAO;
import cadastrobd.model.dao.PessoaJuridicaDAO;
import cadastrobd.model.util.ConectorBD;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Joao Rainier
 */
public class CadastroBDTeste {

    private final ConectorBD bd;
    private final PessoaFisicaDAO pfDao;
    private final PessoaJuridicaDAO pjDao;

    public CadastroBDTeste() {
        bd = new ConectorBD();
        pfDao = new PessoaFisicaDAO(bd);
        pjDao = new PessoaJuridicaDAO(bd);
    }

    public void CriarPessoaFisica() throws SQLException, ClassNotFoundException {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Roxanne");
        pf.setLogradouro("SCLRN 711, Bloco T, Asa Norte");
        pf.setCidade("Brasilia");
        pf.setEstado("DF");
        pf.setTelefone("4040-4040");
        pf.setEmail("roxanne@norte.com");
        pf.setCpf("05388069762");

        pfDao.incluir(pf);
    }

    public void AlterarPessoaFisica() throws SQLException, ClassNotFoundException {
        PessoaFisica pessoa = pfDao.getPessoa(2);
        pessoa.setEmail("roxanne@norte.com");
        pessoa.setCpf("01234567896");
        pfDao.alterar(pessoa);
    }

    public void ListarPessoasFisicas() throws SQLException, ClassNotFoundException {
        List<PessoaFisica> arraypf = pfDao.getPessoas();
        for (PessoaFisica pessoaFisica : arraypf) {
            System.out.println(pessoaFisica.exibir());
        }
    }

    public void ExcluirPessoaFisica() throws SQLException, ClassNotFoundException {
        PessoaFisica pessoa = pfDao.getPessoa(22);
        pfDao.excluir(pessoa);
    }

    public void CriarPessoaJuridica() throws SQLException, ClassNotFoundException {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Kwame Fatumbi");
        pj.setLogradouro("CLS 310, bloco B, Asa Sul");
        pj.setCidade("Asa Sul");
        pj.setEstado("DF");
        pj.setTelefone("3030-3030");
        pj.setEmail("kfatumbi@sul.com");
        pj.setCnpj("98745632102");

        pjDao.incluir(pj);
    }

    public void AlterarPessoaJuridica() throws SQLException, ClassNotFoundException {
        PessoaJuridica pessoa = pjDao.getPessoa(23);
        pessoa.setEmail("kfatumbi@norte.com");
        pessoa.setCnpj("57985210236");
        pjDao.alterar(pessoa);
    }

    public void ListarPessoasJuridicas() throws SQLException, ClassNotFoundException {
        List<PessoaJuridica> arraypj = pjDao.getPessoas();
        for (PessoaJuridica pessoaJuridica : arraypj) {
            System.out.println(pessoaJuridica.exibir());
        }
    }

    public void ExcluirPessoaJuridica() throws SQLException, ClassNotFoundException {
        PessoaJuridica pessoa = pjDao.getPessoa(23);
        pjDao.excluir(pessoa);
    }
}
