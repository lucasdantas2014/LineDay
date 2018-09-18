
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.datatransfer.SystemFlavorMap;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Usuario {

	private String Nome;
	private String Curso;
	private String[] tags = new String[50];
	private Atividade[] atividades = new Atividade[1000];
	private Atividade[] atividadesArquivadas = new Atividade[1000];
	private Disciplina[] disciplinas = new Disciplina[100];
	
	//Ordena as atividades
	public void ordenarAtividades() {
	for (int i = 0; i < atividades.length - 1; ++i) {
	    Atividade tmp = atividades[i];
	    for (int j = i + 1; j < atividades.length; ++j) {
		Atividade tmp2 = atividades[j];
		if (tmp.getDeadline().compareTo(tmp2.getDeadline()) > 0) {
		    Atividade temp = tmp;
		    atividades[i] = tmp2;
		    atividades[j] =temp;
		}
	    }
	}
	}

	// Cadastrar Atividade
	public void CadastrarAtiv(Atividade atividade) {
		Date data = atividade.getDeadline();
		for (int n = 0; n < this.atividades.length; n++) {
			
			Date proximaData = this.atividades[n + 1].getDeadline();
			if(data.before(proximaData)){
				Atividade 
			}
			if (this.atividades[n] == null) {
				this.atividades[n] = atividade;
				break;
			}
		}

	}

	// Cadastrar Discilpina
	public void CadastrarDisc(Disciplina disc) {
		int n;
		for (n = 0; n < this.disciplinas.length; n++) {
			if (this.disciplinas[n] == null) {
				this.disciplinas[n] = disc;
				break;
			}
		}

	}

	// Excluir Atividade
	public void ExcluirAtiv() {

	}

	// Busca disicplinas pelo acronimo
	public Disciplina buscarDiscPorAcronimo(String acronimo) {

		for (int i = 0; this.disciplinas[i] != null; i++) {
			if (acronimo.equals(this.disciplinas[i].getAcronimo())) {

				return this.disciplinas[i];

			}
		}
		// Caso nao encontre
		System.out.println("NAO FOI ENCOPNTRADO");
		return null;
	}

	// Exibir atividade e indice percorrendo a lista
	public void exibirAtividades() {
		for (int n = 0; this.atividades[n] != null; n++) {
			System.out.println(n + " - " + this.atividades[n]);
		}
	}
	
	public void exibirAtividades() {
		for (int n = 0; this.atividades[n] != null; n++) {
			System.out.println(n + " - " + this.atividades[n]);
		}
	}

	// Exibir atividade arquivadas e indice percorrendo a lista
	public void exibirAtividadesArquivadas() {
		System.out.println("OIIIIII");
		for (int n = 0; this.atividadesArquivadas[n] != null; n++) {
			System.out.println("EDDEDEDED");

			System.out.println(n + " - " + this.atividadesArquivadas[n]);
		}
	}
	

	public void exibirDatasAtividades() {
		
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //você pode usar outras máscaras 
		for (int n = 0; this.atividades[n] != null; n++) {
			
			System.out.println(sdf1.format(this.atividades[n].getDeadline()));
		}
			
	}
	
	// Exibir dicsiplina e indice percorrendo a lista
	public void exibirDisciplinas() {
		for (int n = 0; this.disciplinas[n] != null; n++) {
			System.out.println(n + " - " + this.disciplinas[n]);
		}
	}

	// Exibir dicsiplina e indice percorrendo a lista
	public void exibirTags() {
		for (int n = 0; this.tags[n] != null; n++) {
			System.out.println(n + " - " + this.tags[n]);
		}
	}

	// Excluir Atividade POr indice
	public void ExcluirAtividadePorIndice(int indice) {
		this.atividades[indice] = null;
		Atividade aux;
		for (int n = indice; n < this.atividades.length - 1; n++) {
			aux = this.atividades[n];
			this.atividades[n] = this.atividades[n + 1];
			this.atividades[n + 1] = aux;
		}
	}

	// Arquivar Por Indice
	public void arquivarPorIndice(int indice) {
		System.out.println(indice);
		for (int n = 0; n < this.atividadesArquivadas.length; n++) {
			System.out.println("Entrou no loop");
			System.out.println(this.atividadesArquivadas[n]);
			if (this.atividadesArquivadas[n] == null) {
				this.atividadesArquivadas[n] = this.atividades[indice];
				System.out.println(this.atividades[indice]);
				System.out.println(this.atividadesArquivadas[n]);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Usuario [Nome=" + Nome + ", Curso=" + Curso + ", tags=" + Arrays.toString(tags) + ", atividades="
				+ Arrays.toString(atividades) + ", disciplinas=" + Arrays.toString(disciplinas) + "]";
	}

	public void EditarPerfil() {

	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Atividade[] getAtividades() {
		return atividades;
	}

	public void setAtividades(Atividade[] atividades) {
		this.atividades = atividades;
	}

	public void setDisciplinas(Disciplina[] disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void adicionarTag(String novaTag) {

		for (int n = 0; n < this.tags.length; n++) {
			if (this.tags[n] == null) {
				this.tags[n] = novaTag;
				break;
			}
		}
	}

	

}
