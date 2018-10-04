package database;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO
{
  String driver = "org.postgresql.Driver";
  String userBd = "postgres";
  String senha = "ifpb";
  String url = "jdbc:postgresql://localhost:5432/line_day";
  Connection con = null;
  Statement stmt = null;
  
  public Connection connect() throws SQLException {
      return DriverManager.getConnection(url, userBd, senha);
  }
  
  
  public DAO()
  {
    try {
      Class.forName(driver);
      con = java.sql.DriverManager.getConnection(url, userBd, senha);
      System.out.println("Conexão realizada com sucesso.");
      Statement localStatement = con.createStatement();
      
    }
    catch (ClassNotFoundException ex)
    {
      System.err.print(ex.getMessage());
    }
    catch (SQLException e)
    {
      System.out.println("Erro2");
      
      System.err.print(e.getMessage());
    }
  }
}
