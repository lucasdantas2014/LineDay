import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;


public class Main {
	public static void menu(){
		
		System.out.println("------Menu------");
		System.out.println("1 - Editar Usuario ");
		System.out.println("2 - Cadastrar Atividades:");
		System.out.println("3 - Cadastrar Discplina");
		System.out.println("4 - Exibir Atividades");
		System.out.println("5 - Buscar Disciplina por Acronimo");

	}
	
	public static void main(String[] args) {
		Scanner inputStr = new Scanner(System.in); // Scaner String
		Scanner input = new Scanner(System.in); // Scaner Int e outros

		Usuario user = new Usuario(); // Usuario
		int opcao; // 
		menu();
		opcao = input.nextInt();
		
		while (opcao != 0){
			
			
//			Editar opcoes do usuario
			if(opcao == 1){
				
				//Leitura do novo nome e curso
				String nome = inputStr.nextLine();
				String curso = inputStr.nextLine();
				
				//Set Nome e Curso
				user.setNome(nome);
				user.setCurso(curso);
				System.out.println(user.getNome());
				
			}
			else if(opcao == 2){
				String nomeAtv = inputStr.nextLine();
				String deadline = inputStr.nextLine();
				
				String acronimo = inputStr.nextLine();
				Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
				
				Atividade atv = new Atividade(nomeAtv,deadline,tags,disc);
			}
			
			else if(opcao == 3){
				
				String acronimo = inputStr.nextLine();
				String nomeProf = inputStr.nextLine();
				String nomeDisc = inputStr.nextLine();
				
				Disciplina disciplina = new Disciplina(acronimo,nomeProf,nomeDisc);
				user.CadastrarDisc(disciplina);
				
			}
			
			else if(opcao == 4){
				user.exibirDisciplinas();
			}
			
			else if(opcao == 5){
				
				String acronimo = inputStr.nextLine();
				Disciplina disc;
				disc = user.buscarDiscPorAcronimo(acronimo);
				System.out.println(disc);
				
			}
			menu();
			opcao = input.nextInt();

		}
		
		

	}
	
	

}
