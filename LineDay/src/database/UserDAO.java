package database;

import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

import java.sql.ResultSet;

public class UserDAO {
	
	private ConexaoBD conexao;
	
	public Usuario inicializar(){
		
		DisciplinaDAO d = new DisciplinaDAO();
		Usuario user = new Usuario();
		user = buscarPessoa(1);
		user.setDisciplinas(d.buscarTodasDisciplinas());
		user.setAtividades(buscarTodasAtividade);
		
		
	}
	
	public UserDAO() {
		// cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
	public void criarPessoa(Usuario u) {
		// abrindo a conexão com o BD
		conexao.conectar();

		try {
			// usando um PreparedStatement com valores externos como parâmetros (representados pelo '?')
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into usuario(nome,curso) values(?,?)");
			// os métodos set devem ser escolhidos de acordo com os tipos dos atributos da entidade que está
			// sendo acessada. A sequência é determinada por índices, iniciando do valor 1.
			pst.setString(1, u.getNome());
			pst.setString(2, u.getCurso());
			// solicitação da execução da query, após seu preparo
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		
	}
	
	// busca de pessoas por seu código de identificação no banco (id)
	public Usuario buscarPessoa(int id) {
		// abrindo a conexão com o BD
		conexao.conectar();
		// busca utilizando o método de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select * from usuario where id = \'" + id + "\'");
		Usuario u = new Usuario();
		
		try {
			resultado.next();
			// os métodos get devem ser escolhidos de acordo com os tipos dos atributos da entidade que está
			// sendo acessada
			String nomePessoa = resultado.getString("nome");
			String curso = resultado.getString("curso");
			u.setNome(nomePessoa);
			u.setCurso(curso);
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return u;
	}
	
	public void excluirPessoa(int id) {
		// abrindo a conexão com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("delete from usuario where id = \'" + id + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
	}

	public void editarPessoa(String nome, String curso) {
		// abrindo a conexão com o BD
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
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
	}
	
	public ArrayList<Usuario> verTodos() {
		ArrayList<Usuario> pessoas = new ArrayList<>();
		
		// abrindo a conexão com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from usuario");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
				String nomePessoa = resultado.getString("nome");
				String curso = resultado.getString("curso");
				pessoas.add(new Usuario(nomePessoa, curso));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return pessoas;
	}

}