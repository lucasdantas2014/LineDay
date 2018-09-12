import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
	private String Nome;
	private String Curso;
	private String[] tags = new String[50];
	private Atividade[] atividades = new Atividade[1000];
	private Disciplina[] disciplinas = new Disciplina[100];
	
	
	public void CadastrarAtiv(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome da atividade: ");
		String Nome = sc.next();
		
		System.out.println(Nome);
		
		System.out.println("Digite alguma das tags prÃ©-programadas: Projeto; Prova; Monitoria; Miniteste; SeminÃ¡rio: ");
		String Tags = sc.next();
		
		System.out.println(Tags);
	}
	public void CadastrarDisc(Disciplina disc){
		int n;
		for(n = 0; n < this.disciplinas.length; n++){
			if(this.disciplinas[n] == null){
				this.disciplinas[n] = disc;
				break;
			}
		}
		
	}
	public void ExcluirAtiv(){
		
	}
	
	public void exibirDisciplinas(){
		
		for(int i=0;this.disciplinas[i] != null; i++){
		    System.out.println(this.disciplinas[i]);
		}
	}
	
	public Disciplina buscarDiscPorAcronimo(String acronimo){
		
		for(int i=0; this.disciplinas[i] != null; i++){
		    if(acronimo.equals(this.disciplinas[i].getAcronimo())){
		    	
		    	System.out.println("CHEGOU");
		    	return this.disciplinas[i]; 
		    	
		    }
		    else{
		    	System.out.println(this.disciplinas[i].getAcronimo());
		    	System.out.println(acronimo);
		    	System.out.println(this.disciplinas[i].getAcronimo() == acronimo);
		    	System.out.println("ED" == "ED");
		    }
		    
		}
		System.out.println("NAO FOI ENCOPNTRADO");
		return null;
		
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
	
}
