import java.util.Scanner;

public class Fachada {
	
	//Iniciando Scaners
	Scanner input = new Scanner(System.in); // INput Numerico
	Scanner inputStr = new Scanner(System.in); // input de String
	
	//Editando dados do usuario
	public void editarUsuario(Usuario user) {
		boolean continuarEdicao = true;
		while(continuarEdicao){
			System.out.println(user);
			System.out.println("0 - terminar edição");
			System.out.println("1 - mudar nome");
			System.out.println("2 - mudar curso");
			
			int op = input.nextInt();
			if(op == 0){
				break;
				
			}else if(op == 1){
				System.out.println("Digite o novo nome do usuario:");
				String nome = inputStr.nextLine();
				user.setNome(nome);
			
			}else if(op == 2){
				System.out.println("Digite o novo curso do usuario:");
				String curso = inputStr.nextLine();
				user.setCurso(curso);
			}
		}
		
	}
	
	public void cadastrarAtividade(Usuario user) {
		//Dados da Atividae
		System.out.println("Digite o nome da Atividae:");
		String nomeAtv = inputStr.nextLine();
		System.out.println("Digite a data final para entrega:");
		String deadline = inputStr.nextLine();
		
		//Discplina associada
		System.out.println("Digite o acronimo da discilpna desejada:");
		String acronimo = inputStr.nextLine();
		Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
		
		//Buscando tags
		boolean maisTags = true;
		String[] tags = new String[50];
		while(maisTags){
			System.out.println("0-Salvar Atividade");
			System.out.println("1-Escolher entre as tags predefinidas");
			System.out.println("2-Definir uma nova tag");
			
			int opTag = input.nextInt();
			
			if(opTag == 0){
				maisTags = false;
				break;
				
			}else if(opTag == 1){
				System.out.println("Digite o número referente a tag que deseja acrescentar a essa atividade:");
				System.out.println("-1 Para voltar");
				for(int n = 0; user.getTags()[n] != null; n++){
					System.out.println(n + " - " + user.getTags()[n]);
				}
				int indexTag = input.nextInt();
				if( indexTag == -1){
					continue;
				}
				appendStr(tags, user.getTags()[indexTag]);
				
				
				
			}else if(opTag == 2){
				System.out.println("Digite a nova tag:");
				String novaTag = inputStr.nextLine();
				user.adicionarTag(novaTag);
				//Buscando espaço vazio para adicionar a tag
				appendStr(tags, novaTag);
				
				}
			}
			
			
		}
		Atividade atv = new Atividade(nomeAtv,deadline,tags,disc);
		user.CadastrarAtiv(atv);
	}
	
	//Cadastro de disciplina
	public void cadastrarDisicplina(Usuario user) {
		String acronimo = inputStr.nextLine();
		String nomeProf = inputStr.nextLine();
		String nomeDisc = inputStr.nextLine();
		
		Disciplina disciplina = new Disciplina(acronimo,nomeProf,nomeDisc);
		user.CadastrarDisc(disciplina);
	}
	
	public void exibirDisciplinas(Usuario user) {
		user.exibirAtividades();
	}

	public void exibirAtividades(Usuario user) {
		user.exibirAtividades();
		
	}

	public void buscarPorAcronimo(Usuario user) {
		String acronimo = inputStr.nextLine();
		Disciplina disc;
		disc = user.buscarDiscPorAcronimo(acronimo);
		System.out.println(disc);
		
	}
	
	public void appendStr(String[] lista,String elem){
		for(int n = 0; n < lista.length; n++){
			if(lista[n] == null){
				lista[n] = elem;
				break;
			} 
	}
	

}
