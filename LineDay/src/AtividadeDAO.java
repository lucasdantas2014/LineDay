package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Atividade;
import model.Disciplina;

import model.Disciplina;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

import java.sql.ResultSet;

public class AtividadeDAO {
	
	private ConexaoBD conexao;
	
	public AtividadeDAO() {
		// cria o objeto para conex�o com banco, por�m n�o o inicializa
		// a conex�o deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
	public void criarAtividade(Atividade a) {
		// abrindo a conex�o com o BD
		conexao.conectar();

		try {
			// usando um PreparedStatement com valores externos como par�metros (representados pelo '?')
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into atividade(nome, deadline, descricao, disciplina) values(?,?,?,?)");
			PreparedStatement pst2 = conexao.getConexao().prepareStatement("insert into atv_tag(atividade,tag) values(?,?)");
			// os m�todos set devem ser escolhidos de acordo com os tipos dos atributos da entidade que est�
			// sendo acessada. A sequ�ncia � determinada por �ndices, iniciando do valor 1.
			pst.setString(1, a.getNome());
			pst.setDate(2, new java.sql.Date(a.getDeadline().getTime()));
			pst.setString(3, a.getDescricao());
			DisciplinaDAO discDAO = new DisciplinaDAO();
			int idDisc = discDAO.buscarDisciplinaPorAcronimo(a.getDisciplina().getAcronimo());
			pst.setInt(4, idDisc);
			
			pst.execute();
			
			ResultSet resultado = conexao.executarSQL("SELECT ID FROM tabela ORDER BY ID DESC LIMIT 1; ");
			
			if(a.getTags()[0] == null){
				pst2.setInt(1, resultado.getInt(1) );
				pst2.setInt(2, -1);
				pst2.execute();
			}
			else{
				for(int index = 0; index < a.getTags().length; index++){
					if(a.getTags()[index] == null){
						break;
					}
					pst2.setInt(1, resultado.getInt(1));
					TagDAO tagDAO = new TagDAO();
					
					
					pst2.setInt(2, tagDAO.buscarTagPorNome(a.getTags()[index]));
					pst2.execute();
				}
			}
			
			// solicita��o da execu��o da query, ap�s seu preparo
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		
	}
	
	// busca de pessoas por seu c�digo de identifica��o no banco (id)
	public Disciplina buscarDisciplina(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		// busca utilizando o m�todo de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select * from disciplina where id = \'" + id + "\'");
		Disciplina d = new Disciplina("", "", "");
		
		try {
			resultado.next();
			// os m�todos get devem ser escolhidos de acordo com os tipos dos atributos da entidade que est�
			// sendo acessada
			String nome = resultado.getString("nome");
			String professor = resultado.getString("professor");
			String acronimo = resultado.getString("acronimo");
			d.setNomeDisc(nome);
			d.setNomeProf(professor);
			d.setAcronimo(acronimo);
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return d;
	}
	
	public void excluirPessoa(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("delete from disciplina where id = \'" + id + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
	}

	public void editarPessoa(int id, String nome, String professor, String acronimo) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("update disciplina set nome = ?, professor = ?, acronimo = ?"
					+ "where id = \'" + id + "\'");
			stm.setString(1, nome);
			stm.setString(2, professor);
			stm.setString(3, acronimo);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
	}
	
	public ArrayList<Disciplina> verTodos() {
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from usuario");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				String professor = resultado.getString("professor");
				String acronimo = resultado.getString("acronimo");
				disciplinas.add(new Disciplina(nome, professor, acronimo));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return disciplinas;
	}

}