/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.dao;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Rainier
 */
public class PessoaJuridicaDAO {

    private final ConectorBD bd;
    private final SequenceManager sequence;

    public PessoaJuridicaDAO(ConectorBD bd) {
        this.bd = bd;
        this.sequence = new SequenceManager();
    }

    public PessoaJuridica getPessoa(int id) throws ClassNotFoundException, SQLException {
        String consulta = "select * from Pessoa "
                + "inner join PessoaJuridica on (Pessoa.idPessoa = PessoaJuridica.Pessoa_idPessoa) "
                + "where Pessoa_idPessoa = ?";

        PreparedStatement ps = bd.getPrepared(consulta);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PessoaJuridica pessoa = new PessoaJuridica();
            pessoa.setId(rs.getInt("Pessoa_idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCnpj(rs.getString("cnpj"));
            bd.Close();
            return pessoa;
        }

        bd.Close();
        return null;
    }

    public List<PessoaJuridica> getPessoas() throws ClassNotFoundException, SQLException {
        ArrayList<PessoaJuridica> arraypj = new ArrayList();
        String consulta = "select * from Pessoa "
                + "inner join PessoaJuridica on (Pessoa.idPessoa = PessoaJuridica.Pessoa_idPessoa) ";

        ResultSet rs = bd.getSelect(consulta);
        while (rs.next()) {
            PessoaJuridica pessoa = new PessoaJuridica();
            pessoa.setId(rs.getInt("Pessoa_idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCnpj(rs.getString("cnpj"));

            arraypj.add(pessoa);
        }

        bd.Close();
        return arraypj;
    }

    public void incluir(PessoaJuridica pj) throws SQLException, ClassNotFoundException {
        String inserir = "insert into Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) values \n"
                + "(?, ?, ?, ?, ?, ?, ?);\n"
                + "\n"
                + "insert into PessoaJuridica(Pessoa_idPessoa, cnpj) values \n"
                + "(?, ?);";

        int id = sequence.getValue(bd);

        PreparedStatement ps = bd.getPrepared(inserir);
        ps.setInt(1, id);
        ps.setString(2, pj.getNome());
        ps.setString(3, pj.getLogradouro());
        ps.setString(4, pj.getCidade());
        ps.setString(5, pj.getEstado());
        ps.setString(6, pj.getTelefone());
        ps.setString(7, pj.getEmail());
        ps.setInt(8, id);
        ps.setString(9, pj.getCnpj());

        ps.executeUpdate();

        bd.Close();

    }

    public void alterar(PessoaJuridica pj) throws SQLException, ClassNotFoundException {
        String alterar = "UPDATE Pessoa "
                + "SET nome = ?,"
                + "logradouro = ?,"
                + "cidade= ?,"
                + "estado = ?,"
                + "telefone = ?,"
                + "email = ? "
                + "WHERE idPessoa = ?;\n"
                + "UPDATE PessoaJuridica "
                + "SET cnpj = ? "
                + "WHERE Pessoa_idPessoa = ?";

        PreparedStatement ps = bd.getPrepared(alterar);
        ps.setString(1, pj.getNome());
        ps.setString(2, pj.getLogradouro());
        ps.setString(3, pj.getCidade());
        ps.setString(4, pj.getEstado());
        ps.setString(5, pj.getTelefone());
        ps.setString(6, pj.getEmail());
        ps.setInt(7, pj.getId());
        ps.setString(8, pj.getCnpj());
        ps.setInt(9, pj.getId());
        ps.executeUpdate();

        bd.Close();

    }

    public void excluir(PessoaJuridica pj) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM PessoaJuridica WHERE Pessoa_idPessoa = ?;\n"
                + "DELETE FROM Pessoa WHERE idPessoa = ?";
        PreparedStatement ps = bd.getPrepared(delete);
        ps.setInt(1, pj.getId());
        ps.setInt(2, pj.getId());
        ps.executeUpdate();

        bd.Close();
    }

}
