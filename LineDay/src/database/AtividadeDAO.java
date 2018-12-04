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
	
	public int criarAtividade(Atividade a) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		int id = 0;
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
			
			
			TagDAO tagDAO = new TagDAO();
			ResultSet resultado = conexao.executarSQL("SELECT id FROM atividade ORDER BY ID DESC LIMIT 1; ");
			if(a.getTags()[0] == null){
				resultado.next();
				id = resultado.getInt("id");
				pst2.setInt(1, resultado.getInt(1) );
				pst2.setNull(2, 1);
				pst2.execute();
			}
			else{
				for(int index = 0; index < a.getTags().length; index++){
					if(a.getTags()[index] == null){
						break;
					}
					resultado.next();
					pst2.setInt(1, resultado.getInt(1));
					System.out.println(resultado.getInt(1));
					resultado.close();
					
					pst2.setInt(2, tagDAO.buscarTagPorNome(a.getTags()[index]));
					pst2.execute();
				}
			}
			
			// solicita��o da execu��o da query, ap�s seu preparo
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println(e);
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return id;
	}
	
	
	
	
	// busca de pessoas por seu c�digo de identifica��o no banco (id)
	public boolean VerificaSeExiste(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		// busca utilizando o m�todo de consulta do objeto ConexaoBD
		ResultSet resultado = conexao.executarSQL("select * from atividade where id = \'" + id + "\' and arquivado = false");
		boolean bool = false;
		try {
			if(resultado.next()){
				System.out.println(resultado.getInt("id"));
				bool = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return bool;
	}
	
	public void ArquivarrAtividade(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("update atividade set arquivado = true where id = \'" + id + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
	}
	
	public void excluirAtividade(int id) {
		// abrindo a conex�o com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm1 = conexao.getConexao().prepareStatement("delete from atv_tag where id = \'" + id + "\'");
			stm1.execute();
			PreparedStatement stm2 = conexao.getConexao().prepareStatement("delete from atividade where id = \'" + id + "\'");
			stm2.execute();
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
	
	
	
	public String[] BuscarTagsAtividade(int id){
		String[] tags = new String[50];
		TagDAO tagDAO = new TagDAO();
		int contador = 0;
		conexao.conectar();
		
		try{
		PreparedStatement pst1 = conexao.getConexao().prepareStatement("Select * from atv_tag where atividade = \'" + id + "\'");
//		pst1.setInt(1, id);
		
		ResultSet resultado = pst1.executeQuery();
		
		while(resultado.next()){
			System.out.println(resultado.getInt("tag"));
			tags[contador] = tagDAO.buscarTag(resultado.getInt("tag"));
			
			contador++;
		}
		}catch(Exception e){
			System.out.println(e + "FOI EXAAMENTE AQUI");
		}finally {
			conexao.desconectar();
		}
		
		return tags;
	}
	
	public Atividade[] verTodosArquivados() {
		Atividade[] atividades = new Atividade[1000];
		DisciplinaDAO discDAO = new DisciplinaDAO();
		int cont = 0;
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from atividade where arquivado = true");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				Date deadline = resultado.getDate("deadline");
				Disciplina disc = discDAO.buscarDisciplina(resultado.getInt("disciplina"));
				String[] tags = BuscarTagsAtividade(resultado.getInt("id"));
				int id = resultado.getInt("id");
				atividades[cont] = new Atividade(nome, deadline, tags , disc, " ", id);
				cont++;
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return atividades;
	}
	
	public Atividade[] verTodosNaoArquivados() {
		Atividade[] atividades = new Atividade[1000];
		DisciplinaDAO discDAO = new DisciplinaDAO();
		int cont = 0;
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from atividade where arquivado = false");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				Date deadline = resultado.getDate("deadline");
				Disciplina disc = discDAO.buscarDisciplina(resultado.getInt("disciplina"));
				String[] tags = BuscarTagsAtividade(resultado.getInt("id"));
				int id = resultado.getInt("id");
				atividades[cont] = new Atividade(nome, deadline, tags , disc, " ", id);
				cont++;
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return atividades;
	}
	
	public Atividade[] verTodos() {
		Atividade[] atividades = new Atividade[1000];
		DisciplinaDAO discDAO = new DisciplinaDAO();
		int cont = 0;
		// abrindo a conex�o com o BD
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from atividade");
		
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o m�todo next()
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				Date deadline = resultado.getDate("deadline");
				Disciplina disc = discDAO.buscarDisciplina(resultado.getInt("disciplina"));
				String[] tags = BuscarTagsAtividade(resultado.getInt("id"));
				int id = resultado.getInt("id");
				atividades[cont] = new Atividade(nome, deadline, tags , disc, " ", id);
				cont++;
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exce��o � lan�ada
			conexao.desconectar();
		}
		return atividades;
	}

}