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
	public Usuario buscarTag(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		// busca utilizando o m�todo de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select * from usuario where id = \'" + id + "\'");
		Usuario u = new Usuario();
		
		try {
			resultado.next();
			// os m�todos get devem ser escolhidos de acordo com os tipos dos atributos da entidade que est�
			// sendo acessada
			String nomePessoa = resultado.getString("nome");
			String curso = resultado.getString("curso");
			u.setNome(nomePessoa);
			u.setCurso(curso);
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return u;
	}
	
	// busca de pessoas por seu c�digo de identifica��o no banco (id)
		public int buscarTagPorNome(String nome) {
			// abrindo a conex�o com o BD
			conexao.conectar();
			// busca utilizando o m�todo de consulta do objeto ConexaoBD
			ResultSet resultado = conexao.executarSQL("select id from tag where nome = \'" + nome + "\'");
			int id = -1;
			
			try {
				resultado.next();
				id = resultado.getInt(1);
				
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
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
	
	public ArrayList<Usuario> verTodos() {
		ArrayList<Usuario> pessoas = new ArrayList<>();
		
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from usuario");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				String nomePessoa = resultado.getString("nome");
				String curso = resultado.getString("curso");
				pessoas.add(new Usuario(nomePessoa, curso));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return pessoas;
	}

}