import java.util.Scanner;

public class Usuario {
	private String Nome;
	private String Curso;
	private String[] tags = new String[50];
	
	public void CadastrarAtiv(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome da atividade: ");
		String Nome = sc.next();
		
		System.out.println(Nome);
		
		System.out.println("Digite alguma das tags pré-programadas: Projeto; Prova; Monitoria; Miniteste; Seminário: ");
		String Tags = sc.next();
		
		System.out.println(Tags);
	}
	public void CadastrarDisc(){
		
	}
	public void ExcluirAtiv(){
		
	}
	public void EditarPerfil(){
		
	}
}
