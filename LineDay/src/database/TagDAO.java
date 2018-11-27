package database;

import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

import java.sql.ResultSet;

public class TagDAO {
	
	private ConexaoBD conexao;
	
	public TagDAO() {
		// cria o objeto para conex�o com banco, por�m n�o o inicializa
		// a conex�o deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
	public void criarPessoa(String t) {
		// abrindo a conex�o com o BD
		conexao.conectar();

		try {
			// usando um PreparedStatement com valores externos como par�metros (representados pelo '?')
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into tag(nome) values(?)");
			// os m�todos set devem ser escolhidos de acordo com os tipos dos atributos da entidade que est�
			// sendo acessada. A sequ�ncia � determinada por �ndices, iniciando do valor 1.
			pst.setString(1, t);
			// solicita��o da execu��o da query, ap�s seu preparo
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		
	}
	
	// busca de pessoas por seu c�digo de identifica��o no banco (id)
	public String buscarTag(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		// busca utilizando o m�todo de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select nome from tag where id = \'" + id + "\' ");
		String nome = "";
		
		try {
			resultado.next();
			// os m�todos get devem ser escolhidos de acordo com os tipos dos atributos da entidade que est�
			// sendo acessada
			nome = resultado.getString("nome");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return nome;
	}
	
	// busca de pessoas por seu c�digo de identifica��o no banco (id)
		public int buscarTagPorNome(String nome) {
			// abrindo a conex�o com o BD
			conexao.conectar();
			int id = -1;
			// busca utilizando o m�todo de consulta do objeto ConexaoBD
			
			try {
				PreparedStatement pst = conexao.getConexao().prepareStatement("select id from tag where nome = ?");
				pst.setString(1, nome);
				
				ResultSet result = pst.executeQuery();
				
				if(result.next()){
				id = result.getInt("id");
				}else{
					System.out.println("Nenhuma tag encontrada no banco");
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println(e);
			} finally {
				// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
				conexao.desconectar();
			}
			return id;
		}
	
	public void excluirPessoa(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("delete from usuario where id = \'" + id + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
	}

	public void editarPessoa(String nome, String curso) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("update usuario set nome = ?, curso = ? "
					+ "where id = \'" + 1 + "\'");
			stm.setString(1, nome);
			stm.setString(2, curso);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
	}
	
	public String[] verTodos() {
		String[] tags = new String[50];
		int contador = 0;
		
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from tag");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				tags[contador] = resultado.getString("nome");
				contador++;
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return tags;
	}

}