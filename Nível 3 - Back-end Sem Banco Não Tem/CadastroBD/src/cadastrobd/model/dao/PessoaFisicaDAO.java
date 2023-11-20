/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.dao;

import cadastrobd.model.PessoaFisica;
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
public class PessoaFisicaDAO {

    private final ConectorBD bd;
    private final SequenceManager sequence;

    public PessoaFisicaDAO(ConectorBD bd) {
        this.bd = bd;
        this.sequence = new SequenceManager();
    }

    public PessoaFisica getPessoa(int id) throws ClassNotFoundException, SQLException {
        String consulta = "select * from Pessoa "
                + "inner join PessoaFisica on (Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa) "
                + "where idPessoa = ?";

        PreparedStatement ps = bd.getPrepared(consulta);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PessoaFisica pessoa = new PessoaFisica();
            pessoa.setId(rs.getInt("idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));
            bd.Close();
            return pessoa;
        }

        bd.Close();
        return null;
    }

    public List<PessoaFisica> getPessoas() throws ClassNotFoundException, SQLException {
        ArrayList<PessoaFisica> arraypf = new ArrayList();
        String consulta = "select * from Pessoa "
                + "inner join PessoaFisica on (Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa) ";

        ResultSet rs = bd.getSelect(consulta);
        while (rs.next()) {
            PessoaFisica pessoa = new PessoaFisica();
            pessoa.setId(rs.getInt("idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));

            arraypf.add(pessoa);
        }

        bd.Close();
        return arraypf;
    }

    public void incluir(PessoaFisica pf) throws SQLException, ClassNotFoundException {
        String inserir = "insert into Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) values \n"
                + "(?, ?, ?, ?, ?, ?, ?);\n"
                + "\n"
                + "insert into PessoaFisica(Pessoa_idPessoa, cpf) values \n"
                + "(?, ?);";

        int id = sequence.getValue(bd);

        PreparedStatement ps = bd.getPrepared(inserir);
        ps.setInt(1, id);
        ps.setString(2, pf.getNome());
        ps.setString(3, pf.getLogradouro());
        ps.setString(4, pf.getCidade());
        ps.setString(5, pf.getEstado());
        ps.setString(6, pf.getTelefone());
        ps.setString(7, pf.getEmail());
        ps.setInt(8, id);
        ps.setString(9, pf.getCpf());

        ps.executeUpdate();

        bd.Close();

    }

    public void alterar(PessoaFisica pf) throws SQLException, ClassNotFoundException {
        String alterar = "UPDATE Pessoa "
                + "SET nome = ?,"
                + "logradouro = ?,"
                + "cidade= ?,"
                + "estado = ?,"
                + "telefone = ?,"
                + "email = ? "
                + "WHERE idPessoa = ?;\n"
                + "UPDATE PessoaFisica "
                + "SET cpf = ? "
                + "WHERE Pessoa_idPessoa = ?";

        PreparedStatement ps = bd.getPrepared(alterar);
        ps.setString(1, pf.getNome());
        ps.setString(2, pf.getLogradouro());
        ps.setString(3, pf.getCidade());
        ps.setString(4, pf.getEstado());
        ps.setString(5, pf.getTelefone());
        ps.setString(6, pf.getEmail());
        ps.setInt(7, pf.getId());
        ps.setString(8, pf.getCpf());
        ps.setInt(9, pf.getId());
        ps.executeUpdate();

        bd.Close();

    }

    public void excluir(PessoaFisica pf) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM PessoaFisica WHERE Pessoa_idPessoa = ?;\n"
                + "DELETE FROM Pessoa WHERE idPessoa = ?";
        PreparedStatement ps = bd.getPrepared(delete);
        ps.setInt(1, pf.getId());
        ps.setInt(2, pf.getId());
        ps.executeUpdate();

        bd.Close();
    }

}
