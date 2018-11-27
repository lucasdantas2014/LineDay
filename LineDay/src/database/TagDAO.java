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
		// cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
	public void criarPessoa(String t) {
		// abrindo a conexão com o BD
		conexao.conectar();

		try {
			// usando um PreparedStatement com valores externos como parâmetros (representados pelo '?')
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into tag(nome) values(?)");
			// os métodos set devem ser escolhidos de acordo com os tipos dos atributos da entidade que está
			// sendo acessada. A sequência é determinada por índices, iniciando do valor 1.
			pst.setString(1, t);
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
	public String buscarTag(int id) {
		// abrindo a conexão com o BD
		conexao.conectar();
		// busca utilizando o método de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select nome from tag where id = \'" + id + "\' ");
		String nome = "";
		
		try {
			resultado.next();
			// os métodos get devem ser escolhidos de acordo com os tipos dos atributos da entidade que está
			// sendo acessada
			nome = resultado.getString("nome");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return nome;
	}
	
	// busca de pessoas por seu código de identificação no banco (id)
		public int buscarTagPorNome(String nome) {
			// abrindo a conexão com o BD
			conexao.conectar();
			int id = -1;
			// busca utilizando o método de consulta do objeto ConexaoBD
			
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
				// o banco deve ser desconectado, mesmo quando a exceção é lançada
				conexao.desconectar();
			}
			return id;
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
	
	public String[] verTodos() {
		String[] tags = new String[50];
		int contador = 0;
		
		// abrindo a conexão com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from tag");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
				tags[contador] = resultado.getString("nome");
				contador++;
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return tags;
	}

}