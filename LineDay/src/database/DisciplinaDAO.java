package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Disciplina;

public class DisciplinaDAO extends DAO{

	public void buscarDiscPorAcronimo(String ed){//Retornar Disciplina
		String sql = "select * "
					+"from usuario"
					+"where acronimo = ?";
		
		try (Connection con = connect();
	              PreparedStatement pstmt = con.prepareStatement(sql)) {

	          pstmt.setString(1, ed);
	          
	          ResultSet result = pstmt.executeQuery();
	          
	          System.out.println(result);

	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
		
	}
}
