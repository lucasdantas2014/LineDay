//package database;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import model.Usuario;
//
//import java.sql.PreparedStatement;
//
//
//public class UsuarioDAO
//  extends DAO
//{
//  public UsuarioDAO() {}
//  
//  public ResultSet getDados() throws SQLException
//  {
//    String consulta = "select * from usuario where id = 1";
//    con = DriverManager.getConnection(url, userBd, senha);
//    Statement stmt = con.createStatement();
//    ResultSet resultado = stmt.executeQuery(consulta);
//    return resultado;
//  }
//  
//  public void insert(Usuario user) throws SQLException{
//	  String consulta = "insert into usuario(nome,curso) value(" + user.getNome() + ", " + user.getCurso() + ");";
//	  stmt.executeQuery(consulta);
//	  
//	  System.out.println("FOI");
//  }
//  
//  public void update(Usuario user) {
//	  
//	  String SQL = "UPDATE usuario "
//              + "SET nome = ?, curso = ? "
//              + "WHERE id = 1";
//
//      int affectedrows = 0;
//
//      try (Connection con = connect();
//              PreparedStatement pstmt = con.prepareStatement(SQL)) {
//
//          pstmt.setString(1, user.getNome());
//          pstmt.setString(2, user.getCurso());
//
//          affectedrows = pstmt.executeUpdate();
//
//      } catch (SQLException ex) {
//          System.out.println(ex.getMessage());
//      }
//  }
//
//  
//}