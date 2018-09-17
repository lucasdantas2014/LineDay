import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;
import java.text.*;
 

public class Fachada {
	
	//Iniciando Scaners
	Scanner input = new Scanner(System.in); // INput Numerico
	Scanner inputStr = new Scanner(System.in); // input de String
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // Formatando data
    
    


	
	//Editando dados do usuario
	public void editarUsuario(Usuario user) {
		boolean continuarEdicao = true;
		while(continuarEdicao){
			System.out.println(user);
			System.out.println("0 - terminar ediÃ§Ã£o");
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
		//Dados da Atividae(Nome e deadline)
		System.out.println("Digite o nome da Atividae:");
		String nomeAtv = inputStr.nextLine();
		System.out.println("Digite a data final para entrega:");
		String deadlineStr = inputStr.nextLine();
		
		Date deadline = null;
		

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			deadline = formato.parse(deadlineStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Discplina associada
		System.out.println("Digite o acronimo da discilpna desejada:");
		String acronimo = inputStr.nextLine();
		
		//buscando disciplina por acronimo e adicionando a variavel que vai conter a disciplina da atividade
		Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
		
		//Buscando tags
		boolean maisTags = true;
		//Lista das tags de uma atividade
		String[] tags = new String[50];
		
		while(maisTags){
			
			//Menu da parte de associar atividade a tags
			System.out.println("0-Salvar Atividade");
			System.out.println("1-Escolher entre as tags predefinidas");
			System.out.println("2-Definir uma nova tag");
			
			int opTag = input.nextInt();
			
			//Para o loop e finaliza o cadastro de atividade
			if(opTag == 0){
				maisTags = false;
				break;
				
			}else if(opTag == 1){
				System.out.println("Digite o nÃºmero referente a tag que deseja acrescentar a essa atividade:");
				System.out.println("-1 Para voltar");
				
				//Exibir Tags Com indice
				user.exibirTags();
				
				//Opcao de voltar
				int indexTag = input.nextInt();
				if( indexTag == -1){
					continue;
				}
				//Adicionando tag escolhida no final da lista de tags
				appendStr(tags, user.getTags()[indexTag]);
				
				
				
			}else if(opTag == 2){
				//lendo a nova tag
				System.out.println("Digite a nova tag:");
				String novaTag = inputStr.nextLine();
				
				//Adicionando tags
				user.adicionarTag(novaTag);

				//Adicionando tag escolhida no final da lista de tags
				appendStr(tags, novaTag);
				
				}
			}
			
		//Criando Objeto Atividade
		Atividade atv = new Atividade(nomeAtv,deadline,tags,disc);
		//Adicionando ativiade a usuario
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
	
	//Exibir Disciplinas
	public void exibirDisciplinas(Usuario user) {
		user.exibirAtividades();
	}

	//Exibir Atividades
	public void exibirAtividades(Usuario user) {
		user.exibirAtividades();
		
	}

	//Busca de uma disciplina por acronimo
	public void buscarPorAcronimo(Usuario user) {
		String acronimo = inputStr.nextLine();
		Disciplina disc;
		disc = user.buscarDiscPorAcronimo(acronimo);
		System.out.println(disc);
		
	}
	
	//Adicionar uma String a uma lista de String
	public void appendStr(String[] lista,String elem){
		for(int n = 0; n < lista.length; n++){
			if(lista[n] == null){
				lista[n] = elem;
				break;
			} 
		}
	}
	//Adicinar tag a usuario
	public void adicionarTag(Usuario user){
		System.out.println("Digite a nova tag:");
		String novaTag = inputStr.nextLine();
		user.adicionarTag(novaTag);
		user.adicionarTag(novaTag);
	}

	public void excluirAtividadePorIndice(Usuario user) {
		user.exibirAtividades();
		System.out.println("Digite o indice da atividade que deseja excluir:");
		int indice = input.nextInt();
		System.out.println("1 - Desejo arquivar");
		System.out.println("2 - Só excluir");
		boolean arquivar;
		int op = input.nextInt();
		if(op == 1){
			System.out.println("Arquivando");
			user.arquivarPorIndice(indice);
		}
		
		user.ExcluirAtividadePorIndice(indice);
		
	}

	public void exibirAtividadesArquivadas(Usuario user) {
		user.exibirAtividadesArquivadas();
		
	}

	public void exibirDatasAtividades(Usuario user) {
		user.exibirDatasAtividades();
		
	}

}
