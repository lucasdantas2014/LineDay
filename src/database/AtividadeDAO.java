package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import model.Atividade;

public class AtividadeDAO extends DAO{
	public void insert(Atividade atividade){
		String sql = "insert into atividade(nome,deadline,disciplina)"
					+"values(?,?,?);";
				
		try (Connection con = connect();
	              PreparedStatement pstmt = con.prepareStatement(sql)) {

	          pstmt.setString(1, atividade.getNome());
	          pstmt.setDate(2, (Date) atividade.getDeadline());
	          pstmt.setInt(3, atividade.getDisciplina());


	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
		
		
	}
}
