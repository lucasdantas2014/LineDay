import java.util.Scanner;


public class Main {
	public static void menu(){
		
		System.out.println("------Menu------");
		System.out.println("1 - Editar Usuario ");
		System.out.println("2 - Cadastrar Atividades:");
		System.out.println("3 - Cadastrar Discplina");
	}
	
	public static void main(String[] args) {
		menu();
		Usuario user = new Usuario();
		int opcao;
		Scanner sc = new Scanner(System.in);
		opcao = sc.nextInt();
		if(opcao == 1){
			String nome = sc.nextLine();
			String curso = sc.nextLine();
			
		}
		System.out.println(user.get);
		
	}
	
	

}
