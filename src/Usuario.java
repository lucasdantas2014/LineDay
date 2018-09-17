import java.awt.datatransfer.SystemFlavorMap;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;


public class Main {
	public static void menu(){
		
		System.out.println("------Menu------");
		System.out.println("1 - Editar Usuario ");
		System.out.println("2 - Cadastrar Atividades:");
		System.out.println("3 - Cadastrar Discplina");
		System.out.println("4 - Exibir Disciplina");
		System.out.println("5 - Exibir Atividades");
		System.out.println("6 - Buscar Disciplina por Acronimo");

		

	}
	
	public static void main(String[] args) {
		Scanner inputStr = new Scanner(System.in); // Scaner String
		Scanner input = new Scanner(System.in); // Scaner Int e outros

		Fachada fachada = new Fachada();
		Usuario user = new Usuario(); // Usuario
		int opcao; // 
		menu();
		opcao = input.nextInt();
		
		while (opcao != 0){
			
			
//			Editar opcoes do usuario 
			if(opcao == 1){		
				
				fachada.editarUsuario(user);;
				
			}
			
			//Adicionando atividades 
			else if(opcao == 2){
				fachada.cadastrarAtividade(user);
			}
			
			//Cadastrando disciplina
			else if(opcao == 3){				
				fachada.cadastrarDisicplina(user);
				
			}
			
			else if(opcao == 4){
				fachada.exibirDisciplinas(user);
			}
			
			else if(opcao == 5){
				fachada.exibirAtividades(user);
			}
			
			else if(opcao == 6){
				fachada.buscarPorAcronimo(user);
				
			}
			menu();
			opcao = input.nextInt();

		}
		
		

	}
	
	

}
