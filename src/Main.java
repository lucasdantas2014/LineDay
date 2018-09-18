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
		System.out.println("7 - Adicionar tag");
		System.out.println("8 - Excluir atvididade");
		System.out.println("9 - Exibir Arquivados");
		System.out.println("10 - Exibir Datas");
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
			//Exibir Disciplina
			else if(opcao == 4){
				fachada.exibirDisciplinas(user);
			}
			
			//Exibir Atividade			
			else if(opcao == 5){
				fachada.exibirAtividades(user);
			}
			
			//Buscar Por Acronimo
			else if(opcao == 6){
				fachada.buscarPorAcronimo(user);
			}
			
			//Adicionar Tag
			else if(opcao == 7){
				fachada.adicionarTag(user);
			}
			
			//Excluir Atividade Por indice
			else if(opcao == 8){
				fachada.excluirAtividadePorIndice(user);
				
			//Exibir atividades Arquivadas
			}
			else if(opcao == 9){
				fachada.exibirAtividadesArquivadas(user);
				
			}
			else if(opcao == 10){
				fachada.exibirDatasAtividades(user);
				
			}
			menu();
			opcao = input.nextInt();

		}
		
		

	}
	
	

}
