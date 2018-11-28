import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import database.AtividadeDAO;
import database.DisciplinaDAO;
import database.UserDAO;
// import database.UsuarioDAO;
import model.Atividade;
import model.Disciplina;
import model.Ferramentas;
import model.Usuario;





public class Fachada
{
  Scanner input = new Scanner(System.in);
  Scanner inputStr = new Scanner(System.in);
  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
  UserDAO userDAO = new UserDAO();
  DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
  AtividadeDAO atividadeDAO = new AtividadeDAO();
  

  public Fachada() {}
  

//  Editar Usuario(op ==1)
  public void editarUsuario(Usuario user) throws SQLException
  {
	  System.out.println("\n\n--------------------------");
		System.out.println("  Editar usuario");
		System.out.println("--------------------------\n\n"); 
	
    boolean continuarEdicao = true;
    while (continuarEdicao) {
      System.out.println("\n>>>>>>>>>>>>");//Quebra de linha
      System.out.println(user);
      System.out.println("<<<<<<<<<<<<");
      System.out.println("\n0 - terminar edicao");
      System.out.println("1 - mudar nome");
      System.out.println("2 - mudar curso");
      int op = -1;
      
      try{
    	  op = Ferramentas.leitorInteiro();
      }catch (Exception e) {
		System.out.println("Digite um numero inteiro; exemplo: 1 ou 2 ou 3...");
      }
      
      if (op == 0) {
        break;
      }
      if (op == 1) {
        System.out.println("\nDigite o novo nome do usuario:");
        String nome = inputStr.nextLine();
        user.setNome(nome);
      }
      else if (op == 2) {
        System.out.println("\nDigite o novo curso do usuario:");
        String curso = inputStr.nextLine();
        user.setCurso(curso);
      }
    }
    
    userDAO.editarPessoa(user.getNome(), user.getCurso());
  }
  

  public void cadastrarAtividade(Usuario user)
  {
	System.out.println("\n\n--------------------------");
	System.out.println("  Cadastro de Atividade");
  System.out.println("OBS:");
	System.out.println("Digite -2 para cancelar o cadastro e voltar ao menu");
  System.out.println("--------------------------\n\n");
    
    System.out.println("Digite o nome da Atividae:");
    String nomeAtv = inputStr.nextLine();
    //Opcao de cancelar
    System.out.println(nomeAtv );
    if(nomeAtv.equals("-2")){
      System.out.println("-------------");
      System.out.println("Cancelando Cadastro e voltando ao Menu Princiapl");
      System.out.println("-------------");
      return;
    }

    System.out.println("\n=====Deadline(Data FInal para entregar/realizar)=====\n");
    
    //leitura do tipo de data
    Date deadline = Ferramentas.leitorDate();
    
    //Opcao de cancelar
    if(deadline == null){
      System.out.println("-------------");
      System.out.println("Cancelando Cadastro e voltando ao Menu Princiapl");
      System.out.println("-------------");
      return;
    }
    
    

    //Se nao houver disciplina cadastrada
    if(user.getDisciplinas()[0] == null){
    	System.out.println("\n--------Voce nao tem nehuma disciplina Cadastrada.\n--------");
    	System.out.println("Iniciando Cadastramento de Disciplina...");

      cadastrarDisicplina(user);
    
    }
    
    Disciplina disc = null;
    while(disc == null){
      //Mostando disciplina disponiveis e seus acronimo
      Disciplina[] disciplinas = user.getDisciplinas();

      exibirDisciplinas(user);



	    System.out.println("\nDigite o acronimo da disciplina que deseja associar a essa atividade ou digite 1 para cadastrar outra disciplina:");

	    String acronimo = inputStr.nextLine();
      
      //Opcao de cancelar
      if(acronimo.equals("-2")){
        System.out.println("-------------");
        System.out.println("Cancelando Cadastro e voltando ao Menu Princiapl");
        System.out.println("-------------");
        return;
      }

      else if(acronimo.equals("1")){
        cadastrarDisicplina(user);
        continue;
      }
	
	    disc = user.buscarDiscPorAcronimo(acronimo);

    }
    boolean maisTags = true;
    
    String[] tags = new String[50];
    
    while (maisTags)
    {
      System.out.println("\nTags que ja foram adicionadas a esta atividade:");
      for(int index = 0; index < tags.length; index++ ){
    	  if(tags[index] == null){
    		  break;
    	  }
    	  System.out.println( " - " + tags[index]);
      }
      System.out.println("0-Salvar Atividade");
      System.out.println("1-Escolher entre as tags predefinidas");
      System.out.println("2-Definir uma nova tag e associa-la a atividade");
      
      int opTag = Ferramentas.leitorInteiro();
      
      //Opcao de cancelar
      if(opTag == -2){
        System.out.println("-------------");
        System.out.println("Cancelando Cadastro e voltando ao Menu Princiapl");
        System.out.println("-------------");
        return;
      }
    

      if (opTag == 0) {
        maisTags = false;
        break;
      }
      if (opTag == 1) {
        System.out.println("Digite o numero referente a tag que deseja acrescentar a essa atividade:");
        System.out.println("-1 Para voltar");
        

        user.exibirTags();
        

        int indexTag = Ferramentas.leitorInteiro();
        if (indexTag != -1)
        {


          appendStr(tags, user.getTags()[indexTag]);
        }
        
      }
      else if (opTag == 2)
      {
        System.out.println("Digite a nova tag:");
        String novaTag = inputStr.nextLine();
        

        user.adicionarTag(novaTag);
        

        appendStr(tags, novaTag);
      }
    }
    
    System.out.println("Digite uma descrição da atividade:");
    String descricao = inputStr.nextLine();

    Atividade atv = new Atividade(nomeAtv, deadline, tags, disc, "", );
    
    
    int id = atividadeDAO.criarAtividade(atv);
    user.CadastrarAtiv(atv);
    System.out.println("==================");

  }
  
// Cadastrar Dsciplina
  public void cadastrarDisicplina(Usuario user)
  {
	System.out.println("\n\n--------------------------");
	System.out.println("  Cadastro de Disciplina");
	System.out.println("--------------------------\n\n");
	
	
	//Lendo Dados da disciplina(acronimo, professor, nome)
    System.out.println("Digite o acronimo da Disciplina: ");
    String acronimo = inputStr.nextLine();
    System.out.println("Digite o nome do professor: ");
    String nomeProf = inputStr.nextLine();
    System.out.println("Digite o nome da Disicplina: ");
    String nomeDisc = inputStr.nextLine();
    
    Disciplina disciplina = new Disciplina(acronimo, nomeProf, nomeDisc);
    user.CadastrarDisc(disciplina);

    System.out.println(disciplina);

    disciplinaDAO.criarDisciplina(disciplina);
    System.out.println("\n\n--------------------------");
    System.out.println("Fim do Cadastro.\nCadastro de Disciplina Completo");
    System.out.println("--------------------------\n\n");
    
  }
  
//  Percorre a lisa e exibe as disciplinas
  public void exibirDisciplinas(Usuario user)
  {
    System.out.println("\n\n--------------------------");
	System.out.println("  Exibir Disciplinas");
	System.out.println("--------------------------\n\n");
	
    user.exibirDisciplinas();
    System.out.println("==================");
    
  }
  
//Percorre a lisa e exibe as disciplinas
  public void exibirAtividades(Usuario user)
  {
	System.out.println("\n\n--------------------------");
	System.out.println("  Exibir Atividades");
	System.out.println("--------------------------\n\n");
	
    user.exibirAtividades();
    System.out.println("==================");

  }
  

//Busca por Acronimo por acronimo
  public void buscarPorAcronimo(Usuario user)
  {
	System.out.println("\n\n--------------------------");
	System.out.println("  Buscar Por Acronimo");
	System.out.println("--------------------------\n\n");
    String acronimo = inputStr.nextLine();
    
    Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
    System.out.println(disc);
    System.out.println("==================");

  }
  

  //Adiciona String no final de uma lista
  public void appendStr(String[] lista, String elem)
  {
    for (int n = 0; n < lista.length; n++) {
      if (lista[n] == null) {
        lista[n] = elem;
        break;
      }
    }
  }
  
  //Adicionar tags
  public void adicionarTag(Usuario user) {
	  
	System.out.println("\n\n--------------------------");
	System.out.println("  Adiconar Tag");
	System.out.println("--------------------------\n\n");
  
    System.out.println("Digite a nova tag:");
    String novaTag = inputStr.nextLine();
    user.adicionarTag(novaTag);
    System.out.println("==================");

  }
  
  
//  Exlcuir uma atividade pelo indice
  public void excluirAtividadePorIndice(Usuario user) {
    System.out.println("\n\n--------------------------");
	  System.out.println("  Excluir Atividade Por id");
	  System.out.println("--------------------------\n\n");
    while(true){
      user.exibirAtividades();
      System.out.println("Digite o indice da atividade que deseja excluir:");
      System.out.println("OBS:\n Digite -1 para camcelar e voltar ao menu.");
      int indice = Ferramentas.leitorInteiro();

      if(indice > user.getQtd_atividades()){
        System.out.println("Id invalido; Tente novamente");
        continue;
      }
    
      
      if(indice == -1){
        System.out.println("Cancelando...");
        System.out.println("Voltando ao menu");
        return;
      }

      System.out.println("0 - Cancelar");
      System.out.println("1 - Desejo arquivar");
      System.out.println("2 - So excluir");
      
      int op = Ferramentas.leitorInteiro();
        if (op == 0) {
          System.out.println("Cancelando...");
          return;
        }
        else if (op == 1) {
          System.out.println("Arquivando");
          user.arquivarPorIndice(indice);
        }
        
        else if(op == 2){
        user.ExcluirAtividadePorIndice(indice);
 	AtividadeDAO atvDAO = new AtividadeDAO();
	atvDAO.excluirAtividade(indice)
        System.out.println("===================");
        }
        else{
          System.out.println("OpÃ§Ã£o Invalida\n");
        }
      }
  }
  
  public void exibirAtividadesArquivadas(Usuario user)
  {
     System.out.println("\n\n--------------------------");
	  System.out.println("  Exibir Atividades Arquivadas");
	  System.out.println("--------------------------\n\n");
    user.exibirAtividadesArquivadas();
    System.out.println("===================");
  }
  
  public void exibirDatasAtividades(Usuario user)
  {
     System.out.println("\n\n--------------------------");
	  System.out.println("  Excluir Datas Das Atividades");
	  System.out.println("--------------------------\n\n");
    user.exibirDatasAtividades();
    System.out.println("===================");
  }
}
