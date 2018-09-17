import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Usuario {
	private String Nome;
	private String Curso;
	private String[] tags = new String[50];
	private Atividade[] atividades = new Atividade[1000];
	private Disciplina[] disciplinas = new Disciplina[100];
	
	
	//Cadastrar Atividade
	public void CadastrarAtiv(Atividade atividade){
		
			for(int n = 0; n < this.disciplinas.length; n++){
				if(this.atividades[n] == null){
					this.atividades[n] = atividade;
					break;
				}
			}
			
		}
	
	//Cadastrar Discilpina
	public void CadastrarDisc(Disciplina disc){
		int n;
		for(n = 0; n < this.disciplinas.length; n++){
			if(this.disciplinas[n] == null){
				this.disciplinas[n] = disc;
				break;
			}
		}
		
	}
	
	//Excluir Atividade
	public void ExcluirAtiv(){
		
	}
	
	//Exibir todas as discilplinas percorrendo a lista
	public void exibirDisciplinas(){
		
		for(int i=0;this.disciplinas[i] != null; i++){
		    System.out.println(this.disciplinas[i]);
		}
	}
	
	//Busca disicplinas pelo acronimo
	public Disciplina buscarDiscPorAcronimo(String acronimo){
		
		for(int i=0; this.disciplinas[i] != null; i++){
		    if(acronimo.equals(this.disciplinas[i].getAcronimo())){
		    	
		    	return this.disciplinas[i]; 
		    	
		    }
		    	
		    
		}
		//Caso nao encontre
		System.out.println("NAO FOI ENCOPNTRADO");
		return null;
		
	}
	
	//Exibir atividade percorrendo a lista
	public void exibirAtividades(){
			
			for(int i=0;this.atividades[i] != null; i++){
			    System.out.println(this.atividades[i]);
			}
		}
	
	
	
	@Override
	public String toString() {
		return "Usuario [Nome=" + Nome + ", Curso=" + Curso + ", tags=" + Arrays.toString(tags) + ", atividades="
				+ Arrays.toString(atividades) + ", disciplinas=" + Arrays.toString(disciplinas) + "]";
	}
	public void EditarPerfil(){
		
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
		
		for(int n = 0; n < this.tags.length; n++){
			if(this.tags[n] == null){
				this.tags[n] = novaTag;
				break;
			}
		}
	}
	
}
