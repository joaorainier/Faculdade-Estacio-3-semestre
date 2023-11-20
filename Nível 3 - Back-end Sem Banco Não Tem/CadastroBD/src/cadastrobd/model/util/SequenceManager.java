/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Joao Rainier
 */
public class SequenceManager {

    public int getValue(ConectorBD bd) throws ClassNotFoundException, SQLException {
        ResultSet result = bd.getSelect("SELECT NEXT VALUE FOR idPessoaSequence");
        if (result.next()) {
            return result.getInt(1);
        }
        result.close();
        return 0;
    }
}
